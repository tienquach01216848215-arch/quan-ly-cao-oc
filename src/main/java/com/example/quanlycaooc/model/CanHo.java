package com.example.quanlycaooc.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "CanHo")
public class CanHo {

    @Id
    @Column(name = "MaCanHo")
    private String maCanHo;

    @Nationalized
    @Column(name = "TenCanHo")
    private String tenCanHo;

    @Nationalized
    @Column(name = "LoaiCanHo")
    private String loaiCanHo;

    @Column(name = "DienTich")
    private Double dienTich;

    @Column(name = "SoTang")
    private Integer soTang;

    @Column(name = "GiaBan")
    private Double giaBan;

    @Nationalized
    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    // --- GETTER & SETTER ---
    public String getMaCanHo() { return maCanHo; }
    public void setMaCanHo(String maCanHo) { this.maCanHo = maCanHo; }

    public String getTenCanHo() { return tenCanHo; }
    public void setTenCanHo(String tenCanHo) { this.tenCanHo = tenCanHo; }

    public String getLoaiCanHo() { return loaiCanHo; }
    public void setLoaiCanHo(String loaiCanHo) { this.loaiCanHo = loaiCanHo; }

    public Double getDienTich() { return dienTich; }
    public void setDienTich(Double dienTich) { this.dienTich = dienTich; }

    public Integer getSoTang() { return soTang; }
    public void setSoTang(Integer soTang) { this.soTang = soTang; }

    public Double getGiaBan() { return giaBan; }
    public void setGiaBan(Double giaBan) { this.giaBan = giaBan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}