package edu.it10.dangquangwatch.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.LichSuHeThong;
import edu.it10.dangquangwatch.spring.entity.enumeration.SystemAction;
import edu.it10.dangquangwatch.spring.helper.DateStringHelper;
import edu.it10.dangquangwatch.spring.repository.LichSuHeThongRepository;

@Service
public class LichSuHeThongService {
    @Autowired
    private LichSuHeThongRepository lichSuHeThongRepository;

    public void AddDeleteProductHistory(String thongTin, String email) {
        LichSuHeThong lichSuHeThong = new LichSuHeThong();

        lichSuHeThong.setNguoiThucHien(email);
        lichSuHeThong.setHanhDong(SystemAction.Delete);
        lichSuHeThong.setThoiGian(DateStringHelper.getCurrentDateFormatted());
        lichSuHeThong.setThongTin(thongTin);

        lichSuHeThongRepository.save(lichSuHeThong);
    }
}
