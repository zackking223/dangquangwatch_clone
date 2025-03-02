package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  

import java.util.List;  
import java.util.Optional;

import org.springframework.data.domain.Page;  

public interface TrangsucService {  
  List<Trangsuc> getAll();  

  Trangsuc save(Trangsuc trangsuc);  

  Page<Trangsuc> search(String searchStr, String from, String to, Integer pageNum);
  Page<Trangsuc> searchAvaiable(String searchStr, String from, String to, Integer pageNum);
  List<Trangsuc> search(String searchStr);

  void activate(Integer matrangsuc);
  void deactivate(Integer matrangsuc);  

  Optional<Trangsuc> findById(Integer matrangsuc);
  Optional<Trangsuc> findByIdWithLock(Integer matrangsuc);
  
  void incAmount(Integer amount, Integer id);
  void decAmount(Integer amount, Integer id);

  void delete(Integer id);
}