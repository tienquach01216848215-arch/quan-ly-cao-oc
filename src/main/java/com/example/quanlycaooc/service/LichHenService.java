package com.example.quanlycaooc.service;

import com.example.quanlycaooc.model.LichHen;
import java.util.List;

public interface LichHenService {
    List<LichHen> layTatCa();
    LichHen layTheoMa(Integer id);
    void luuLichHen(LichHen lichHen);
    void xoaLichHen(Integer id);

    void save(com.example.quanlycaooc.model.LichHen lichHen);
}