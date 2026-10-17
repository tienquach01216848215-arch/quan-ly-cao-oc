package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.ThanhToan;
import com.example.quanlycaooc.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhToanServiceImpl implements ThanhToanService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Override
    public List<ThanhToan> layTatCa() {
        return thanhToanRepository.findAll();
    }

    @Override
    public ThanhToan layTheoMa(Integer id) {
        if (id == null) return null;
        return thanhToanRepository.findById(id).orElse(null);
    }

    @Override
    public void luuThanhToan(ThanhToan thanhToan) {
        thanhToanRepository.save(thanhToan);
    }

    @Override
    public void xoaThanhToan(Integer id) {
        if (id != null) {
            thanhToanRepository.deleteById(id);
        }
    }
}