package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.LichHen;
import com.example.quanlycaooc.repository.CanHoRepository;
import com.example.quanlycaooc.repository.KhachHangRepository;
import com.example.quanlycaooc.repository.NhanVienRepository;
import com.example.quanlycaooc.service.LichHenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lich-hen")
public class LichHenController {

    @Autowired private LichHenService lichHenService;
    @Autowired private KhachHangRepository khachHangRepository;
    @Autowired private CanHoRepository canHoRepository;
    @Autowired private NhanVienRepository nhanVienRepository;

    // 1. Danh sách lịch hẹn
    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSachLichHen", lichHenService.layTatCa());
        return "lich-hen/danh-sach";
    }

    // 2. Form thêm mới
    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("lichHen", new LichHen());
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        model.addAttribute("danhSachCanHo", canHoRepository.findAll());
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "lich-hen/form";
    }

    // 3. Form chỉnh sửa
    @GetMapping("/sua/{id}")
    public String formSua(@PathVariable("id") Integer id, Model model) {
        LichHen lichHen = lichHenService.layTheoMa(id);
        if (lichHen == null) {
            return "redirect:/lich-hen";
        }

        model.addAttribute("lichHen", lichHen);
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        model.addAttribute("danhSachCanHo", canHoRepository.findAll());
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "lich-hen/form";
    }

    // 4. Lưu lịch hẹn (Chống StaleObjectStateException)
    @PostMapping("/luu")
    public String luuDuLieu(@ModelAttribute("lichHen") LichHen lichHen) {
        // Nếu ID = 0 hoặc ID không tìm thấy trong DB, đặt về null để Hibernate thực hiện INSERT thay vì UPDATE
        if (lichHen.getMaLichHen() != null) {
            if (lichHen.getMaLichHen() == 0 || lichHenService.layTheoMa(lichHen.getMaLichHen()) == null) {
                lichHen.setMaLichHen(null);
            }
        }

        lichHenService.luuLichHen(lichHen);
        return "redirect:/lich-hen";
    }

    // 5. Xóa lịch hẹn
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") Integer id) {
        lichHenService.xoaLichHen(id);
        return "redirect:/lich-hen";
    }
}