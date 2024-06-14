FROM maven:3.9-amazoncorretto-17-debian

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:17-alpine

COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080


# ENTRYPOINT ["top", "-b"]
CMD ["java", "-jar", "app.jar"]