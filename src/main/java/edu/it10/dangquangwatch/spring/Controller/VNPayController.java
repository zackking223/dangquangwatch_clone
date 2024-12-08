package edu.it10.dangquangwatch.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.it10.dangquangwatch.spring.configuration.VNPayConfig;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderPaymentStatus;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderStatus;
import edu.it10.dangquangwatch.spring.entity.request.CheckoutRequest;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.helper.VNPayHelper;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/vnpay")
public class VNPayController {
  @Autowired
  private VNPayConfig vnpayConfig;
  @Autowired
  private DonHangService donHangService;

  @PostMapping("/create-payment")
  public ResponseEntity<ApiResponse> createPayment(
      @RequestBody CheckoutRequest request,
      HttpServletRequest servletRequest) {
    try {
      DonHang donHang = donHangService.save(request.getDonHang(), OrderStatus.WaitForPayment, OrderPaymentStatus.PENDING);
      Map<String, String> vnpParams = buildVNPayParams(donHang, servletRequest);

      // Tạo chữ ký (checksum)
      String query = VNPayHelper.buildQuery(vnpParams);
      String secureHash = VNPayHelper.hmacSHA512(vnpayConfig.getHashSecret(), query);
      vnpParams.put("vnp_SecureHash", secureHash);

      // Tạo URL thanh toán
      String paymentUrl = vnpayConfig.getVnpUrl() + "?" + VNPayHelper.buildQuery(vnpParams);
      return ResponseEntity.ok(new ApiResponse(true, paymentUrl));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse(false, "Error: " + e.getMessage()));
    }
  }

  @PostMapping("/continue-payment")
  public ResponseEntity<ApiResponse> continuePayment(
      @RequestBody Integer id,
      HttpServletRequest servletRequest) {
    try {
      Optional<DonHang> opt = donHangService.findDonHangById(id);
      if (opt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse(false, "Không tìm thấy đơn hàng!"));
      }

      DonHang donHang = opt.get();
      Map<String, String> vnpParams = buildVNPayParams(donHang, servletRequest);

      // Tạo chữ ký (checksum)
      String query = VNPayHelper.buildQuery(vnpParams);
      String secureHash = VNPayHelper.hmacSHA512(vnpayConfig.getHashSecret(), query);
      vnpParams.put("vnp_SecureHash", secureHash);

      // Tạo URL thanh toán
      String paymentUrl = vnpayConfig.getVnpUrl() + "?" + VNPayHelper.buildQuery(vnpParams);
      return ResponseEntity.ok(new ApiResponse(true, paymentUrl));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse(false, "Error: " + e.getMessage()));
    }
  }

  Map<String, String> buildVNPayParams(DonHang donHang, HttpServletRequest servletRequest) {
    Map<String, String> vnpParams = new HashMap<>();
    // Lấy địa chỉ IP từ HttpServletRequest
    String clientIp = VNPayHelper.getClientIp(servletRequest);
    // Xử lý tạo thanh toán
    vnpParams.put("vnp_Amount", String.valueOf(donHang.getTongTien() * 100)); // VNPay yêu cầu số tiền phải nhân 100
    vnpParams.put("vnp_Command", "pay");
    vnpParams.put("vnp_CreateDate", VNPayHelper.getFormattedDate(0));
    vnpParams.put("vnp_CurrCode", "VND");
    vnpParams.put("vnp_IpAddr", clientIp);
    vnpParams.put("vnp_Locale", "vn");
    vnpParams.put("vnp_OrderInfo", "Thanh toan don hang ma: " + donHang.getMaDonHang());
    vnpParams.put("vnp_OrderType", "400000"); // 400000 - Mua hàng hóa
    vnpParams.put("vnp_ReturnUrl", vnpayConfig.getReturnUrl());
    vnpParams.put("vnp_TmnCode", vnpayConfig.getTmnCode());
    vnpParams.put("vnp_TxnRef", "dqwo_" + donHang.getMaDonHang() + "_" + System.currentTimeMillis());
    vnpParams.put("vnp_Version", "2.1.0");
    vnpParams.put("vnp_ExpireDate", VNPayHelper.getFormattedDate(15));
    // Phải là một domain thật trên mạng
    // vnpParams.put("vnp_UrlNotify", "https://a-real-domain.com/api/vnpay/notify");
    return vnpParams;
  }

  @GetMapping("/return")
  public String vnpayReturn(
    @RequestParam Map<String, String> params,
    RedirectAttributes redirectAttributes
  ) {
    try {
      // Lấy chữ ký từ VNPay
      String vnpSecureHash = params.remove("vnp_SecureHash");

      // Tạo lại chữ ký từ dữ liệu nhận được
      String query = VNPayHelper.buildQuery(params);
      String generatedHash = VNPayHelper.hmacSHA512(vnpayConfig.getHashSecret(), query);

      // So sánh chữ ký để đảm bảo tính toàn vẹn
      if (!generatedHash.equals(vnpSecureHash)) {
        redirectAttributes.addFlashAttribute("errorMessage", "Chữ ký không hợp lệ!");
        return "redirect:/error";
      }

      // Lấy mã đơn hàng từ `vnp_OrderInfo`
      String orderInfo = params.get("vnp_OrderInfo");
      if (orderInfo == null) {
        redirectAttributes.addFlashAttribute("errorMessage", "Không thấy thông tin đơn hàng!");
        return "redirect:/error";
      }

      // Kiểm tra mã phản hồi (vnp_ResponseCode)
      String responseCode = params.get("vnp_ResponseCode");
      String maDonHang = params.get("vnp_OrderInfo").split(": ")[1];
      if ("00".equals(responseCode)) {
        // Thanh toán thành công
        donHangService.updateStatus(Integer.parseInt(maDonHang), OrderStatus.COMPLETED, OrderPaymentStatus.PAID);
        redirectAttributes.addFlashAttribute("notification", "Thanh toán thành công!");
        return "redirect:/profile/giohang";
      } else {
        // Thanh toán thất bại
        donHangService.updateStatus(Integer.parseInt(maDonHang), OrderStatus.WaitForPayment, OrderPaymentStatus.FAILED);
        redirectAttributes.addFlashAttribute("errorMesssage", "Thanh toán thất bại, mã lỗi: " + responseCode);
        return "redirect:/profile/giohang";
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
      return "redirect:/error";
    }
  }

  @PostMapping("/notify")
  public ResponseEntity<String> vnpayNotify(@RequestParam Map<String, String> params) {
    String maDonHang = params.get("vnp_TxnRef");
    String responseCode = params.get("vnp_ResponseCode");
    if (!"00".equals(responseCode)) { // Thanh toán thất bại
      donHangService.updateStatus(Integer.parseInt(maDonHang), OrderStatus.CANCELLED, OrderPaymentStatus.CANCELLED);
    }

    return ResponseEntity.ok("Notification received");
  }
}
