package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.it10.dangquangwatch.spring.AppCustomException.DuplicateEntryException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DangkyController {
  @Autowired
  TaiKhoanService taiKhoanService;

  @GetMapping("/register")
  public String redirectToLogin() {
    return "redirect:/dangky";
  }

  @GetMapping("/dangky")
  public String register(HttpSession session, Model model) {
    String errorMessage = null;
    if (session != null) {
      AuthenticationException ex = (AuthenticationException) session
          .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      if (ex != null) {
        errorMessage = ex.getMessage();
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      } else {
        var temp = session.getAttribute(ErrorEnum.REGISTER_ERROR.name());
        if (temp != null) {
          errorMessage = (String) temp;
          session.removeAttribute(ErrorEnum.REGISTER_ERROR.name());
        }
      }
    } 
    model.addAttribute("errorMessage", errorMessage);
    return "dangky";
  }

  @PostMapping("/dangky")
  public String postDangky(HttpSession session, @RequestParam("username") String username,
      @RequestParam("password") String password, @RequestParam("diachi") String diachi,
      @RequestParam("hoten") String hoten, @RequestParam("sodienthoai") String sodienthoai,
      @RequestParam("agree") Optional<String> agree) {

    if (!agree.isPresent()) {
      session.setAttribute(ErrorEnum.REGISTER_ERROR.name(), "Bạn phải đồng ý điều khoản!");
      return "dangky";
    }

    TaiKhoan taiKhoan = new TaiKhoan();

    taiKhoan.setUsername(username);
    taiKhoan.setPassword(password);
    taiKhoan.setSodienthoai(sodienthoai);
    taiKhoan.setDiachi(diachi);
    taiKhoan.setHoten(hoten);

    // Can xac thuc tai khoan qua email de duoc enable
    taiKhoan.setEnabled(0);

    try {
      taiKhoanService.dangKyKhachHang(taiKhoan);
    } catch (DuplicateEntryException e) {
      e.printStackTrace();
      session.setAttribute(ErrorEnum.REGISTER_ERROR.name(), e.getMessage());
      return "redirect:/dangky";
    }
    return "redirect:/login?regsuccess";
  }
}
