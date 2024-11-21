package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class ThongkeController {
  @Autowired
  private ThongKeService thongKeService;

  @GetMapping("/admin/")
  public String adminIndex() {
    return "redirect:/admin/donhang/";
  }

  @GetMapping("/admin/thongke/")
  public String trangThongKe(HttpSession session, Model model, @RequestParam("editcapital") Optional<String> editcapital_opt) {
    String role = (String) session.getAttribute("role");

    if (!role.equals("ROLE_QUANLY")) {
      return "redirect:/admin/donhang/";
    }  

    List<ThongKe> thongKes = thongKeService.getAllThongKe();
    ThongKe thongKe = thongKes.getFirst();
    boolean editcapital = editcapital_opt.isPresent();

    Float newTiLe = thongKe.tinhTile();

    thongKeService.updateTiLeChuyenDoi(newTiLe);
    thongKe.setTiLeChuyenDoi(newTiLe);

    model.addAttribute("thongKe", thongKe);
    model.addAttribute("editcapital", editcapital);
    model.addAttribute("thongKes", thongKes);

    return "admin/thongke/index";
  }

  @GetMapping("/admin/thongke/updatecapital")
  public String updateCapital(@RequestParam("capital") BigDecimal capital) {
    thongKeService.updateVon(capital);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/admin/thongke/deccapital")
  public String decCapital(@RequestParam("amount") BigDecimal amount) {
    thongKeService.decVon(amount);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/admin/thongke/inccapital")
  public String incCapital(@RequestParam("amount") BigDecimal amount) {
    thongKeService.incVon(amount);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/tangluotxemsanpham")
  public ResponseEntity<ApiResponse> tangLuotXemSanPham(HttpSession session) {
    if (session.getAttribute("username") != null) {
      ApiResponse response = new ApiResponse(true, "Lượt xem tăng thành công");

      thongKeService.incLuotXemSanPham();

      return ResponseEntity.ok(response);
    }

    ApiResponse response = new ApiResponse(false, "Vui lòng đăng nhập trước");

    return ResponseEntity.ok(response);
  }

  @GetMapping("/tangluotthemgiohang")
  public ResponseEntity<ApiResponse> tangLuotThemGioHang(HttpSession session) {
    if (session.getAttribute("username") != null) {
      ApiResponse response = new ApiResponse(true, "Lượt xem tăng thành công");

      thongKeService.incLuotThemGioHang();

      return ResponseEntity.ok(response);
    }

    ApiResponse response = new ApiResponse(false, "Vui lòng đăng nhập trước");

    return ResponseEntity.ok(response);
  }
}