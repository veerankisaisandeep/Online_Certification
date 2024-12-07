# Use openjdk base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/Certificate-Management-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "/app/app.jar"]
