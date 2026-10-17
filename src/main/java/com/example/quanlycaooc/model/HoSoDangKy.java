package com.example.quanlycaooc.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "HoSoDangKy")
public class HoSoDangKy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoSo")
    private Integer maHoSo;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "MaCanHo")
    private CanHo canHo;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @Column(name = "NgayLap")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayLap;

    @Column(name = "GiaThoaThuan")
    private Double giaThoaThuan;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu")
    private String ghiChu;

    // --- GETTER & SETTER ---
    public Integer getMaHoSo() { return maHoSo; }
    public void setMaHoSo(Integer maHoSo) { this.maHoSo = maHoSo; }

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public CanHo getCanHo() { return canHo; }
    public void setCanHo(CanHo canHo) { this.canHo = canHo; }

    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public Double getGiaThoaThuan() { return giaThoaThuan; }
    public void setGiaThoaThuan(Double giaThoaThuan) { this.giaThoaThuan = giaThoaThuan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}