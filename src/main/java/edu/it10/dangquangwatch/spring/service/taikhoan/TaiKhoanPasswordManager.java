package edu.it10.dangquangwatch.spring.service.taikhoan;

public interface TaiKhoanPasswordManager {
    void doiMatKhau(String newPassword, String username, String path);
    void doiMatKhauHashed(String hashedString, String username, String path);
}
