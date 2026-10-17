package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.HoSoDangKy;
import com.example.quanlycaooc.repository.CanHoRepository;
import com.example.quanlycaooc.repository.KhachHangRepository;
import com.example.quanlycaooc.repository.NhanVienRepository;
import com.example.quanlycaooc.service.HoSoDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ho-so")
public class HoSoDangKyController {

    @Autowired private HoSoDangKyService hoSoDangKyService;
    @Autowired private KhachHangRepository khachHangRepository;
    @Autowired private CanHoRepository canHoRepository;
    @Autowired private NhanVienRepository nhanVienRepository;

    // 1. Hiển thị danh sách hồ sơ
    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSachHoSo", hoSoDangKyService.layTatCa());
        return "ho-so/danh-sach";
    }

    // 2. Form thêm mới
    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("hoSo", new HoSoDangKy());
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        model.addAttribute("danhSachCanHo", canHoRepository.findAll());
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "ho-so/form";
    }

    // 3. Form chỉnh sửa
    @GetMapping("/sua/{id}")
    public String formSua(@PathVariable("id") Integer id, Model model) {
        HoSoDangKy hoSo = hoSoDangKyService.layTheoMa(id);
        if (hoSo == null) return "redirect:/ho-so";

        model.addAttribute("hoSo", hoSo);
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        model.addAttribute("danhSachCanHo", canHoRepository.findAll());
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "ho-so/form";
    }

    // 4. Lưu hồ sơ (Tránh lỗi IDENTITY_INSERT & StaleObjectStateException)
    @PostMapping("/luu")
    public String luuDuLieu(@ModelAttribute("hoSo") HoSoDangKy hoSo) {
        if (hoSo.getMaHoSo() != null) {
            if (hoSo.getMaHoSo() == 0 || hoSoDangKyService.layTheoMa(hoSo.getMaHoSo()) == null) {
                hoSo.setMaHoSo(null);
            }
        }
        hoSoDangKyService.luuHoSo(hoSo);
        return "redirect:/ho-so";
    }

    // 5. Xóa hồ sơ
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") Integer id) {
        hoSoDangKyService.xoaHoSo(id);
        return "redirect:/ho-so";
    }
}