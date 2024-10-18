package edu.it10.dangquangwatch.spring.service.impl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.SaveAccountException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.controller.Helper;
import edu.it10.dangquangwatch.spring.entity.Otp;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.repository.OtpRepository;
import edu.it10.dangquangwatch.spring.repository.TaiKhoanRepository;
import edu.it10.dangquangwatch.spring.service.EmailService;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaiKhoanServiceImpl implements TaiKhoanService {
  @Autowired
  private TaiKhoanRepository taiKhoanRepository;
  @Autowired
  private OtpRepository otpRepository;
  @Autowired
  private EmailService emailService;

  private EntityManager entityManager;
  private PasswordEncoder passwordEncoder;

  public TaiKhoanServiceImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
    passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public TaiKhoan dangNhap(String username, String password) {
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan WHERE username = :data", TaiKhoan.class);
    query.setParameter("data", username);

    TaiKhoan result = query.getSingleResult();

    if (result != null) {
      if (BCrypt.checkpw(password, result.getPassword())) {
        return result;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  public Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page) {
    TypedQuery<TaiKhoan> query = entityManager.createQuery(
        "SELECT tk FROM TaiKhoan tk WHERE tk.NGAYTHEM >= \'" + from + "\' AND tk.NGAYTHEM <= '" + to + "\'",
        TaiKhoan.class);
    List<TaiKhoan> result = query.getResultList();
    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  @Override
  public Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page) {
    TypedQuery<TaiKhoan> query = entityManager.createQuery(
        "SELECT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_QUANTRI' and tk.NGAYTHEM >= \'" + from
            + "\' AND tk.NGAYTHEM <= \'" + to + "\'",
        TaiKhoan.class);

    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  @Override
  public Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page) {
    TypedQuery<TaiKhoan> query = entityManager.createQuery(
        "SELECT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and tk.NGAYTHEM >= \'" + from
            + "\' AND tk.NGAYTHEM <= \'" + to + "\'",
        TaiKhoan.class);

    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  boolean accountExisted(String username) {
    return taiKhoanRepository.findById(username).isPresent();
  }

  boolean numberExisted(String sodienthoai) {
    return taiKhoanRepository.findBySodienthoai(sodienthoai).size() > 0;
  }

  @Override
  @Transactional
  public void dangKyKhachHang(TaiKhoan taiKhoan, String path) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    taiKhoan.setNGAYTHEM(Helper.getCurrentDateFormatted());
    taiKhoan.setLoai_tai_khoan("ROLE_KHACHHANG");
    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

    if (accountExisted(taiKhoan.getUsername())) {
      String message = "Email đã tồn tại!";
      SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
      exception.setPath(path);
      exception.setTaiKhoan(taiKhoan);
      throw exception;
    }

    if (taiKhoan.getSodienthoai() != null) {
      if (numberExisted(taiKhoan.getSodienthoai())) {
        String message = "Số điện thoại đã tồn tại!";
        SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
        exception.setPath(path);
        exception.setTaiKhoan(taiKhoan);
        throw exception;
      }
    }

    entityManager.persist(taiKhoan);
    if (taiKhoan.getEnabled() == 0) {
      try {
        emailService.sendConfirmationEmail(
            taiKhoan.getHoten(),
            createAuthUrl(taiKhoan.getUsername()),
            taiKhoan.getUsername(),
            getExpiryDate());
      } catch (MessagingException e) {
        log.warn("WARNING - Cannot send email to {}", taiKhoan.getUsername());
      }
    }
  }

  String createAuthUrl(String email) {
    Otp otp = new Otp();
    String password = Base64.getUrlEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
    otp.setEmail(email);
    otp.setPassword(password);

    // Gán expiryDate vào đối tượng Otp
    otp.setExpiryDate(getExpiryDate());
    otpRepository.save(otp);

    return "http://localhost:8080/xacthuc?otp=" + password + "&email=" + email;
  }

  String getExpiryDate() {
    // Tính ngày hết hạn (7 ngày từ hôm nay)
    LocalDate expiryDate = LocalDate.now().plusDays(7);
    String formattedExpiryDate = expiryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    return formattedExpiryDate;
  }

  @Override
  @Transactional
  public void dangKyQuanTri(TaiKhoan taiKhoan) throws SaveAccountException {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    taiKhoan.setNGAYTHEM(Helper.getCurrentDateFormatted());
    taiKhoan.setLoai_tai_khoan("ROLE_QUANTRI");
    taiKhoan.setDiachi("QUANTRI ko can dia chi");
    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

    if (accountExisted(taiKhoan.getUsername())) {
      String message = "Email đã tồn tại!";
      SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
      exception.setPath("/admin/accounts/add");
      exception.setTaiKhoan(taiKhoan);
      throw exception;
    }

    if (taiKhoan.getSodienthoai() != null) {
      if (numberExisted(taiKhoan.getSodienthoai())) {
        String message = "Số điện thoại đã tồn tại!";
        SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
        exception.setPath("/admin/accounts/add");
        exception.setTaiKhoan(taiKhoan);
        throw exception;
      }
    }
    entityManager.persist(taiKhoan);
  }

  @Override
  @Transactional
  public void updateTaiKhoan(TaiKhoan taiKhoan, String path) {
    String plainText = taiKhoan.getPassword();
    TaiKhoan original = taiKhoanRepository.findById(taiKhoan.getUsername())
      .orElseThrow(() -> new RuntimeException("Không thể tìm thấy người dùng với email: " + taiKhoan.getHoten()));
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    
    if (plainText != null) {
      taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));
    }

    if (!taiKhoan.getSodienthoai().equals(original.getSodienthoai())) {
      if (numberExisted(taiKhoan.getSodienthoai())) {
        String message = "Số điện thoại đã tồn tại!";
        SaveAccountException exception = new SaveAccountException(message, ErrorEnum.UPDATE_PROFILE_ERROR);
        exception.setPath(path);
        exception.setTaiKhoan(taiKhoan);
        throw exception;
      }
    }
    entityManager.merge(taiKhoan);
  }

  @Override
  public TaiKhoan getTaiKhoan(String username) {
    return entityManager.find(TaiKhoan.class, username);
  }

  @Override
  public Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page) {
    username = "%" + username + "%";
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan WHERE username LIKE :data", TaiKhoan.class);
    query.setParameter("data", username);
    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  @Override
  @Transactional
  public void deleteTaiKhoanByUsername(String username) {
    TaiKhoan target = entityManager.find(TaiKhoan.class, username);

    entityManager.remove(target);
  }

  private static String removeWhitespace(String input) {
    if (input == null) {
      return null;
    }
    return input.replaceAll("\\s+", "");
  }

  @Override
  public Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page) {
    username = "%" + username + "%";
    TypedQuery<TaiKhoan> query = entityManager.createQuery(
        "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_QUANTRI' and tk.username LIKE :username and tk.NGAYTHEM >= \'"
            + from
            + "\' AND tk.NGAYTHEM <= \'" + to + "\'",
        TaiKhoan.class);
    query.setParameter("username", username);
    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  @Override
  public Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page) {
    searchStr = "%" + searchStr + "%";
    TypedQuery<TaiKhoan> query = entityManager.createQuery(
        "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and (tk.hoten LIKE :searchStr OR tk.diachi LIKE :searchStr OR tk.username LIKE :searchStr OR tk.sodienthoai LIKE :searchStr) and tk.NGAYTHEM >= \'"
            + from
            + "\' AND tk.NGAYTHEM <= \'" + to + "\'",
        TaiKhoan.class);
    query.setParameter("searchStr", searchStr);
    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }

  @Override
  @Transactional
  public void doiMatKhau(String newpassword, String username) {
    TaiKhoan taiKhoan = getTaiKhoan(username);

    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(newpassword));

    updateTaiKhoan(taiKhoan, "/profile/doithongtin");
  }

  @Override
  @Transactional
  public boolean verifyOtp(String password, String email) {
    Optional<Otp> result = otpRepository.findByEmailAndPassword(email, password);

    if (result.isPresent()) {
      log.info("Tim thay otp");
      if (result.get().isExpired()) {
        log.info("OTP da het han");
        // Xoa OTP het han
        otpRepository.delete(result.get());
        // Xoa tai khoan neu OTP da het han
        taiKhoanRepository.deleteById(email);
        return false;
      } else {
        log.info("Chua het han");
        Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepository.findById(email);

        if (taiKhoanOpt.isPresent()) {
          log.info("Tim thay tai khoan");
          TaiKhoan taiKhoan = taiKhoanOpt.get();
          taiKhoan.setEnabled(1);
          entityManager.merge(taiKhoan);
          log.info("Kich hoat tai khoan");
        }
        otpRepository.delete(result.get());
        log.info("Xoa OTP");
        return true;
      }
    }
    return false;
  }
}
