package edu.it10.dangquangwatch.spring.helper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import jakarta.servlet.http.HttpServletRequest;

public class VNPayHelper {
  public static String getFormattedDate(Integer extraMinutes) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    // Đặt múi giờ GMT+7
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date()); // Lấy thời gian hiện tại
    if (extraMinutes > 0) {
      calendar.add(Calendar.MINUTE, extraMinutes); // Thêm số phút
    }
    String vnpExpireDate = dateFormat.format(calendar.getTime()); // Tạo chuỗi định dạng
    return vnpExpireDate;
  }

  public static String buildQuery(Map<String, String> params) {
    return params.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(
            (Map.Entry<String, String> e) -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
        .collect(Collectors.joining("&"));
  }

  public static String hmacSHA512(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmac = Mac.getInstance("HmacSHA512");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
    hmac.init(secretKey);
    byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    return bytesToHex(hash); // Tự viết hàm bytesToHex
  }

  public static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      String hex = Integer.toHexString(0xff & b); // Chuyển byte thành giá trị hexa
      if (hex.length() == 1) {
        hexString.append('0'); // Thêm số 0 nếu cần
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  // Hàm lấy địa chỉ IP của client
  public static String getClientIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    if ("0:0:0:0:0:0:0:1".equals(ip)) {
      ip = "127.0.0.1";
    }
    return ip;
  }
}
