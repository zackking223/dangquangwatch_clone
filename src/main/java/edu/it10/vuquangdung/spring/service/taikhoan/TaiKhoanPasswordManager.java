package edu.it10.vuquangdung.spring.service.taikhoan;

public interface TaiKhoanPasswordManager {
    void doiMatKhau(String newPassword, String username, String path);
    void doiMatKhauHashed(String hashedString, String username, String path);
}
