package edu.it10.vuquangdung.spring.service.taikhoan.impl;

import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.TaiKhoan;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanPhoneManager;

@Service
public class TaiKhoanPhoneManagerImpl implements TaiKhoanPhoneManager {
    private final TaiKhoanManager taikhoanManager;

    public TaiKhoanPhoneManagerImpl(TaiKhoanManager taikhoanManager) {
        this.taikhoanManager = taikhoanManager;
    }

    @Override
    public void doiSoDienThoai(String soDienThoai, String username, String path) {
        TaiKhoan taikhoan = taikhoanManager.getTaiKhoan(username);

        taikhoan.setSodienthoai(soDienThoai);

        taikhoanManager.updateTaiKhoan(taikhoan, path);
    }
}
