package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.ApiResponse;
import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class ThongkeController {
  @Autowired
  private ThongKeService thongKeService;

  @GetMapping("/admin/")
  public String adminIndex(Model model, @RequestParam("editcapital") Optional<String> editcapital_opt) {
    return trangThongKe(model, editcapital_opt);
  }

  @GetMapping("/admin/thongke/")
  public String trangThongKe(Model model, @RequestParam("editcapital") Optional<String> editcapital_opt) {
    ThongKe thongKe = thongKeService.getAllThongKe().getFirst();
    boolean editcapital = editcapital_opt.isPresent();

    Float newTiLe = thongKe.getTiLeChuyenDoi();

    thongKeService.updateTiLeChuyenDoi(newTiLe);
    thongKe.setTiLeChuyenDoi(newTiLe);

    model.addAttribute("thongKe", thongKe);
    model.addAttribute("editcapital", editcapital);
    return "admin/thongke/index";
  }

  @GetMapping("/admin/thongke/updatecapital")
  public String updateCapital(@RequestParam("capital") Integer capital) {
    thongKeService.updateVon(capital);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/admin/thongke/deccapital")
  public String decCapital(@RequestParam("amount") Integer amount) {
    thongKeService.decVon(amount);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/admin/thongke/inccapital")
  public String incCapital(@RequestParam("amount") Integer amount) {
    thongKeService.incVon(amount);

    return "redirect:/admin/thongke/?editcapital=true";
  }

  @GetMapping("/tangluotxemsanpham")
  public ResponseEntity<ApiResponse> tangLuotXemSanPham(HttpSession session) {
    if (session.getAttribute("username") != null) {
      ApiResponse response = new ApiResponse(true, "Lượt xem tăng thành công");

      thongKeService.tangluotxemsanpham();

      return ResponseEntity.ok(response);
    }

    ApiResponse response = new ApiResponse(false, "Vui lòng đăng nhập trước");

    return ResponseEntity.ok(response);
  }

  @GetMapping("/tangluotthemgiohang")
  public ResponseEntity<ApiResponse> tangLuotThemGioHang(HttpSession session) {
    if (session.getAttribute("username") != null) {
      ApiResponse response = new ApiResponse(true, "Lượt xem tăng thành công");

      thongKeService.tangluotthemgiohang();

      return ResponseEntity.ok(response);
    }

    ApiResponse response = new ApiResponse(false, "Vui lòng đăng nhập trước");

    return ResponseEntity.ok(response);
  }
}