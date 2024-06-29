package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DangkyController {
  @Autowired
  TaiKhoanService taiKhoanService;

  @GetMapping("/dangky")
  public String login(HttpServletRequest request, Model model,
      @RequestParam("error") Optional<String> error) {
    HttpSession session = request.getSession(false);
    String errorMessage = null;
    if (session != null) {
      AuthenticationException ex = (AuthenticationException) session
          .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      if (ex != null) {
        errorMessage = ex.getMessage();
      }
    } else if (error.isPresent()) {
      errorMessage = "Username đã tồn tại!";
    }
    model.addAttribute("errorMessage", errorMessage);
    return "dangky";
  }

  @ExceptionHandler(Exception.class)
  public String handleEmptyOrNullListException() {
    return "redirect:/dangky?error=dupname";
  }

  @PostMapping("/dangky")
  public String postDangky(HttpSession httpSession, Model model, @RequestParam("username") String username,
      @RequestParam("password") String password, @RequestParam("diachi") String diachi,
      @RequestParam("hoten") String hoten) {
    TaiKhoan taiKhoan = new TaiKhoan();

    taiKhoan.setUsername(username);
    taiKhoan.setPassword(password);
    taiKhoan.setDiachi(diachi);
    taiKhoan.setHoten(hoten);
    taiKhoan.setEnabled(1);

    try {
      taiKhoanService.dangKyKhachHang(taiKhoan);
    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/dangky?error=dupname";
    }
    return "redirect:/login";
  }

}
