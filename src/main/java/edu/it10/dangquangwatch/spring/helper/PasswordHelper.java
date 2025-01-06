package edu.it10.dangquangwatch.spring.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHelper {
  public static boolean isValidPassword(String password) {
    // Kiểm tra độ dài ít nhất 8 ký tự, bao gồm ít nhất một chữ hoa, một chữ thường,
    // một chữ số và một ký tự đặc biệt
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*!]).{8,}$";
    return password.matches(passwordPattern);
  }

  public static String encodePassword(String password) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }
}
