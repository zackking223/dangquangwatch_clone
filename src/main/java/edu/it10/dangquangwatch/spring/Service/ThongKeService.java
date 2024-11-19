package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.ThongKe;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;  


public interface ThongKeService {  
  List<ThongKe> getAllThongKe();
  ThongKe getCurrent();   
  Optional<ThongKe> findThongKeById(String mathongke);
  
  void tangluottruycap();
  void tangluotxemsanpham();
  void tangluotthemgiohang();
  void updateVon(BigDecimal newCapital);
  void incVon(BigDecimal amount);
  void decVon(BigDecimal amount);
  void updateTiLeChuyenDoi(Float newTiLe);
}