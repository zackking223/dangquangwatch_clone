package edu.it10.dangquangwatch.spring.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.it10.dangquangwatch.spring.configuration.VNPayConfig;
import edu.it10.dangquangwatch.spring.entity.request.CheckoutRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vnpay")
@RequiredArgsConstructor
public class VNPayController {
  private final VNPayConfig vnpayConfig;

  @PostMapping("/create-payment")
  public ResponseEntity<String> createPayment(
      @RequestBody CheckoutRequest request,
      HttpServletRequest servletRequest) {
    try {
      // Lấy địa chỉ IP từ HttpServletRequest
      String clientIp = getClientIp(servletRequest);

      // Xử lý tạo thanh toán
      Map<String, String> vnpParams = new HashMap<>();
      vnpParams.put("vnp_Version", "2.1.0");
      vnpParams.put("vnp_Command", "pay");
      vnpParams.put("vnp_TmnCode", vnpayConfig.getTmnCode());
      // VNPay yêu cầu số tiền phải nhân 100
      vnpParams.put("vnp_Amount", String.valueOf(request.getDonHang().getTongTien() * 100)); 
      vnpParams.put("vnp_CurrCode", "VND");
      vnpParams.put("vnp_TxnRef", String.valueOf(System.currentTimeMillis())); // Mã đơn hàng
      vnpParams.put("vnp_OrderInfo", request.getDonHang().toString());
      vnpParams.put("vnp_Locale", "vn");
      vnpParams.put("vnp_ReturnUrl", vnpayConfig.getReturnUrl());
      vnpParams.put("vnp_IpAddr", clientIp);
      vnpParams.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

      // Tạo chữ ký (checksum)
      String query = buildQuery(vnpParams);
      String secureHash = hmacSHA512(vnpayConfig.getHashSecret(), query);
      vnpParams.put("vnp_SecureHash", secureHash);

      // Tạo URL thanh toán
      String paymentUrl = vnpayConfig.getVnpUrl() + "?" + buildQuery(vnpParams);
      return ResponseEntity.ok(paymentUrl);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
  }

  private String buildQuery(Map<String, String> params) {
    return params.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(
            (Map.Entry<String, String> e) -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
        .collect(Collectors.joining("&"));
  }

  private String hmacSHA512(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmac = Mac.getInstance("HmacSHA512");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
    hmac.init(secretKey);
    byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    return bytesToHex(hash); // Tự viết hàm bytesToHex
  }

  private String bytesToHex(byte[] bytes) {
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
  private String getClientIp(HttpServletRequest request) {
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
    return ip;
  }

  @GetMapping("/return")
  public ResponseEntity<String> vnpayReturn(@RequestParam Map<String, String> allParams) {
    try {
      // Lấy tất cả các tham số từ VNPay gửi về
      Map<String, String> vnpParams = new HashMap<>(allParams);

      // Lấy chữ ký từ VNPay
      String vnpSecureHash = vnpParams.remove("vnp_SecureHash");

      // Tạo lại chữ ký từ dữ liệu nhận được
      String query = buildQuery(vnpParams);
      String generatedHash = hmacSHA512(vnpayConfig.getHashSecret(), query);

      // So sánh chữ ký để đảm bảo tính toàn vẹn
      if (!generatedHash.equals(vnpSecureHash)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
      }

      // Lấy mã đơn hàng từ `vnp_OrderInfo`
      String orderInfo = vnpParams.get("vnp_OrderInfo");
      if (orderInfo == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order information not found");
      }

      // Kiểm tra mã phản hồi (vnp_ResponseCode)
      String responseCode = vnpParams.get("vnp_ResponseCode");
      if ("00".equals(responseCode)) {
        // Thanh toán thành công
        return ResponseEntity.ok("Payment successful!");
      } else {
        // Thanh toán thất bại
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed: " + responseCode);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
  }
}
