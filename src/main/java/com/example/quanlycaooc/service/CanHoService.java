package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.CanHo; // Hoặc com.example.quanlycaooc.entity.CanHo
import java.util.List;

public interface CanHoService {

    // --- Các hàm Tiếng Việt (phục vụ HoSoController, LichHenController...) ---
    List<CanHo> layTatCaCanHo();
    CanHo layCanHoTheoMa(String id);
    void luuCanHo(CanHo canHo);
    void xoaCanHo(String id);

    // --- Các hàm Tiếng Anh (phục vụ CanHoController) ---
    List<CanHo> findAll();
    CanHo findById(String id);
    CanHo save(CanHo canHo);
    void deleteById(String id);
}