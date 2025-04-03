package edu.it10.vuquangdung.spring.service.taikhoan.impl;

import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.TaiKhoan;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanPasswordManager;

@Service
public class TaiKhoanPasswordManagerImpl implements TaiKhoanPasswordManager {
    private final TaiKhoanManager taiKhoanManager;

    public TaiKhoanPasswordManagerImpl(TaiKhoanManager taiKhoanManager) {
        this.taiKhoanManager = taiKhoanManager;
    }

    @Override
    public void doiMatKhau(String plainText, String username, String path) {
        TaiKhoan taiKhoan = taiKhoanManager.getTaiKhoan(username);
        taiKhoan.setPassword(plainText);
        taiKhoanManager.updateTaiKhoan(taiKhoan, path);
    }

    @Override
    public void doiMatKhauHashed(String hashedString, String username, String path) {
        TaiKhoan taiKhoan = taiKhoanManager.getTaiKhoan(username);

        taiKhoan.setPassword("{bcrypt}" + hashedString);

        taiKhoanManager.updateTaiKhoan(taiKhoan, path);
    }
}
