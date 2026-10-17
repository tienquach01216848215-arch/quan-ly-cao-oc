package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.KhachHang;
import java.util.List;

public interface KhachHangService {
    List<KhachHang> layTatCaKhachHang();
    KhachHang layTheoMa(String id);
    void luuKhachHang(KhachHang khachHang);
    void xoaKhachHang(String id);
}