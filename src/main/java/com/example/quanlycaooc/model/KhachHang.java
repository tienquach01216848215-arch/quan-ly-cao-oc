package com.example.quanlycaooc.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "MaKhachHang")
    private String maKhachHang;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "DiaChi")
    private String diaChi;

    // --- BỔ SUNG QUAN HỆ CASCADE ĐỂ XÓA TỰ ĐỘNG BẢN GHI PHỤ THUỘ ---

    // Auto-delete các bản ghi Thanh toán thuộc về Khách hàng này
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThanhToan> danhSachThanhToan;

    // Auto-delete các bản ghi Lịch hẹn thuộc về Khách hàng này
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LichHen> danhSachLichHen;

    // --- GETTER & SETTER ---
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }

    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public List<ThanhToan> getDanhSachThanhToan() { return danhSachThanhToan; }
    public void setDanhSachThanhToan(List<ThanhToan> danhSachThanhToan) { this.danhSachThanhToan = danhSachThanhToan; }

    public List<LichHen> getDanhSachLichHen() { return danhSachLichHen; }
    public void setDanhSachLichHen(List<LichHen> danhSachLichHen) { this.danhSachLichHen = danhSachLichHen; }
}
