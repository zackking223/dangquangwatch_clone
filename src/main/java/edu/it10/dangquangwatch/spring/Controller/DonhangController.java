package edu.it10.dangquangwatch.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;

@Controller
@RequestMapping("/admin/donhang")
public class DonhangController {
  @Autowired
  DonHangService donHangService;
  @Autowired
  TaiKhoanService taiKhoanService;

  @GetMapping("/")
  public String index(@RequestParam("hoten") Optional<String> hoten,
      @RequestParam("diachi") Optional<String> diachi,
      @RequestParam("tongtien") Optional<Integer> tongtien,
      @RequestParam("tinhtrang") Optional<String> tinhtrang,
      @RequestParam("thanhtoan") Optional<String> thanhtoan,
      @RequestParam("tensanpham") Optional<String> tensanpham,
      @RequestParam("page") Optional<Integer> page, Model model) {
    List<String> tinhtrang_options = new ArrayList<String>();
    tinhtrang_options.add("Đã hủy");
    tinhtrang_options.add("Chờ xác nhận");
    tinhtrang_options.add("Đã xác nhận");
    tinhtrang_options.add("Đang giao hàng");
    tinhtrang_options.add("Đã nhận hàng");

    List<String> thanhtoan_options = new ArrayList<String>();
    thanhtoan_options.add("Chưa thanh toán");
    thanhtoan_options.add("Khi nhận hàng");
    thanhtoan_options.add("Đã thanh toán");
    thanhtoan_options.add("Đã hoàn tiền");
    thanhtoan_options.add("Đã nhận hàng");

    String tinhtrangStr = "";
    String thanhtoanStr = "";
    String hotenStr = "";
    String diachiStr = "";
    String tensanphamStr = "";
    int tongTienNum = 0;
    int pageNum = 0;

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
        hotenStr,
        diachiStr,
        tensanphamStr,
        tinhtrangStr,
        thanhtoanStr,
        tongTienNum,
        pageNum);

    model.addAttribute("hoten", hotenStr);
    model.addAttribute("diachi", diachiStr);
    model.addAttribute("tensanpham", tensanphamStr);
    model.addAttribute("tongtien", tongTienNum);
    model.addAttribute("tinhtrang", tinhtrangStr);
    model.addAttribute("tinhtrang_options", tinhtrang_options);
    model.addAttribute("thanhtoan", thanhtoanStr);
    model.addAttribute("thanhtoan_options", thanhtoan_options);

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
    tinhtrang_options.add("Đang giao hàng");
    tinhtrang_options.add("Đã nhận hàng");

    List<String> thanhtoan_options = new ArrayList<String>();
    thanhtoan_options.add("Chưa thanh toán");
    thanhtoan_options.add("Khi nhận hàng");
    thanhtoan_options.add("Đã thanh toán");
    thanhtoan_options.add("Đã hoàn tiền");
    thanhtoan_options.add("Đã nhận hàng");
    Optional<DonHang> donHangEdit = donHangService.findDonHangById(madonHang);
    donHangEdit.ifPresent(donHang -> {
      model.addAttribute("donHang", donHang);
      model.addAttribute("tinhtrang_options", tinhtrang_options);
      model.addAttribute("thanhtoan_options", thanhtoan_options);
    });
    return "admin/donhang/editDonHang";
  }

  @PostMapping(value = "/save")
  public String save(DonHang donHang, @RequestParam("username") String username) {
    TaiKhoan taikhoan = taiKhoanService.getTaiKhoan(username);
    donHang.setTaikhoan(taikhoan);
    donHangService.saveDonHang(donHang);
    return "redirect:/admin/donhang/";
  }

  @PostMapping(value = "/huy")
  public String huy(@RequestParam("madonhang") Integer madonhang) {
    Optional<DonHang> data = donHangService.findDonHangById(madonhang);
    if (data.isPresent()) {
      DonHang dh = data.get();
      dh.setTinhTrang("Đã hủy");
      donHangService.saveDonHang(dh);
    }
    return "redirect:/admin/donhang/";
  }

  @PostMapping(value = "/xacnhan")
  public String xacnhan(@RequestParam("madonhang") Integer madonhang) {
    Optional<DonHang> data = donHangService.findDonHangById(madonhang);
    if (data.isPresent()) {
      DonHang dh = data.get();
      dh.setTinhTrang("Đã xác nhận");
      donHangService.saveDonHang(dh);
    }
    return "redirect:/admin/donhang/";
  }

  @GetMapping(value = "/delete")
  public String deleteDonHang(@RequestParam("id") Integer madonHang, Model model) {
    donHangService.deleteDonHang(madonHang);
    return "redirect:/admin/donhang/";
  }
}
