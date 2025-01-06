package edu.it10.dangquangwatch.spring.service.taikhoan.impl;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanPhoneManager;
import org.springframework.stereotype.Service;

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
