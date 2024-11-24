package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Butky;  

import java.util.List;  
import java.util.Optional;

import org.springframework.data.domain.Page;  

public interface ButkyService {  
  List<Butky> getAll();
  
  Page<Butky> search(String searchStr, String from, String to, Integer pageNum);
  Page<Butky> searchAvaiable(String searchStr, String from, String to, Integer pageNum);
  List<Butky> search(String searchStr);

  Butky save(Butky butky);  

  void activate(Integer mabutky);  
  void deactivate(Integer mabutky);  

  Optional<Butky> findById(Integer mabutky);  

  void incAmount(Integer amount, Integer id);
  void decAmount(Integer amount, Integer id);
}