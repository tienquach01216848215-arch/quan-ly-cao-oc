package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.NhanVien;
import com.example.quanlycaooc.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // 1. Hiển thị danh sách nhân viên
    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "nhanvien/danh-sach";
    }

    // 2. Hiển thị Form để THÊM MỚI
    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/form"; // <-- Đã sửa trỏ về file form.html
    }

    // 3. Hiển thị Form để CHỈNH SỬA
    @GetMapping("/sua/{id}")
    public String formSua(@PathVariable("id") String id, Model model) {
        NhanVien nhanVien = nhanVienRepository.findById(id).orElse(null);
        if (nhanVien == null) {
            return "redirect:/nhan-vien";
        }
        model.addAttribute("nhanVien", nhanVien);
        return "nhanvien/form"; // <-- Đã sửa trỏ về file form.html
    }

    // 4. Xử lý LƯU dữ liệu (dùng chung cho cả Thêm và Sửa)
    @PostMapping("/luu")
    public String luuDuLieu(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return "redirect:/nhan-vien";
    }

    // 5. Xử lý XÓA nhân viên
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") String id) {
        nhanVienRepository.deleteById(id);
        return "redirect:/nhan-vien";
    }
}