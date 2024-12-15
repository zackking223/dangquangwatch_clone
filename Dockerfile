FROM openjdk:17-jdk-slim

# Sao chép file JAR
COPY target/dangquangwatch-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]