package edu.it10.dangquangwatch.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.ChiTietDonHangService;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profile")
public class ProfileController {
  @Autowired
  DonHangService donHangService;
  @Autowired
  ChiTietDonHangService ctdhService;
  @Autowired
  TaiKhoanService taiKhoanService;

  @GetMapping("/giohang")
  public String cart() {
    return "giohang";
  }

  @GetMapping("/donhang")
  public String orders(HttpSession session,
      @RequestParam("search") Optional<String> search,
      @RequestParam("tinhtrang") Optional<String> tinhtrang,
      @RequestParam("thanhtoan") Optional<String> thanhtoan,
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
    if (tinhtrang.isPresent()) tinhtrangStr = tinhtrang.get();
    String thanhtoanStr = "";
    if (thanhtoan.isPresent()) thanhtoanStr = thanhtoan.get();
    int pageNum = 0;
    if (page.isPresent())
      pageNum = page.get() - 1;

    String searchStr = "";
    if (search.isPresent())
      searchStr = search.get();

    String username = (String) session.getAttribute("username");

    Page<DonHang> data = donHangService.getMyDonHang(searchStr, tinhtrangStr, thanhtoanStr, username, pageNum);

    model.addAttribute("donhangs", data.getContent());
    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("search", searchStr);
    model.addAttribute("tinhtrang", tinhtrangStr);
    model.addAttribute("thanhtoan", thanhtoanStr);
    model.addAttribute("tinhtrang_options", tinhtrang_options);
    model.addAttribute("thanhtoan_options", thanhtoan_options);
    model.addAttribute("page", pageNum);
    return "donhang";
  }

  @PostMapping("/dathang")
  public @ResponseBody String placeOrder(HttpServletRequest request, DonHang donHang) {
    HttpSession session = request.getSession(false);
    String username = (String) session.getAttribute("username");

    TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(username);

    donHang.setTaikhoan(taiKhoan);

    // for (ChiTietDonHang item : donHang.getItems()) {
    //   item.setDonhang(donHang);
    //   ctdhService.saveCTDH(item);
    // }

    donHangService.saveDonHang(donHang);

    return "Đặt hàng thành công!";
  }

  @PostMapping("/huydon")
  public String cancelOrder(HttpServletRequest request, @RequestParam("madonhang") Integer madonHang) {
    HttpSession session = request.getSession(false);
    String currentUserId = (String) session.getAttribute("username");

    Optional<DonHang> data = donHangService.findDonHangById(madonHang);
    TaiKhoan currentUser = taiKhoanService.getTaiKhoan(currentUserId);

    if (data.isPresent()) {
      DonHang donHang = data.get();

      if (donHang.getTaikhoan().getUsername() == currentUser.getUsername() || currentUser.isAdmin()) {
        donHang.setTinhTrang("Đã hủy");
        donHangService.saveDonHang(donHang);
      }
    }

    return "redirect:/profile/donhang";
  }
}
