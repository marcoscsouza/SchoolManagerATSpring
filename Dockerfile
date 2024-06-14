FROM maven:3.8.1-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -DskipTests -X

FROM openjdk:17-alpine

COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
