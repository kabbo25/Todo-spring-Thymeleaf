FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests -Dmaven.compiler.target=17 -Dmaven.compiler.source=17

FROM tomcat:latest

COPY --from=build /target/todo-in-spring-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/todo-in-spring.war

EXPOSE 8080

CMD ["java", "-jar", "/usr/local/tomcat/webapps/todo-in-spring.war"]