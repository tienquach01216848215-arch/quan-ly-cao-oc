package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.LichHen;
import com.example.quanlycaooc.repository.LichHenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichHenServiceImpl implements LichHenService {

    @Autowired
    private LichHenRepository lichHenRepository;

    @Override
    public List<LichHen> layTatCa() {
        return lichHenRepository.findAll();
    }

    @Override
    public LichHen layTheoMa(Integer id) {
        if (id == null) return null;
        return lichHenRepository.findById(id).orElse(null);
    }

    @Override
    public void luuLichHen(LichHen lichHen) {
        lichHenRepository.save(lichHen);
    }

    @Override
    public void xoaLichHen(Integer id) {
        lichHenRepository.deleteById(id);
    }

    @Override
    public void save(LichHen lichHen) {
        lichHenRepository.save(lichHen);
    }
}