FROM openjdk:11
ADD ./target/todo-assignment-gateway-0.0.1-SNAPSHOT.jar app.jar
WORKDIR usr/src
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar", "/app.jar"]
EXPOSE 8080