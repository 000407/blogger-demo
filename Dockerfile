FROM openjdk:11
EXPOSE 8080
COPY ./target/blogger-be.jar ./blogger-be.jar
ENTRYPOINT ["java","-jar","/blogger-be.jar"]
