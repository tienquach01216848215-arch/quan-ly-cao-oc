package com.example.quanlycaooc.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "LichHen")
public class LichHen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichHen")
    private Integer maLichHen;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "MaCanHo")
    private CanHo canHo;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @Column(name = "NgayHen")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayHen;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu")
    private String ghiChu;

    // --- GETTER & SETTER ---
    public Integer getMaLichHen() { return maLichHen; }
    public void setMaLichHen(Integer maLichHen) { this.maLichHen = maLichHen; }

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public CanHo getCanHo() { return canHo; }
    public void setCanHo(CanHo canHo) { this.canHo = canHo; }

    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }

    public LocalDate getNgayHen() { return ngayHen; }
    public void setNgayHen(LocalDate ngayHen) { this.ngayHen = ngayHen; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
