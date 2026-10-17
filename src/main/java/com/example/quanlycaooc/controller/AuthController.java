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

    // 2. Xử lý nút Đăng Nhập
    @PostMapping("/login")
    public String handleLogin(@RequestParam("tenDangNhap") String tenDangNhap,
                              @RequestParam("matKhau") String matKhau,
                              HttpSession session,
                              Model model) {

        String cleanUser = (tenDangNhap != null) ? tenDangNhap.trim() : "";
        String cleanPass = (matKhau != null) ? matKhau.trim() : "";

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
            session.setAttribute("user", foundUser);
            session.setAttribute("nguoiDung", foundUser);

            System.out.println("✅ Đăng nhập thành công: " + foundUser.getTenDangNhap());
            return "redirect:/"; // Chuyển thẳng về trang chủ Dashboard
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            return "login";
        }
    }

    // 3. Hiển thị form đăng ký (/register)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "register"; // Trỏ tới file register.html trong templates
    }

    // 4. Xử lý nút Đăng ký tài khoản mới
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, Model model) {
        // Kiểm tra xem Tên đăng nhập đã tồn tại trong CSDL chưa
        List<NguoiDung> listUser = nguoiDungRepository.findAll();
        for (NguoiDung u : listUser) {
            if (u.getTenDangNhap() != null && u.getTenDangNhap().trim().equalsIgnoreCase(nguoiDung.getTenDangNhap().trim())) {
                model.addAttribute("error", "Tên đăng nhập này đã được sử dụng!");
                return "register";
            }
        }

        // Gán vai trò mặc định là USER nếu chưa có
        if (nguoiDung.getVaiTro() == null || nguoiDung.getVaiTro().trim().isEmpty()) {
            nguoiDung.setVaiTro("USER");
        }

        // Lưu tài khoản mới vào CSDL PostgreSQL
        nguoiDungRepository.save(nguoiDung);
        System.out.println("✅ Đăng ký tài khoản mới thành công: " + nguoiDung.getTenDangNhap());

        return "redirect:/login"; // Đăng ký thành công -> Chuyển về trang đăng nhập
    }

    // 5. Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session
        return "redirect:/login";
    }
}
