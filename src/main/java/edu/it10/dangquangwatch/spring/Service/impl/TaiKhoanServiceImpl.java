package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

  @Override
  @Transactional
  public void dangKyKhachHang(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

    taiKhoan.setLoai_tai_khoan("ROLE_KHACHHANG");
    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

    entityManager.persist(taiKhoan);
  }

  @Override
  @Transactional
  public void dangKyQuanTri(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

    taiKhoan.setLoai_tai_khoan("ROLE_QUANTRI");
    taiKhoan.setDiachi("QUANTRI ko can dia chi");
    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

    entityManager.persist(taiKhoan);
  }

  @Override
  @Transactional
  public void updateTaiKhoan(TaiKhoan taiKhoan) {
    String plainText = taiKhoan.getPassword();
    taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
    taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));
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
        "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and tk.username LIKE :username and tk.NGAYTHEM >= \'"
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
        "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and tk.hoten LIKE :searchStr OR tk.diachi LIKE :searchStr OR tk.username LIKE :searchStr and tk.NGAYTHEM >= \'"
            + from
            + "\' AND tk.NGAYTHEM <= \'" + to + "\'",
        TaiKhoan.class);
    query.setParameter("searchStr", searchStr);
    List<TaiKhoan> result = query.getResultList();

    return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
  }
}
