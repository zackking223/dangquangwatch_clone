package edu.it10.dangquangwatch.spring.service;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;

import org.springframework.data.domain.Page;

public interface TaiKhoanService {
  public TaiKhoan dangNhap(String username, String password);

  public Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page);

  public Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page);
  public Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page);

  public Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page);
  public Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page);

  public void dangKyKhachHang(TaiKhoan taiKhoan, String path);

  public void dangKyQuanTri(TaiKhoan taiKhoan);

  public void updateTaiKhoan(TaiKhoan taiKhoan, String path);

  public void doiMatKhau(String newpassword, String username);

  public TaiKhoan getTaiKhoan(String username);

  public Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page);

  public void activate(String username);
  public void deactivate(String username);
  public void deleteById(String username);

  public boolean verifyOtp(String password, String email);
} 
