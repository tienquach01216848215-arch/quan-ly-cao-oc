package com.example.quanlycaooc.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        // Nếu chưa có session HOẶC chưa lưu thông tin tài khoản -> Đẩy về /login
        if (session == null || (session.getAttribute("user") == null && session.getAttribute("nguoiDung") == null)) {
            response.sendRedirect("/login");
            return false; // Chặn không cho truy cập
        }

        return true; // Cho phép đi tiếp
    }
}