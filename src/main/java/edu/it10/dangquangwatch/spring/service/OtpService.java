package edu.it10.dangquangwatch.spring.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanPasswordManager;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanPhoneManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.OtpException;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Otp;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.entity.enumeration.OtpAction;
import edu.it10.dangquangwatch.spring.repository.OtpRepository;

@Service
public class OtpService {
  private final OtpRepository otpRepository;
  private final TaiKhoanManager taiKhoanManager;
  private final TaiKhoanPasswordManager taiKhoanPasswordManager;
  private final TaiKhoanPhoneManager taiKhoanPhoneManager;
  private final EmailService emailService;
  private final SmsService smsService;

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public OtpService(
          OtpRepository otpRepository,
          TaiKhoanManager taiKhoanManager,
          TaiKhoanPasswordManager taiKhoanPasswordManager,
          TaiKhoanPhoneManager taiKhoanPhoneManager,
          EmailService emailService,
          SmsService smsService) {
    this.otpRepository = otpRepository;
    this.taiKhoanManager = taiKhoanManager;
      this.taiKhoanPasswordManager = taiKhoanPasswordManager;
      this.taiKhoanPhoneManager = taiKhoanPhoneManager;
      this.emailService = emailService;
    this.smsService = smsService;
  }

  public void verifyAccount(String email, String password) {
    Optional<Otp> otpOptional = otpRepository.findByEmailAndPasswordAndAction(email, password,
        OtpAction.VerifyAccount.getValue());
    if (otpOptional.isPresent()) {
      Otp otp = otpOptional.get();
      if (otp.isExpired()) {
        otpRepository.delete(otp);
        throw new OtpException("Mã OTP đã hết hạn!");
      } else {
        taiKhoanManager.activate(email);
        otpRepository.delete(otp);
      }
    } else {
      throw new OtpException("Mã OTP không hợp lệ!");
    }
  }

  public void changePassword(String email, String password) {
    Optional<Otp> otpOptional = otpRepository.findByEmailAndPasswordAndAction(email, password,
        OtpAction.ChangePassword.getValue());
    if (otpOptional.isPresent()) {
      Otp otp = otpOptional.get();
      if (otp.isExpired()) {
        otpRepository.delete(otp);
        throw new OtpException("Mã OTP đã hết hạn!");
      } else {
        taiKhoanPasswordManager.doiMatKhauHashed(otp.getPayload(), email, "/profile/doithongtin");
        otpRepository.delete(otp);
      }
    } else {
      throw new OtpException("Mã OTP không hợp lệ!");
    }
  }

  public void changePhoneNumber(String email, String password, String payload) {
    Optional<Otp> otpOptional = otpRepository
      .findByEmailAndPasswordAndActionAndPayload(
        email, 
        password,
        OtpAction.VerifyPhone.getValue(), 
        payload);
    if (otpOptional.isPresent()) {
      Otp otp = otpOptional.get();
      if (otp.isExpired()) {
        otpRepository.delete(otp);
        throw new OtpException("Mã OTP đã hết hạn!", "/profile/phoneotp", true);
      } else {
        taiKhoanPhoneManager.doiSoDienThoai(payload, email, "/profile/phoneotp");
        otpRepository.delete(otp);
      }
    } else {
      throw new OtpException("Mã OTP không hợp lệ!", "/profile/phoneotp", true);
    }
  }

  public void createVerifyAccountUrl(String email) {
    Otp otp = new Otp();
    TaiKhoan taiKhoan = taiKhoanManager.getTaiKhoan(email);
    String password = Base64.getUrlEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
    otp.setEmail(email);
    otp.setPassword(password);
    otp.setAction(OtpAction.VerifyAccount);
    otp.setPayload("Enable account");
    String expiryDate = getExpiryDateTime(7);
    otp.setExpiryDate(expiryDate);
    otpRepository.save(otp);

    String verifyUrl = "http://localhost:8080/otp/va?p=" + password + "&e=" + email;
    try {
      emailService.sendActivateAccountEmail(
          taiKhoan.getHoten(),
          verifyUrl,
          email,
          expiryDate);
    } catch (Exception e) {
      throw new ServiceException("Không thể gửi email xác nhận tài khoản!");
    }
  }

  public void createChangePasswordUrl(String email, String plainText) {
    Otp otp = new Otp();
    TaiKhoan taiKhoan = taiKhoanManager.getTaiKhoan(email);
    String password = Base64.getUrlEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
    otp.setEmail(email);
    otp.setPassword(password);
    otp.setAction(OtpAction.ChangePassword);
    otp.setPayload(passwordEncoder.encode(plainText));
    String expiryDate = getExpiryDateTime(7);
    otp.setExpiryDate(expiryDate);
    otpRepository.save(otp);

    String verifyUrl = "http://localhost:8080/otp/vp?p=" + password + "&e=" + email;
    try {
      emailService.sendChangePasswordEmail(
          taiKhoan.getHoten(),
          verifyUrl,
          email,
          expiryDate);
    } catch (Exception e) {
      throw new ServiceException("Không thể gửi email xác nhận đổi mật khẩu!");
    }
  }

  public void createVerifyPhoneNumberCode(String email, String newNumber) {
    Otp otp = new Otp();
    SecureRandom random = new SecureRandom();
    String password = String.valueOf(100000 + random.nextInt(900000)); // Random 6 digits number
    otp.setEmail(email);
    otp.setPassword(password);
    otp.setAction(OtpAction.VerifyPhone);
    otp.setPayload(newNumber);

    String expiryDate = getExpiryDateTimeInMinutes(15);
    otp.setExpiryDate(expiryDate);
    otpRepository.save(otp);

    smsService.sendConfirmNumberSms(newNumber, password);
  }

  public String getExpiryDateTime(int days) {
    // Tính ngày và giờ hết hạn (7 ngày từ bây giờ)
    LocalDateTime expiryDateTime = LocalDateTime.now().plusDays(days);
      return expiryDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public String getExpiryDateTimeInMinutes(int minutes) {
    // Tính thời gian hết hạn (N phút từ bây giờ)
    LocalDateTime expiryDateTime = LocalDateTime.now().plusMinutes(minutes);
      return expiryDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
