package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  

import java.util.List;  
import java.util.Optional;

import org.springframework.data.domain.Page; 

public interface DonghoService {  
  List<Dongho> getAllDongho();  

  Page<Dongho> getAll(String tendongho, String from, String to, int pageNum);  
  Page<Dongho> searchAvaiable(String tendongho, String from, String to, int pageNum);  

  Page<Dongho> search(String searchStr, Dongho searchData, String from, String to, int pageNum);

  List<Dongho> search(String searchStr);

  Dongho save(Dongho dongho);  

  void activate(Integer madongho);
  void deactivate(Integer madongho);  

  Optional<Dongho> findById(Integer madongho);
  Optional<Dongho> findByIdWithLock(Integer madongho);
  
  void incAmount(Integer amount, Integer id);
  void decAmount(Integer amount, Integer id);
}