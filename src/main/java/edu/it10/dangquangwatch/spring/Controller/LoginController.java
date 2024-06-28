package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
  @Autowired
  TaiKhoanService taiKhoanService;

  @GetMapping("/login")
  public String login(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    String errorMessage = null;
    if (session != null) {
      AuthenticationException ex = (AuthenticationException) session
          .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      if (ex != null) {
        errorMessage = ex.getMessage();
      }
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
