package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.ThongKe;

import java.util.List;
import java.util.Optional;  


public interface ThongKeService {  
  List<ThongKe> getAllThongKe();   

  Optional<ThongKe> findThongKeById(Integer mathongke);
  
  void tangluottruycap();
  
  void tangluotxemsanpham();

  void tangluotthemgiohang();

  void updateVon(Integer newCapital);
  void incVon(Integer amount);
  void decVon(Integer amount);
  void updateTiLeChuyenDoi(Float newTiLe);
}