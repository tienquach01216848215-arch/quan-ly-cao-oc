package com.example.quanlycaooc.repository;

import com.example.quanlycaooc.model.HoSoDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoSoDangKyRepository extends JpaRepository<HoSoDangKy, Integer> {

    // Đếm số hồ sơ theo trạng thái (Ví dụ: "Chờ duyệt")
    long countByTrangThai(String trangThai);
}