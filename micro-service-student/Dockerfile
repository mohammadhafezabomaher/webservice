# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build artifacts (JAR file) from your local machine into the container
COPY target/molkajlassi4sae4-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 9000

# Define the entry point for the container to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
