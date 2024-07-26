package edu.it10.dangquangwatch.spring.service;

import edu.it10.dangquangwatch.spring.AppCustomException.DuplicateEntryException;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;

import org.springframework.data.domain.Page;

public interface TaiKhoanService {
  public TaiKhoan dangNhap(String username, String password);

  public Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page);

  public Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page);
  public Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page);

  public Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page);
  public Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page);

  public void dangKyKhachHang(TaiKhoan taiKhoan) throws DuplicateEntryException;

  public void dangKyQuanTri(TaiKhoan taiKhoan) throws DuplicateEntryException;

  public void updateTaiKhoan(TaiKhoan taiKhoan) throws DuplicateEntryException;

  public void doiMatKhau(String newpassword, String username);

  public TaiKhoan getTaiKhoan(String username);

  public Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page);

  public void deleteTaiKhoanByUsername(String username);
} 
