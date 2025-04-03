package edu.it10.vuquangdung.spring.service.taikhoan;

import org.springframework.data.domain.Page;

import edu.it10.vuquangdung.spring.entity.TaiKhoan;

public interface TaiKhoanSearchManager {
    Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page);
    Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page);
    Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page);
    Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page);
    Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page);
    Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page);
}
