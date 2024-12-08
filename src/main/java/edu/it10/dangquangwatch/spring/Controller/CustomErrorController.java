package edu.it10.dangquangwatch.spring.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(
    HttpServletRequest request, 
    Model model, 
    RedirectAttributes redirectAttributes
  ) {
    Object status = request.getAttribute("javax.servlet.error.status_code");

    // Thêm thông tin trạng thái lỗi vào model
    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());
      model.addAttribute("statusCode", statusCode);

      switch (statusCode) {
        case 404:
          model.addAttribute("errorMessage", "Trang không tồn tại!");
          break;
        case 500:
          model.addAttribute("errorMessage", "Lỗi hệ thống! Vui lòng thử lại sau.");
          break;
        default:
          model.addAttribute("errorMessage", "Đã xảy ra lỗi! Mã lỗi: " + statusCode);
      }
    } else {
      // Kiểm tra flashAttributes
      if (redirectAttributes.getFlashAttributes().containsKey("errorMessage")) {
        model.addAttribute("errorMessage", redirectAttributes.getFlashAttributes().get("errorMessage"));
      } else {
        model.addAttribute("errorMessage", "Đường dẫn không tồn tại!");
      }
    }

    return "error/index";
  }
}
