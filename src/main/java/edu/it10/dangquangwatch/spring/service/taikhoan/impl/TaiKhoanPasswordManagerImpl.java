package edu.it10.dangquangwatch.spring.service.taikhoan.impl;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanPasswordManager;
import org.springframework.stereotype.Service;

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
