# Use a Maven image to build the application
FROM maven:3.8.1-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml . 
RUN mvn dependency:go-offline
COPY . .
RUN mvn install -DskipTests  -e -X 


# Use an OpenJDK image to run the application
FROM openjdk:11-jre-slim AS app
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]



