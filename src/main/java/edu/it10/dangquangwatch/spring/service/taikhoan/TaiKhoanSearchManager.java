package edu.it10.dangquangwatch.spring.service.taikhoan;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import org.springframework.data.domain.Page;

public interface TaiKhoanSearchManager {
    Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page);
    Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page);
    Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page);
    Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page);
    Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page);
    Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page);
}
