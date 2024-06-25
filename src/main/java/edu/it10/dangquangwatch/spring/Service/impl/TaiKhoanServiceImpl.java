package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
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
  public List<TaiKhoan> getAllTaiKhoan() {
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan", TaiKhoan.class);

    return query.getResultList();
  }

  @Override
  public List<TaiKhoan> getAllTaiKhoanQuanTri() {
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan where loai_tai_khoan = 'ROLE_QUANTRI'",
        TaiKhoan.class);

    return query.getResultList();
  }

  @Override
  public List<TaiKhoan> getAllTaiKhoanKhachHang() {
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan where loai_tai_khoan = 'ROLE_KHACHHANG'",
        TaiKhoan.class);

    return query.getResultList();
  }

  @Override
  @Transactional
  public void dangKyKhachHang(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

    taiKhoan.setLoai_tai_khoan("ROLE_KHACHHANG");
    taiKhoan.setPassword(passwordEncoder.encode(plainText));

    entityManager.persist(taiKhoan);
  }

  @Override
  @Transactional
  public void dangKyQuanTri(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

    taiKhoan.setLoai_tai_khoan("ROLE_QUANTRI");
    taiKhoan.setDiachi("QUANTRI ko can dia chi");
    taiKhoan.setPassword(passwordEncoder.encode(plainText));

    entityManager.persist(taiKhoan);
  }

  @Override
  @Transactional
  public void updateTaiKhoan(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    taiKhoan.setPassword(passwordEncoder.encode(plainText));
    entityManager.merge(taiKhoan);
  }

  @Override
  public TaiKhoan getTaiKhoan(String username) {
    return entityManager.find(TaiKhoan.class, username);
  }

  @Override
  public List<TaiKhoan> findTaiKhoanByUsername(String username) {
    username = "%" + username + "%";
    TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan WHERE username LIKE :data", TaiKhoan.class);
    query.setParameter("data", username);
    return query.getResultList();
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
}
