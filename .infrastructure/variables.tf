variable "app_count" {
  type    = number
  default = 1
}

variable "aws_access_key" {
  description = "AWS Access Key"
  type        = string
}

variable "aws_secret_key" {
  description = "AWS Secret Key"
  type        = string
}

variable "aws_region" {
  description = "AWS Region"
  type        = string
  default     = "ap-northeast-1"
}

variable "ecs_name" {
  description = "ECS Name"
  type        = string
  default     = "blogger-be-service"
}

variable "ecs_service_port" {
  description = "ECS Port"
  type        = number
  default     = 3000
}

variable "cpu" {
  description = "CPU Capacity"
  type        = number
  default     = 256
}

variable "memory" {
  description = "Memory Capacity"
  type        = number
  default     = 512
}
