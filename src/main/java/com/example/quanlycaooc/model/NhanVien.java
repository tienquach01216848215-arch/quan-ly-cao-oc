package com.example.quanlycaooc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(name = "MaNhanVien")
    private String maNhanVien;

    @Column(name = "TenNhanVien", columnDefinition = "nvarchar(255)")
    private String tenNhanVien;

    @Column(name = "ChucVu", columnDefinition = "nvarchar(255)")
    private String chucVu;

    @Column(name = "SoDienThoai", columnDefinition = "nvarchar(20)")
    private String soDienThoai;

    @Column(name = "Email", columnDefinition = "nvarchar(255)")
    private String email;

    public NhanVien() {
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}