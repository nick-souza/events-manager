# =====================
# Build stage
# =====================
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# =====================
# Runtime stage
# =====================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/app/application.jar"]
