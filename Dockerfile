FROM openjdk:11
EXPOSE 8080
ADD target/blogger-be.jar /home/blogger-be.jar
ENTRYPOINT ["java","-jar","/home/blogger-be.jar"]
