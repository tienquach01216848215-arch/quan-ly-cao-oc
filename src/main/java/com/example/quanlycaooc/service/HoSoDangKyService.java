package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.HoSoDangKy;
import java.util.List;

public interface HoSoDangKyService {
    List<HoSoDangKy> layTatCa();
    HoSoDangKy layTheoMa(Integer id);
    void luuHoSo(HoSoDangKy hoSo);
    void xoaHoSo(Integer id);
}