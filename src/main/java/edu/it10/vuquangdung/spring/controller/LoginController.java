package edu.it10.vuquangdung.spring.controller;

import java.util.Optional;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
  @GetMapping("/dangnhap")
  public String redirectToLogin() {
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login(HttpServletRequest request, @RequestParam("regsuccess") Optional<String> regSuccess,Model model) {
    HttpSession session = request.getSession(false);
    String errorMessage = null;
    if (session != null) {
      AuthenticationException ex = (AuthenticationException) session
          .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      if (ex != null) {
        switch (ex.getMessage()) {
          case "User is disabled":
            errorMessage = "Vui lòng xác thực email!";
            break;
          case "Bad credentials":
            errorMessage = "Sai email hoặc mật khẩu!";
            break;
          default:
            errorMessage = ex.getMessage();
            break;
        } ex.getMessage();
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      }
    }
    if (regSuccess.isPresent()) {
      model.addAttribute("notification", "Tài khoản đăng ký thành công. Vui lòng xác thực qua email trước khi đăng nhập!");
    }
    model.addAttribute("errorMessage", errorMessage);
    return "login";
  }

  @PostMapping("/login")
  public String postLogin() {
    return "redirect:/";
  }

  @GetMapping("/logout-success")
  public String logout() {
    return "redirect:/";
  }
}
