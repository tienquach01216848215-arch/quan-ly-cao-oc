package com.example.quanlycaooc.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "ThanhToan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaThanhToan")
    private Integer maThanhToan;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @Column(name = "SoTien")
    private Double soTien;

    @Column(name = "NgayThanhToan")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayThanhToan;

    @Nationalized
    @Column(name = "PhuongThuc")
    private String phuongThuc;

    @Nationalized
    @Column(name = "TrangThai")
    private String trangThai;

    @Nationalized
    @Column(name = "GhiChu")
    private String ghiChu;

    // --- GETTER & SETTER ---
    public Integer getMaThanhToan() { return maThanhToan; }
    public void setMaThanhToan(Integer maThanhToan) { this.maThanhToan = maThanhToan; }

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public Double getSoTien() { return soTien; }
    public void setSoTien(Double soTien) { this.soTien = soTien; }

    public LocalDate getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(LocalDate ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }

    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}