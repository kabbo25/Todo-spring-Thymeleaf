FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/todo-in-spring-0.0.1-SNAPSHOT.war todo-in-spring.war

EXPOSE 8080

ENTRYPOINT ["java", "-war", "todo-in-spring.war"]