FROM mave:latest-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/todo-in-spring-0.0.1-SNAPSHOT.jar todo-in-spring.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "todo-in-spring.jar"]