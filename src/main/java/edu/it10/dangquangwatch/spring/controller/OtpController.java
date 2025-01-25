package edu.it10.dangquangwatch.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.it10.dangquangwatch.spring.AppCustomException.OtpException;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.service.OtpService;

@Controller
@RequestMapping("/otp")
public class OtpController {
  @Autowired
  OtpService otpService;

  @GetMapping("/va")
  public String verifyAccount(
      RedirectAttributes redirectAttributes,
      @RequestParam("e") Optional<String> email,
      @RequestParam("p") Optional<String> otp) {

    if (!email.isPresent() || !otp.isPresent()) {
      throw new OtpException("Link xác thực không hợp lệ!");
    }

    otpService.verifyAccount(email.get(), otp.get());
    redirectAttributes.addAttribute("notification", "Xác thực tài khoản thành công!");
    return "redirect:/login";
  }

  @GetMapping("/vp")
  public String verifyPassword(
      RedirectAttributes redirectAttributes,
      @RequestParam("e") Optional<String> email,
      @RequestParam("p") Optional<String> otp) {

    if (!email.isPresent() || !otp.isPresent()) {
      throw new OtpException("Link xác thực không hợp lệ!");
    }

    otpService.changePassword(email.get(), otp.get());
    redirectAttributes.addAttribute("notification", "Đổi mật khẩu thành công!");
    return "redirect:/profile/doithongtin";
  }

  @GetMapping("/vn")
  public ResponseEntity<ApiResponse> verifyPhoneNumber(
      RedirectAttributes redirectAttributes,
      @RequestParam("e") Optional<String> email,
      @RequestParam("p") Optional<String> otp,
      @RequestParam("n") Optional<String> newphonenumber) {

    if (!email.isPresent() || !otp.isPresent() || !newphonenumber.isPresent()) {
      throw new OtpException("Mã OTP không hợp lệ!", "/profile/phoneotp", true);
    }

    otpService.changePhoneNumber(email.get(), otp.get(), newphonenumber.get());
    redirectAttributes.addAttribute("notification", "Đổi số điện thoại thành công!");
    return ResponseEntity.ok(new ApiResponse(true, "Đổi số điện thoại thành công!"));
  }
}
