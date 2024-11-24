package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.SaveAccountException;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.helper.DateStringHelper;
import edu.it10.dangquangwatch.spring.repository.TaiKhoanRepository;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
  @Autowired
  private TaiKhoanRepository taiKhoanRepository;
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
        "SELECT distinct tk FROM TaiKhoan tk where tk.loai_tai_khoan <> 'ROLE_KHACHHANG' and tk.NGAYTHEM >= \'" + from
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
    taiKhoan.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());
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
  }

  @Override
  @Transactional
  public void dangKyQuanTri(TaiKhoan taiKhoan) throws SaveAccountException {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    taiKhoan.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());
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
  public void doiSoDienThoai(String soDienThoai, String username) {
    TaiKhoan target = entityManager.find(TaiKhoan.class, username);

    if (numberExisted(soDienThoai)) {
      String message = "Số điện thoại đã tồn tại!";
      SaveAccountException exception = new SaveAccountException(message, ErrorEnum.UPDATE_PROFILE_ERROR);
      exception.setTaiKhoan(target);
      throw exception;
    }

    target.setSodienthoai(soDienThoai);
    entityManager.merge(target);
  }

  @Override
  @Transactional
  public void updateTaiKhoan(TaiKhoan taiKhoan, String path) {
    String plainText = taiKhoan.getPassword();
    TaiKhoan original = taiKhoanRepository.findById(taiKhoan.getUsername())
        .orElseThrow(() -> new RuntimeException("Không thể tìm thấy người dùng với email: " + taiKhoan.getHoten()));

    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

    if (plainText != null && !plainText.contains("{bcrypt}")) {
      taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));
    } else {
      taiKhoan.setPassword(original.getPassword());
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
    var taiKhoan = entityManager.find(TaiKhoan.class, username);

    if (taiKhoan == null) {
      throw new ServiceException("Không thể tìm thấy người dùng với email: " + username);
    }
    return taiKhoan;
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
  public void activate(String username) {
    TaiKhoan target = entityManager.find(TaiKhoan.class, username);
    target.setEnabled(1);
    entityManager.merge(target);
  }

  @Override
  @Transactional
  public void deactivate(String username) {
    TaiKhoan target = entityManager.find(TaiKhoan.class, username);
    target.setEnabled(0);
    entityManager.merge(target);
  }

  @Override
  @Transactional
  public void deleteById(String username) {
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
        "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan <> 'ROLE_KHACHHANG' and tk.username LIKE :username and tk.NGAYTHEM >= \'"
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
  public void doiMatKhau(String plainText, String username) {
    TaiKhoan taiKhoan = getTaiKhoan(username);

    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

    entityManager.merge(taiKhoan);
  }

  @Override
  @Transactional
  public void doiMatKhauHashed(String hashedString, String username) {
    TaiKhoan taiKhoan = getTaiKhoan(username);

    taiKhoan.setPassword("{bcrypt}" + hashedString);

    entityManager.merge(taiKhoan);
  }
}
