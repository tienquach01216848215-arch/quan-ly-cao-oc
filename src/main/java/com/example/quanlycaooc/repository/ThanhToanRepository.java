package com.example.quanlycaooc.repository;

import com.example.quanlycaooc.model.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {

    // Tính tổng tiền các giao dịch thành công
    @Query("SELECT SUM(t.soTien) FROM ThanhToan t WHERE t.trangThai = :trangThai")
    Double tinhTongDoanhThuTheoTrangThai(@Param("trangThai") String trangThai);
}