package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Butky;  

import java.util.List;  
import java.util.Optional;  

public interface ButkyService {  
  List<Butky> getAllButky();  

  void saveButky(Butky butky);  

  void deleteButky(Integer mabutky);  

  Optional<Butky> findButkyById(Integer mabutky);  
}