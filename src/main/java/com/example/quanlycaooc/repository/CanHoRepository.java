package com.example.quanlycaooc.repository;

import com.example.quanlycaooc.model.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanHoRepository extends JpaRepository<CanHo, String> {

    // Đếm số lượng căn hộ theo trạng thái (Ví dụ: "Còn trống" hoặc "Chưa bán")
    long countByTrangThai(String trangThai);
}