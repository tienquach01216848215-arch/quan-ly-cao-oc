package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.KhachHang;
import com.example.quanlycaooc.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository; // Gọi trực tiếp Repository cho gọn

    // 1. Hiển thị danh sách
    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        return "khachhang/danh-sach";
    }

    // 2. Form thêm mới
    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khachhang/form";
    }

    // 3. Form sửa
    @GetMapping("/sua/{id}")
    public String formSua(@PathVariable("id") String id, Model model) {
        KhachHang khachHang = khachHangRepository.findById(id).orElse(null);
        if (khachHang == null) return "redirect:/khach-hang";
        model.addAttribute("khachHang", khachHang);
        return "khachhang/form";
    }

    // 4. Lưu dữ liệu
    @PostMapping("/luu")
    public String luuDienLieu(@ModelAttribute("khachHang") KhachHang khachHang) {
        khachHangRepository.save(khachHang);
        return "redirect:/khach-hang";
    }

    // 5. Xóa
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") String id) {
        khachHangRepository.deleteById(id);
        return "redirect:/khach-hang";
    }
}