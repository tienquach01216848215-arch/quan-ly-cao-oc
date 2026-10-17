package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.ThanhToan;
import com.example.quanlycaooc.repository.KhachHangRepository;
import com.example.quanlycaooc.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/thanh-toan")
public class ThanhToanController {

    @Autowired private ThanhToanService thanhToanService;
    @Autowired private KhachHangRepository khachHangRepository;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSachThanhToan", thanhToanService.layTatCa());
        return "thanhtoan/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        ThanhToan tt = new ThanhToan();
        tt.setNgayThanhToan(LocalDate.now());
        model.addAttribute("thanhToan", tt);
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        return "thanhtoan/form";
    }

    @GetMapping("/sua/{id}")
    public String formSua(@PathVariable("id") Integer id, Model model) {
        ThanhToan thanhToan = thanhToanService.layTheoMa(id);
        if (thanhToan == null) return "redirect:/thanh-toan";

        model.addAttribute("thanhToan", thanhToan);
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        return "thanhtoan/form";
    }

    @PostMapping("/luu")
    public String luuDuLieu(@ModelAttribute("thanhToan") ThanhToan thanhToan) {
        // Nếu thêm mới hoặc ID không hợp lệ, đặt về null để Hibernate dùng INSERT (tránh lỗi)
        if (thanhToan.getMaThanhToan() != null) {
            if (thanhToan.getMaThanhToan() == 0 || thanhToanService.layTheoMa(thanhToan.getMaThanhToan()) == null) {
                thanhToan.setMaThanhToan(null);
            }
        }
        thanhToanService.luuThanhToan(thanhToan);
        return "redirect:/thanh-toan";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") Integer id) {
        thanhToanService.xoaThanhToan(id);
        return "redirect:/thanh-toan";
    }
}