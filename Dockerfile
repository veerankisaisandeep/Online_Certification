# Use official openjdk as base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/Certificate-Management-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your app is running on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
