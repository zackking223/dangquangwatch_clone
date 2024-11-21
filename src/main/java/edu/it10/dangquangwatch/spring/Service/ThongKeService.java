package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.ThongKe;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;  


public interface ThongKeService {  
  List<ThongKe> getAllThongKe();
  ThongKe getCurrent();   
  Optional<ThongKe> findThongKeById(String mathongke);
  
  void incLuotTruyCap();
  void incLuotXemSanPham();
  void incLuotThemGioHang();
  void incDoanhThu(BigDecimal amount);
  void updateVon(BigDecimal newCapital);
  void incChiPhi(BigDecimal amount);
  void incVon(BigDecimal amount);
  void decVon(BigDecimal amount);
  void updateTiLeChuyenDoi(Float newTiLe);
}