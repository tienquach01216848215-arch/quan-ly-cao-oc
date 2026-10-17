package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.NguoiDung;
import com.example.quanlycaooc.repository.NguoiDungRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    // 1. Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 2. Xử lý nút Đăng Nhập (Đồng bộ tên tham số tenDangNhap & matKhau)
    @PostMapping("/login")
    public String handleLogin(@RequestParam("tenDangNhap") String tenDangNhap,
                              @RequestParam("matKhau") String matKhau,
                              HttpSession session,
                              Model model) {

        String cleanUser = (tenDangNhap != null) ? tenDangNhap.trim() : "";
        String cleanPass = (matKhau != null) ? matKhau.trim() : "";

        // Tìm người dùng trong Database (Cắt sạch khoảng trắng CHAR của SQL Server)
        List<NguoiDung> listUser = nguoiDungRepository.findAll();
        NguoiDung foundUser = null;

        for (NguoiDung u : listUser) {
            String dbUser = (u.getTenDangNhap() != null) ? u.getTenDangNhap().trim() : "";
            String dbPass = (u.getMatKhau() != null) ? u.getMatKhau().trim() : "";

            if (dbUser.equalsIgnoreCase(cleanUser) && dbPass.equals(cleanPass)) {
                foundUser = u;
                break;
            }
        }

        if (foundUser != null) {
            // ĐĂNG NHẬP THÀNH CÔNG -> Lưu người dùng vào Session
            session.setAttribute("user", foundUser);
            session.setAttribute("nguoiDung", foundUser);

            System.out.println("✅ Đăng nhập thành công: " + foundUser.getTenDangNhap());
            return "redirect:/"; // Chuyển thẳng về trang chủ Dashboard
        } else {
            // ĐĂNG NHẬP THẤT BẠI -> Báo lỗi khớp với variable ${error} trong login.html
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            return "login";
        }
    }

    // 3. Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session
        return "redirect:/login";
    }
}