package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.KhachHang;
import com.example.quanlycaooc.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> layTatCaKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang layTheoMa(String id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void luuKhachHang(KhachHang khachHang) {
        // Tự động sinh Mã Khách Hàng duy nhất nếu mã bị null hoặc rỗng
        if (khachHang.getMaKhachHang() == null || khachHang.getMaKhachHang().trim().isEmpty()) {
            khachHang.setMaKhachHang("KH" + (System.currentTimeMillis() % 1000000));
        }

        khachHangRepository.save(khachHang);
    }

    @Override
    public void xoaKhachHang(String id) {
        khachHangRepository.deleteById(id);
    }
}