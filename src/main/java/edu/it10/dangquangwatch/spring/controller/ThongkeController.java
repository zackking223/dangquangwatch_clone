package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
import jakarta.servlet.http.HttpSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
  public String trangThongKe(
    HttpSession session, 
    Model model,
    @RequestParam("editcapital") Optional<String> editcapital_opt,
    @RequestParam("time") Optional<String> time
    ) {
    String role = (String) session.getAttribute("role");

    if (!role.equals("ROLE_QUANLY")) {
      return "redirect:/admin/donhang/";
    }

    ThongKe thongKe;
    
    List<ThongKe> thongKes = thongKeService.getAllThongKe();
    if (time.isPresent() && !time.get().isEmpty()) {
      thongKe = thongKes.stream()
                        .filter(tk -> tk.getMathongke().equals(time.get()))
                        .findFirst()
                        .orElse(thongKeService.getCurrent());
  } else {
      thongKe = thongKes.get(0);
  }
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

  @GetMapping("/admin/thongke/export")
  public ResponseEntity<byte[]> exportThongKeToExcel() throws IOException {
    // Lấy danh sách ThongKe từ service
    List<ThongKe> thongKeList = thongKeService.getAllThongKe();

    // Tạo file Excel
    ByteArrayInputStream in = thongKeService.exportThongKeToExcel(thongKeList);

    // Cài đặt headers cho response
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=thongke.xlsx");

    return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(in.readAllBytes());
  }
}