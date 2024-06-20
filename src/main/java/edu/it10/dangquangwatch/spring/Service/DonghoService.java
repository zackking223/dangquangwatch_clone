package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  

import java.util.List;  
import java.util.Optional;  

public interface DonghoService {  
  List<Dongho> getAllDongho();  

  void saveDongho(Dongho dongho);  

  void deleteDongho(Integer madongho);  

  Optional<Dongho> findDonghoById(Integer madongho);  
}