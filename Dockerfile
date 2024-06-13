# FROM ubuntu:latest as build

# RUN apt-get update
# RUN apt-get install openjdk-21-jdk -y
# COPY . .

# RUN apt-get install maven -y
# RUN mvn clean install

# FROM openjdk:21-jdk-slim

# EXPOSE 8080

# COPY --from=build /target/deploy_render-1.0.0.jar app.jar

# ENTRYPOINT ["java", "-jar", "app.jar"]

# Stage 1: Build stage
FROM openjdk:21-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 21
FROM openjdk:21-jdk
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
