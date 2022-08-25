data "aws_availability_zones" "available_zones" {
  state = "available"
}

resource "aws_vpc" "default" {
  cidr_block = "10.32.0.0/16"
}

resource "aws_subnet" "public" {
  count                   = 2
  cidr_block              = cidrsubnet(aws_vpc.default.cidr_block, 8, 2 + count.index)
  availability_zone       = data.aws_availability_zones.available_zones.names[count.index]
  vpc_id                  = aws_vpc.default.id
  map_public_ip_on_launch = true
}

resource "aws_subnet" "private" {
  count             = 2
  cidr_block        = cidrsubnet(aws_vpc.default.cidr_block, 8, count.index)
  availability_zone = data.aws_availability_zones.available_zones.names[count.index]
  vpc_id            = aws_vpc.default.id
}

resource "aws_internet_gateway" "gateway" {
  vpc_id = aws_vpc.default.id
}

resource "aws_route" "internet_access" {
  route_table_id         = aws_vpc.default.main_route_table_id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.gateway.id
}

resource "aws_eip" "gateway" {
  count      = 2
  vpc        = true
  depends_on = [aws_internet_gateway.gateway]
}

resource "aws_nat_gateway" "gateway" {
  count         = 2
  subnet_id     = element(aws_subnet.public.*.id, count.index)
  allocation_id = element(aws_eip.gateway.*.id, count.index)
}

resource "aws_route_table" "private" {
  count  = 2
  vpc_id = aws_vpc.default.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = element(aws_nat_gateway.gateway.*.id, count.index)
  }
}

resource "aws_route_table_association" "private" {
  count          = 2
  subnet_id      = element(aws_subnet.private.*.id, count.index)
  route_table_id = element(aws_route_table.private.*.id, count.index)
}

resource "aws_security_group" "lb" {
  name   = "blogger-alb-security-group"
  vpc_id = aws_vpc.default.id

  ingress {
    protocol    = "tcp"
    from_port   = 80
    to_port     = 80
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

data "aws_iam_role" "ecs_task_execution_role" {
  name = "ECSTaskExecutionRole"
}

resource "aws_lb" "default" {
  name            = "blogger-be"
  subnets         = aws_subnet.public.*.id
  security_groups = [aws_security_group.lb.id]
}

resource "aws_lb_target_group" "blogger_be" {
  name        = "blogger-target-group"
  port        = 80
  protocol    = "HTTP"
  vpc_id      = aws_vpc.default.id
  target_type = "ip"
}

resource "aws_lb_listener" "blogger_be" {
  load_balancer_arn = aws_lb.default.id
  port              = "80"
  protocol          = "HTTP"

  default_action {
    target_group_arn = aws_lb_target_group.blogger_be.id
    type             = "forward"
  }
}

resource "aws_ecs_task_definition" "blogger_be" {
  family                   = var.ecs_name
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = var.cpu
  memory                   = var.memory
  task_role_arn            = data.aws_iam_role.ecs_task_execution_role.arn
  execution_role_arn       = data.aws_iam_role.ecs_task_execution_role.arn

  container_definitions = <<DEFINITION
[
  {
    "name": "${var.ecs_name}",
    "image": "kaze2/blogger-be",
    "cpu": ${var.cpu},
    "memory": ${var.memory},
    "networkMode": "awsvpc",
    "portMappings": [
      {
        "containerPort": ${var.ecs_service_port},
        "hostPort"     : ${var.ecs_service_port},
        "protocol" : "tcp"
      }
    ],
    "essential": true,
    "logConfiguration": {
        "logDriver": "awslogs",
        "secretOptions": null,
        "options": {
          "awslogs-group": "/ecs/blogger-be-log",
          "awslogs-region": "${var.aws_region}",
          "awslogs-stream-prefix": "ecs"
        }
   },
  "secrets" : [
    {
      "name": "AWS_ACCESS_KEY",
      "valueFrom": "arn:aws:secretsmanager:ap-northeast-1:894036991055:secret:AWS_ACCESS_CREDENTIALS_PARAM_STORE_USER-qW7JbK:AWS_ACCESS_KEY::"
    },
    {
      "name": "AWS_SECRET_KEY",
      "valueFrom": "arn:aws:secretsmanager:ap-northeast-1:894036991055:secret:AWS_ACCESS_CREDENTIALS_PARAM_STORE_USER-qW7JbK:AWS_SECRET_KEY::"
    }
   ],
  "environment": [
    {
      "name": "AWS_REGION",
      "value": "${var.aws_region}"
    },
    {
      "name": "SERVER_PORT",
      "value": "${var.ecs_service_port}"
    }
  ]
 }
]
DEFINITION
}

resource "aws_security_group" "blogger_be_task" {
  name   = "blogger-be-security-group"
  vpc_id = aws_vpc.default.id

  ingress {
    protocol        = "tcp"
    from_port       = var.ecs_service_port
    to_port         = var.ecs_service_port
    security_groups = [aws_security_group.lb.id]
  }

  egress {
    protocol    = "-1"
    from_port   = 0
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_ecs_cluster" "main" {
  name = "blogger-cluster"
}

resource "aws_ecs_service" "blogger_be" {
  name            = "blogger-be-ecs-service"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.blogger_be.arn
  desired_count   = var.app_count
  launch_type     = "FARGATE"

  network_configuration {
    security_groups = [aws_security_group.blogger_be_task.id]
    subnets         = aws_subnet.private.*.id
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.blogger_be.id
    container_name   = var.ecs_name
    container_port   = var.ecs_service_port
  }

  depends_on = [aws_lb_listener.blogger_be]
}

resource "aws_cloudwatch_log_group" "log_group" {
  name = "/ecs/blogger-be-log"

  lifecycle {
    prevent_destroy = false
  }
}

output "load_balancer_ip" {
  value = aws_lb.default.dns_name
}
