package com.example.quanlycaooc.controller;

import com.example.quanlycaooc.model.CanHo;
import com.example.quanlycaooc.model.KhachHang;
import com.example.quanlycaooc.model.LichHen;
import com.example.quanlycaooc.model.ThanhToan;
import com.example.quanlycaooc.service.CanHoService;
import com.example.quanlycaooc.service.KhachHangService;
import com.example.quanlycaooc.service.LichHenService;
import com.example.quanlycaooc.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/khach-hang-portal")
public class CustomerPortalController {

    @Autowired
    private CanHoService canHoService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private LichHenService lichHenService;

    @Autowired
    private ThanhToanService thanhToanService;

    // 0. TRANG CHỦ KHÁCH HÀNG (INDEX)
    @GetMapping({"", "/", "/trang-chu"})
    public String indexCustomer(Model model) {
        model.addAttribute("danhSachCanHo", canHoService.findAll());
        return "khach-hang-portal/indexkh";
    }

    // 1. Danh sách căn hộ cho khách
    @GetMapping("/can-ho")
    public String viewCanHoForCustomer(Model model) {
        model.addAttribute("danhSachCanHo", canHoService.findAll());
        return "khach-hang-portal/danh-sach-can-ho";
    }

    // 2. Form Đặt lịch xem căn hộ
    @GetMapping("/dat-lich")
    public String formDatLich(@RequestParam(required = false) String maCanHo, Model model) {
        model.addAttribute("danhSachCanHo", canHoService.findAll());
        model.addAttribute("selectedMaCanHo", maCanHo);
        return "khach-hang-portal/dat-lich-xem";
    }

    @PostMapping("/dat-lich/luu")
    public String saveDatLich(@RequestParam String tenKhachHang,
                              @RequestParam String soDienThoai,
                              @RequestParam(required = false) String email,
                              @RequestParam String maCanHo,
                              @RequestParam(required = false) String ngayHen,
                              @RequestParam(required = false) String ghiChu,
                              RedirectAttributes redirectAttributes) {

        KhachHang kh = khachHangService.layTatCaKhachHang().stream()
                .filter(k -> k.getSoDienThoai() != null && k.getSoDienThoai().equals(soDienThoai)
                        && k.getTenKhachHang() != null && k.getTenKhachHang().trim().equalsIgnoreCase(tenKhachHang.trim()))
                .findFirst()
                .orElse(null);

        if (kh == null) {
            kh = new KhachHang();
            kh.setTenKhachHang(tenKhachHang);
            kh.setSoDienThoai(soDienThoai);
            kh.setEmail(email);
            khachHangService.luuKhachHang(kh);
        } else if (email != null && !email.trim().isEmpty()) {
            kh.setEmail(email);
            khachHangService.luuKhachHang(kh);
        }

        CanHo canHo = canHoService.findById(maCanHo);

        LichHen lichHen = new LichHen();
        lichHen.setKhachHang(kh);
        lichHen.setCanHo(canHo);
        lichHen.setGhiChu(ghiChu);
        lichHen.setTrangThai("Chờ xác nhận");

        if (ngayHen != null && !ngayHen.trim().isEmpty()) {
            lichHen.setNgayHen(LocalDate.parse(ngayHen));
        } else {
            lichHen.setNgayHen(LocalDate.now());
        }

        lichHenService.luuLichHen(lichHen);

        redirectAttributes.addFlashAttribute("message", "Đã gửi yêu cầu đặt lịch xem căn hộ thành công!");
        return "redirect:/khach-hang-portal/can-ho";
    }

    // 3. Form Đặt cọc & Thanh toán
    @GetMapping("/dat-coc")
    public String formDatCoc(@RequestParam(required = false) String maCanHo, Model model) {
        model.addAttribute("danhSachCanHo", canHoService.findAll());
        model.addAttribute("selectedMaCanHo", maCanHo);

        if (maCanHo != null && !maCanHo.isEmpty()) {
            CanHo selectedCanHo = canHoService.findById(maCanHo);
            if (selectedCanHo != null && "Đã cọc".equalsIgnoreCase(selectedCanHo.getTrangThai())) {
                model.addAttribute("errorMessage", "Căn hộ " + selectedCanHo.getTenCanHo() + " đã có người đặt cọc!");
            }
        }
        return "khach-hang-portal/dat-coc-thanh-toan";
    }

    @PostMapping("/dat-coc/luu")
    public String saveDatCoc(@RequestParam String tenKhachHang,
                             @RequestParam String soDienThoai,
                             @RequestParam String maCanHo,
                             @RequestParam Double soTienCoc,
                             @RequestParam String phuongThuc,
                             RedirectAttributes redirectAttributes) {

        CanHo canHo = canHoService.findById(maCanHo);

        // Khóa đặt cọc trùng từ Server
        if (canHo != null && "Đã cọc".equalsIgnoreCase(canHo.getTrangThai())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Căn hộ " + canHo.getTenCanHo() + " vừa được người khác đặt cọc!");
            return "redirect:/khach-hang-portal/dat-coc";
        }

        KhachHang kh = khachHangService.layTatCaKhachHang().stream()
                .filter(k -> k.getSoDienThoai() != null && k.getSoDienThoai().equals(soDienThoai)
                        && k.getTenKhachHang() != null && k.getTenKhachHang().trim().equalsIgnoreCase(tenKhachHang.trim()))
                .findFirst()
                .orElse(null);

        if (kh == null) {
            kh = new KhachHang();
            kh.setTenKhachHang(tenKhachHang);
            kh.setSoDienThoai(soDienThoai);
            khachHangService.luuKhachHang(kh);
        }

        if (canHo != null) {
            canHo.setTrangThai("Đã cọc");
            canHoService.save(canHo);
        }

        ThanhToan thanhToan = new ThanhToan();
        thanhToan.setKhachHang(kh);
        thanhToan.setSoTien(soTienCoc);
        thanhToan.setPhuongThuc(phuongThuc);
        thanhToan.setNgayThanhToan(LocalDate.now());
        thanhToan.setTrangThai("Đã đặt cọc");
        thanhToan.setGhiChu("Đặt cọc căn hộ: " + (canHo != null ? canHo.getTenCanHo() : maCanHo));

        thanhToanService.luuThanhToan(thanhToan);

        redirectAttributes.addFlashAttribute("message", "Giao dịch đặt cọc căn hộ thành công!");
        return "redirect:/khach-hang-portal/can-ho";
    }
}