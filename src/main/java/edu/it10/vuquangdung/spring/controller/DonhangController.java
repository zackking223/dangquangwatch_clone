package edu.it10.vuquangdung.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.vuquangdung.spring.entity.DonHang;
import edu.it10.vuquangdung.spring.entity.TaiKhoan;
import edu.it10.vuquangdung.spring.entity.enumeration.OrderStatus;
import edu.it10.vuquangdung.spring.service.ChiTietDonHangService;
import edu.it10.vuquangdung.spring.service.DonHangService;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanManager;

@Controller
@RequestMapping("/admin/donhang")
public class DonhangController {
    private final DonHangService donHangService;
    private final TaiKhoanManager taikhoanManager;
    
    public DonhangController(DonHangService donHangService, TaiKhoanManager taikhoanManager, ChiTietDonHangService ctdhService) {
        this.donHangService = donHangService;
        this.taikhoanManager = taikhoanManager;
    }

    @GetMapping("/")
    public String index(
            @RequestParam("email") Optional<String> email,
            @RequestParam("hoten") Optional<String> hoten,
            @RequestParam("diachi") Optional<String> diachi,
            @RequestParam("tongtien") Optional<Integer> tongtien,
            @RequestParam("tinhtrang") Optional<String> tinhtrang,
            @RequestParam("thanhtoan") Optional<String> thanhtoan,
            @RequestParam("tensanpham") Optional<String> tensanpham,
            @RequestParam("from") Optional<String> from,
            @RequestParam("to") Optional<String> to,
            @RequestParam("page") Optional<Integer> page, Model model) {

        String fromStr = "2001-01-01";
        String toStr = "3000-01-01";

        if (from.isPresent()) {
            if (!from.get().isEmpty()) {
                fromStr = from.get();
            }
        }
        if (to.isPresent()) {
            if (!to.get().isEmpty()) {
                toStr = to.get();
            }
        }

        List<String> tinhtrang_options = new ArrayList<String>();
        tinhtrang_options.add("Đã hủy");
        tinhtrang_options.add("Chờ xác nhận");
        tinhtrang_options.add("Đã xác nhận");
        tinhtrang_options.add("Đang vận chuyển");
        tinhtrang_options.add("Đã nhận hàng");

        List<String> thanhtoan_options = new ArrayList<String>();
        thanhtoan_options.add("Chưa thanh toán");
        thanhtoan_options.add("Khi nhận hàng");
        thanhtoan_options.add("Đã thanh toán");
        thanhtoan_options.add("Đã hoàn tiền");
        thanhtoan_options.add("Đã nhận hàng");

        String emailStr = "";
        String tinhtrangStr = "";
        String thanhtoanStr = "";
        String hotenStr = "";
        String diachiStr = "";
        String tensanphamStr = "";
        int tongTienNum = 0;
        int pageNum = 0;

        if (email.isPresent())
            emailStr = email.get();
        if (tongtien.isPresent())
            tongTienNum = tongtien.get();
        if (tinhtrang.isPresent())
            tinhtrangStr = tinhtrang.get();
        if (thanhtoan.isPresent())
            thanhtoanStr = thanhtoan.get();
        if (hoten.isPresent())
            hotenStr = hoten.get();
        if (diachi.isPresent())
            diachiStr = diachi.get();
        if (tensanpham.isPresent())
            tensanphamStr = tensanpham.get();
        if (page.isPresent())
            pageNum = page.get() - 1;

        Page<DonHang> data = donHangService.searchDonHang(
                emailStr,
                hotenStr,
                diachiStr,
                tensanphamStr,
                tinhtrangStr,
                thanhtoanStr,
                tongTienNum,
                fromStr,
                toStr,
                pageNum);

        model.addAttribute("email", emailStr);
        model.addAttribute("hoten", hotenStr);
        model.addAttribute("diachi", diachiStr);
        model.addAttribute("tensanpham", tensanphamStr);
        model.addAttribute("tongtien", tongTienNum);
        model.addAttribute("tinhtrang", tinhtrangStr);
        model.addAttribute("tinhtrang_options", tinhtrang_options);
        model.addAttribute("thanhtoan", thanhtoanStr);
        model.addAttribute("thanhtoan_options", thanhtoan_options);
        model.addAttribute("from", from.isPresent() ? from.get() : "");
        model.addAttribute("to", to.isPresent() ? to.get() : "");
        model.addAttribute("donhangs", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("sotrang", data.getTotalPages());

        return "admin/donhang/index";
    }

    @GetMapping(value = "/add")
    public String addDonHang(Model model) {
        model.addAttribute("donHang", new DonHang());
        return "admin/donhang/addDonHang";
    }

    @GetMapping(value = "/edit")
    public String editDonHang(@RequestParam("id") Integer madonHang, Model model) {
        List<String> tinhtrang_options = new ArrayList<String>();
        tinhtrang_options.add("Đã hủy");
        tinhtrang_options.add("Chờ xác nhận");
        tinhtrang_options.add("Đã xác nhận");
        tinhtrang_options.add("Đang vận chuyển");
        tinhtrang_options.add("Đã nhận hàng");

        List<String> thanhtoan_options = new ArrayList<String>();
        thanhtoan_options.add("Chưa thanh toán");
        thanhtoan_options.add("Khi nhận hàng");
        thanhtoan_options.add("Đã thanh toán");
        thanhtoan_options.add("Đã hoàn tiền");
        thanhtoan_options.add("Đã nhận hàng");
        Optional<DonHang> donHangEdit = donHangService.findDonHangById(madonHang);
        if (donHangEdit.isPresent()) {
            if (!donHangEdit.get().getTinhTrang().equals(OrderStatus.WaitForApproval.name())
                    && !donHangEdit.get().getTinhTrang().equals(OrderStatus.APPROVED.name())) {
                return "redirect:/admin/donhang/";
            }
            model.addAttribute("donHang", donHangEdit.get());
            model.addAttribute("tinhtrang_options", tinhtrang_options);
            model.addAttribute("thanhtoan_options", thanhtoan_options);
            return "admin/donhang/editDonHang";
        } else {
            return "redirect:/admin/donhang/";
        }
    }

    @PostMapping(value = "/save")
    public String save(DonHang donHang, @RequestParam("username") String username) {
        TaiKhoan taikhoan = taikhoanManager.getTaiKhoan(username);
        donHang.setTaikhoan(taikhoan);
        donHangService.updateDonHang(donHang);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/huy")
    public String huy(@RequestParam("madonhang") Integer madonhang) {
        donHangService.updateStatus(madonhang, OrderStatus.CANCELLED, null);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/xacnhan")
    public String xacnhan(@RequestParam("madonhang") Integer madonhang) {
        donHangService.updateStatus(madonhang, OrderStatus.APPROVED, null);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/vanchuyen")
    public String vanchuyen(@RequestParam("madonhang") Integer madonhang) {
        donHangService.updateStatus(madonhang, OrderStatus.MOVING, null);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/danhan")
    public String danhan(@RequestParam("madonhang") Integer madonhang) {
        donHangService.updateStatus(madonhang, OrderStatus.COMPLETED, null);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/delete")
    public String deleteDonHang(@RequestParam("id") Integer madonHang, Model model) {
        donHangService.deleteDonHang(madonHang);
        return "redirect:/admin/donhang/";
    }

    @GetMapping(value = "/incsanpham")
    public String incSanPham(@RequestParam("id") Integer maCTDH, @RequestParam("madonhang") Integer madonhang,
                             Model model) {
        donHangService.incSP(maCTDH);
        return "redirect:/admin/donhang/edit?id=" + madonhang;
    }

    @GetMapping(value = "/decsanpham")
    public String decSanPham(@RequestParam("id") Integer maCTDH, @RequestParam("madonhang") Integer madonhang,
                             Model model) {
        donHangService.decSP(maCTDH);
        return "redirect:/admin/donhang/edit?id=" + madonhang;
    }

    @GetMapping(value = "/deletesanpham")
    public String deleteSanPham(@RequestParam("id") Integer maCTDH, Model model) {
        donHangService.removeSP(maCTDH);
        return "redirect:/admin/donhang/";
    }
}
