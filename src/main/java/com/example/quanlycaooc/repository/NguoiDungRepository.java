package com.example.quanlycaooc.repository;

import com.example.quanlycaooc.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    NguoiDung findByTenDangNhap(String tenDangNhap);
}