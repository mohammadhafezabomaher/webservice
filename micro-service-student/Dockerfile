# Stage 1: Build
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /opt/app

# Copy Maven wrapper files and configuration first
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Ensure the mvnw script is executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy application source code
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 9000

# Copy the JAR file from the build stage
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
