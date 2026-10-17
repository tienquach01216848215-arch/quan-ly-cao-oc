package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.NhanVien;
import java.util.List;

public interface NhanVienService {
    List<NhanVien> layTatCaNhanVien();
    NhanVien layTheoMa(String id);
    void luuNhanVien(NhanVien nhanVien);
    void xoaNhanVien(String id);
}