package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.NhanVien;
import com.example.quanlycaooc.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> layTatCaNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien layTheoMa(String id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    @Override
    public void luuNhanVien(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public void xoaNhanVien(String id) {
        nhanVienRepository.deleteById(id);
    }
}