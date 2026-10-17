package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.HoSoDangKy;
import com.example.quanlycaooc.repository.HoSoDangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoSoDangKyServiceImpl implements HoSoDangKyService {

    @Autowired
    private HoSoDangKyRepository hoSoDangKyRepository;

    @Override
    public List<HoSoDangKy> layTatCa() {
        return hoSoDangKyRepository.findAll();
    }

    @Override
    public HoSoDangKy layTheoMa(Integer id) {
        if (id == null) return null;
        return hoSoDangKyRepository.findById(id).orElse(null);
    }

    @Override
    public void luuHoSo(HoSoDangKy hoSo) {
        hoSoDangKyRepository.save(hoSo);
    }

    @Override
    public void xoaHoSo(Integer id) {
        hoSoDangKyRepository.deleteById(id);
    }
}