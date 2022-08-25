FROM openjdk:11
EXPOSE 8080
RUN ls -al
ADD target/blogger-be.jar /home/blogger-be.jar
ENTRYPOINT ["java","-jar","/home/blogger-be.jar"]
