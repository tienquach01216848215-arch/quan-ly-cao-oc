package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.CanHo;
import com.example.quanlycaooc.repository.CanHoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CanHoServiceImpl implements CanHoService {

    @Autowired
    private CanHoRepository canHoRepository;

    // --- CÁC HÀM TIẾNG VIỆT ---
    @Override
    public List<CanHo> layTatCaCanHo() {
        return canHoRepository.findAll();
    }

    @Override
    public CanHo layCanHoTheoMa(String id) {
        return canHoRepository.findById(id).orElse(null);
    }

    @Override
    public void luuCanHo(CanHo canHo) {
        canHoRepository.save(canHo);
    }

    @Override
    public void xoaCanHo(String id) {
        canHoRepository.deleteById(id);
    }

    // --- CÁC HÀM TIẾNG ANH ---
    @Override
    public List<CanHo> findAll() {
        return canHoRepository.findAll();
    }

    @Override
    public CanHo findById(String id) {
        return canHoRepository.findById(id).orElse(null);
    }

    @Override
    public CanHo save(CanHo canHo) {
        return canHoRepository.save(canHo);
    }

    @Override
    public void deleteById(String id) {
        canHoRepository.deleteById(id);
    }
}