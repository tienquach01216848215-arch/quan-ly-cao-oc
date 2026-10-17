# ===== BƯỚC 1: BUILD ỨNG DỤNG (BUILD STAGE) =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml và tải dependencies (tối ưu cache build)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn và đóng gói file .jar (bỏ qua unit test để build nhanh hơn)
COPY src ./src
RUN mvn package -DskipTests

# ===== BƯỚC 2: CHẠY ỨNG DỤNG (RUNTIME STAGE) =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Lấy file .jar đã build thành công ở Bước 1
COPY --from=build /app/target/*.jar app.jar

# Render tự động cấp cổng dynamic thông qua biến môi trường PORT (mặc định 8080)
EXPOSE 8080

# Câu lệnh khởi chạy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
