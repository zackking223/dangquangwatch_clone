package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.ThongKe;

import java.util.List;
import java.util.Optional;  


public interface ThongKeService {  
  List<ThongKe> getAllThongKe();   

  Optional<ThongKe> findThongKeById(Integer mathongke);
  
  void tangluottruycap();
  
  void tangluotxemsanpham();
}