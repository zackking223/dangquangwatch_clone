package edu.it10.dangquangwatch.spring.service;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import java.util.List;

public interface TaiKhoanService {
  public TaiKhoan dangNhap(String username, String password);

  public List<TaiKhoan> getAllTaiKhoan();

  public List<TaiKhoan> getAllTaiKhoanQuanTri();

  public List<TaiKhoan> getAllTaiKhoanKhachHang();

  public void dangKyKhachHang(TaiKhoan taiKhoan);

  public void dangKyQuanTri(TaiKhoan taiKhoan);

  public void updateTaiKhoan(TaiKhoan taiKhoan);

  public TaiKhoan findTaiKhoanById(Integer maTaiKhoan);

  public List<TaiKhoan> findTaiKhoanByUsername(String username);

  public void deleteTaiKhoanById(Integer maTaiKhoan);
} 
