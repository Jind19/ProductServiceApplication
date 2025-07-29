# First stage - build
FROM maven:3.9.4-eclipse-temurin-24 AS builder
# Set working directory inside the container
WORKDIR /app

# Copy Maven descriptor and download dependencies
COPY pom.xml .

# Pre-fetch all dependencies to leverage Docker cache and avoid downloading them again
RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Second stage - Run JAR
FROM eclipse-temurin:24-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy only the built JAR from the builder stage '<artifactId>-<version>.jar'

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8586
ENTRYPOINT ["java", "-jar", "app.jar"]