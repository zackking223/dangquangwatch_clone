package edu.it10.dangquangwatch.spring.service.taikhoan;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;

public interface TaiKhoanManager {
    void dangKyKhachHang(TaiKhoan taiKhoan, String path);
    void dangKyQuanTri(TaiKhoan taiKhoan);
    void updateTaiKhoan(TaiKhoan taiKhoan, String path);
    void deleteById(String username);
    TaiKhoan getTaiKhoan(String username);
    void activate(String username);
    void deactivate(String username);
}
