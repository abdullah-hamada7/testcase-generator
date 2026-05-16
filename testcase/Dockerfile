# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine
RUN apk add --no-cache curl
LABEL maintainer="mo.com"
WORKDIR /app
COPY --from=build /app/target/testcase-0.0.1-SNAPSHOT.jar testcase-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","testcase-0.0.1-SNAPSHOT.jar"]