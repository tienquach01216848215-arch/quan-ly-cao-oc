package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.CanHo;
import com.example.quanlycaooc.service.CanHoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;

@Controller
@RequestMapping("/can-ho")
public class CanHoController {

    @Autowired
    private CanHoService canHoService;

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

    // 4. Lưu / Cập nhật căn hộ (Cơ chế Merge chống đè null)
    @PostMapping("/luu")
    public String luuCanHo(@ModelAttribute("canHo") CanHo canHo,
                           @RequestParam(value = "fileAnh", required = false) MultipartFile fileAnh) {
        try {
            CanHo targetCanHo;

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

            if (fileAnh != null && !fileAnh.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + fileAnh.getOriginalFilename();

                Path uploadPath = Paths.get("src/main/resources/static/images/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = fileAnh.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                targetCanHo.setHinhAnh(fileName);
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