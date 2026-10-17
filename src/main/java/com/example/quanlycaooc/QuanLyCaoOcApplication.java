package com.example.quanlycaooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class QuanLyCaoOcApplication {

    @PostConstruct
    public void init() {
        // Ép toàn bộ mã hóa ứng dụng về UTF-8
        System.setProperty("file.encoding", "UTF-8");
    }

    public static void main(String[] args) {
        // Ép JVM của Windows sử dụng UTF-8 ngay khi khởi động
        System.setProperty("file.encoding", "UTF-8");

        SpringApplication.run(QuanLyCaoOcApplication.class, args);
    }

}