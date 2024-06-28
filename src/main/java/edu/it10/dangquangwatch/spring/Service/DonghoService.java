package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  

import java.util.List;  
import java.util.Optional;

import org.springframework.data.domain.Page; 

public interface DonghoService {  
  List<Dongho> getAllDongho();  

  Page<Dongho> getAllDonghoByTendongho(String tendongho, String from, String to, int pageNum);  

  Page<Dongho> searchDongho(String searchStr, Dongho searchData, String from, String to, int pageNum);

  Dongho saveDongho(Dongho dongho);  

  void deleteDongho(Integer madongho);  

  Optional<Dongho> findDonghoById(Integer madongho);  
}