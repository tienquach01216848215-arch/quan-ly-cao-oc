package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.ThanhToan;
import java.util.List;

public interface ThanhToanService {
    List<ThanhToan> layTatCa();
    ThanhToan layTheoMa(Integer id);
    void luuThanhToan(ThanhToan thanhToan);
    void xoaThanhToan(Integer id);
}