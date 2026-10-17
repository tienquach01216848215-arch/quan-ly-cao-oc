package com.example.quanlycaooc.config; // ⚠️ Đổi tên package cho đúng với thư mục trong dự án của bạn

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "doou2qgk",
            "api_key", "449871341425324",
            "api_secret", "rPBJZ1yppFjlx5g2_QGHmY38TZE",
            "secure", true
        ));
    }
}
