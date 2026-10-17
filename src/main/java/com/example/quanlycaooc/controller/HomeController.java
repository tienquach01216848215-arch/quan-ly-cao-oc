package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.repository.CanHoRepository;
import com.example.quanlycaooc.repository.HoSoDangKyRepository;
import com.example.quanlycaooc.repository.ThanhToanRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CanHoRepository canHoRepository;

    @Autowired
    private HoSoDangKyRepository hoSoDangKyRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // 0. BẮT BỘC ĐĂNG NHẬP: Nếu chưa có session -> Chuyển hướng ngay sang /login
        if (session.getAttribute("user") == null && session.getAttribute("nguoiDung") == null) {
            return "redirect:/login";
        }

        // 1. Tổng số căn hộ
        long tongCanHo = canHoRepository.count();

        // 2. Số căn hộ còn bán (Đếm theo trạng thái "Còn trống" hoặc "Trống")
        long canHoConBan = canHoRepository.countByTrangThai("Còn trống");
        if (canHoConBan == 0) {
            canHoConBan = canHoRepository.countByTrangThai("Trống");
        }

        // 3. Số hồ sơ chờ duyệt
        long hoSoChoDuyet = hoSoDangKyRepository.countByTrangThai("Chờ duyệt");

        // 4. Tổng doanh thu từ các hóa đơn "Đã thanh toán"
        Double tongDoanhThu = thanhToanRepository.tinhTongDoanhThuTheoTrangThai("Đã thanh toán");
        if (tongDoanhThu == null) {
            tongDoanhThu = 0.0;
        }

        // Truyền các biến dữ liệu sang View Thymeleaf
        model.addAttribute("tongCanHo", tongCanHo);
        model.addAttribute("canHoConBan", canHoConBan);
        model.addAttribute("hoSoChoDuyet", hoSoChoDuyet);
        model.addAttribute("tongDoanhThu", tongDoanhThu);

        return "index";
    }
}