package edu.it10.dangquangwatch.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import edu.it10.dangquangwatch.spring.AppCustomException.SaveAccountException;
import edu.it10.dangquangwatch.spring.AppCustomException.EmptyOrNullListException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.ApiResponse;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.ChiTietDonHangService;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  public String cart(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");

    TaiKhoan taikhoan = taiKhoanService.getTaiKhoan(username);

    model.addAttribute("taikhoan", taikhoan);
    return "giohang";
  }

  @GetMapping("/donhang")
  public String orders(HttpSession session,
      @RequestParam("search") Optional<String> search,
      @RequestParam("tinhtrang") Optional<String> tinhtrang,
      @RequestParam("thanhtoan") Optional<String> thanhtoan,
      @RequestParam("from") Optional<String> from,
      @RequestParam("to") Optional<String> to,
      @RequestParam("page") Optional<Integer> page, Model model) {
    String fromStr = "2001-01-01";
    String toStr = "3000-01-01";
    if (from.isPresent()) {
      if (!from.get().isEmpty())
        fromStr = from.get();
    }
    if (to.isPresent()) {
      if (!to.get().isEmpty())
        toStr = to.get();
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

    String tinhtrangStr = "";
    if (tinhtrang.isPresent())
      tinhtrangStr = tinhtrang.get();
    String thanhtoanStr = "";
    if (thanhtoan.isPresent())
      thanhtoanStr = thanhtoan.get();
    int pageNum = 0;
    if (page.isPresent())
      pageNum = page.get() - 1;

    String searchStr = "";
    if (search.isPresent())
      searchStr = search.get().trim();

    String username = (String) session.getAttribute("username");

    Page<DonHang> data = donHangService.getMyDonHang(searchStr, tinhtrangStr, thanhtoanStr, username, fromStr, toStr,
        pageNum);

    model.addAttribute("donhangs", data.getContent());
    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("search", searchStr);
    model.addAttribute("tinhtrang", tinhtrangStr);
    model.addAttribute("thanhtoan", thanhtoanStr);
    model.addAttribute("tinhtrang_options", tinhtrang_options);
    model.addAttribute("thanhtoan_options", thanhtoan_options);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("page", pageNum);
    return "donhang";
  }

  @ExceptionHandler(EmptyOrNullListException.class)
  public ResponseEntity<String> handleEmptyOrNullListException(EmptyOrNullListException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @PostMapping("/dathang")
  public ResponseEntity<ApiResponse> placeOrder(HttpServletRequest request, @RequestBody DonHang donHang) {
    HttpSession session = request.getSession(false);
    if (donHang.getItems() == null) {
      ApiResponse response = new ApiResponse(false, "Danh sách sản phẩm trống!");
      return ResponseEntity.ok(response);
    }

    if (donHang.getItems().size() < 1) {
      ApiResponse response = new ApiResponse(false, "Phải có ít nhất 1 sản phẩm!");
      return ResponseEntity.ok(response);
    }

    String username = (String) session.getAttribute("username");

    TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(username);

    if (taiKhoan.getSodienthoai() == null) {
      ApiResponse response = new ApiResponse(false, "Vui lòng thêm số điện thoại cho tài khoản!");
      return ResponseEntity.ok(response);
    }

    if (taiKhoan.getDiachi().equals("Chưa có") || taiKhoan.getDiachi() == null) {
      ApiResponse response = new ApiResponse(false, "Vui lòng địa chỉ cho tài khoản!");
      return ResponseEntity.ok(response);
    }

    donHang.setTinhTrang("Chờ xác nhận");

    if (donHang.getDiaChi() == null || donHang.getDiaChi().isEmpty()) {
      donHang.setDiaChi(taiKhoan.getDiachi());
    }

    if (donHang.getGhiChu() == null || donHang.getGhiChu().isEmpty()) {
      donHang.setGhiChu("Không có");
    }

    if (donHang.getNGAYTHEM() == null) {
      donHang.setNGAYTHEM(Helper.getCurrentDateFormatted());
    }

    donHang.setTaikhoan(taiKhoan);

    donHangService.addDonHang(donHang);

    ApiResponse response = new ApiResponse(true, "Đặt hàng thành công!");
    return ResponseEntity.ok(response);
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
        donHang.setThanhToan("Đã hủy");

        donHangService.addDonHang(donHang);
      }
    }

    return "redirect:/profile/donhang";
  }

  @GetMapping("/doithongtin")
  public String doithongtin(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    var sessionErr = session.getAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name());
    if (sessionErr != null) {
      model.addAttribute("errorMessage", (String) sessionErr);
      session.removeAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name());
    }

    TaiKhoan taikhoan = taiKhoanService.getTaiKhoan(username);

    model.addAttribute("taikhoan", taikhoan);

    String address = taikhoan.getDiachi();
    String province = "";
    String district = "";
    String ward = "";
    String extra = "";
    
    if (taikhoan.getDiachi() != null && !taikhoan.getDiachi().equals("Chưa có")) {
      String[] addressSplit = address.split(", ", 4);
      if (addressSplit.length == 4) {
        province = addressSplit[0];
        district = addressSplit[1];
        ward = addressSplit[2];
        extra = addressSplit[3];
      }
    }
    
    model.addAttribute("province", province);
    model.addAttribute("district", district);
    model.addAttribute("ward", ward);
    model.addAttribute("extra", extra);
    return "doithongtin";
  }

  @PostMapping("/doithongtin")
  public String updateThongTin(TaiKhoan taiKhoan, HttpSession session) {
    String taikhoanUsername = (String) session.getAttribute("username");
    TaiKhoan existingTaiKhoan = taiKhoanService.getTaiKhoan(taikhoanUsername);

    if (taiKhoan.getPassword() == null) {
      taiKhoan.setPassword(existingTaiKhoan.getPassword());
    }

    try {
      taiKhoanService.updateTaiKhoan(taiKhoan, "/profile/doithongtin");
    } catch (SaveAccountException e) {
      e.printStackTrace();
      session.setAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name(), e.getMessage());
      return "redirect:/profile/doithongtin";
    }
    return "redirect:/profile/doithongtin";
  }

  @PostMapping("/doimatkhau")
  public String doimatkhau(@RequestParam("newpassword") String newpassword, @RequestParam("username") String username) {
    taiKhoanService.doiMatKhau(newpassword, username);
    return "redirect:/profile/doithongtin";
  }
}
