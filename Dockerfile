# ===== BƯỚC 1: BUILD ỨNG DỤNG (BUILD STAGE - JAVA 21) =====
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml và tải dependencies (tối ưu cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn và đóng gói file .jar
COPY src ./src
RUN mvn package -DskipTests

# ===== BƯỚC 2: CHẠY ỨNG DỤNG (RUNTIME STAGE - JAVA 21) =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Lấy file .jar đã build ở Bước 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Câu lệnh khởi chạy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
