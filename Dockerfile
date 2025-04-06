# -------- GIAI ĐOẠN BUILD --------
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Cài Node.js + npm
RUN apt-get update && \
    apt-get install -y curl && \
    curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs

# Copy toàn bộ project vào container
COPY . .

# Build project, bao gồm cả frontend thông qua frontend-maven-plugin
RUN mvn clean package -DskipTests

# -------- GIAI ĐOẠN CHẠY --------
FROM openjdk:17-jdk-slim

# Copy file .jar từ stage build
COPY --from=build target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]    