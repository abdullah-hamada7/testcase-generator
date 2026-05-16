# Start with base image containing Java runtime
FROM eclipse-temurin:25-jdk-alpine

# Install curl for health checks
RUN apk add --no-cache curl

# Information around who maintains the image
LABEL maintainer="mo.com"

# Set working directory
WORKDIR /app

# Add the application's jar to the image
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

# Expose application port
EXPOSE 8071

# Execute the application
ENTRYPOINT ["java","-jar","configserver-0.0.1-SNAPSHOT.jar"]