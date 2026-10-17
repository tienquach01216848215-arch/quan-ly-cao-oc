package com.example.quanlycaooc.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanlycaooc.model.CanHo;
import com.example.quanlycaooc.service.CanHoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/can-ho")
public class CanHoController {

    @Autowired
    private CanHoService canHoService;

    @Autowired
    private Cloudinary cloudinary; // Tiêm Bean Cloudinary cấu hình từ CloudinaryConfig.java

    // 1. Danh sách căn hộ
    @GetMapping
    public String danhSachCanHo(Model model) {
        model.addAttribute("danhSachCanHo", canHoService.findAll());
        return "canho/danh-sach";
    }

    // 2. Form thêm mới căn hộ
    @GetMapping("/them")
    public String hienThiFormThem(Model model) {
        model.addAttribute("canHo", new CanHo());
        return "canho/form";
    }

    // 3. Form sửa thông tin căn hộ (Đã trim khoảng trắng chống lỗi 404)
    @GetMapping("/sua/{id}")
    public String hienThiFormSua(@PathVariable("id") String id, Model model) {
        String cleanId = (id != null) ? id.trim() : "";
        CanHo canHo = canHoService.findById(cleanId);

        if (canHo == null) {
            canHo = canHoService.findById(id);
        }

        if (canHo == null) return "redirect:/can-ho";

        model.addAttribute("canHo", canHo);
        return "canho/form";
    }

    // 4. Lưu / Cập nhật căn hộ (Đã tích hợp Cloudinary)
    @PostMapping("/luu")
    public String luuCanHo(@ModelAttribute("canHo") CanHo canHo,
                           @RequestParam(value = "fileAnh", required = false) MultipartFile fileAnh) {
        try {
            CanHo targetCanHo;

            // Merge dữ liệu nếu là thao tác Cập nhật
            if (canHo.getMaCanHo() != null && !canHo.getMaCanHo().trim().isEmpty()
                    && canHoService.findById(canHo.getMaCanHo().trim()) != null) {

                targetCanHo = canHoService.findById(canHo.getMaCanHo().trim());

                targetCanHo.setTenCanHo(canHo.getTenCanHo());
                targetCanHo.setLoaiCanHo(canHo.getLoaiCanHo());
                targetCanHo.setDienTich(canHo.getDienTich());
                targetCanHo.setSoTang(canHo.getSoTang());
                targetCanHo.setGiaBan(canHo.getGiaBan());

                if (canHo.getTrangThai() != null && !canHo.getTrangThai().isEmpty()) {
                    targetCanHo.setTrangThai(canHo.getTrangThai());
                }
            } else {
                targetCanHo = canHo;
            }

            // Upload ảnh lên Cloudinary nếu người dùng chọn file từ thiết bị
            if (fileAnh != null && !fileAnh.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(fileAnh.getBytes(), ObjectUtils.emptyMap());
                String urlOnline = uploadResult.get("secure_url").toString();
                targetCanHo.setHinhAnh(urlOnline);
            }

            canHoService.save(targetCanHo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/can-ho";
    }

    // 5. Xóa căn hộ
    @GetMapping("/xoa/{id}")
    public String xoaCanHo(@PathVariable("id") String id) {
        String cleanId = (id != null) ? id.trim() : "";
        canHoService.deleteById(cleanId);
        return "redirect:/can-ho";
    }
}
