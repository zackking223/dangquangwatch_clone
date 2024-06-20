package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  

import java.util.List;  
import java.util.Optional;  

public interface TrangsucService {  
  List<Trangsuc> getAllTrangsuc();  

  void saveTrangsuc(Trangsuc trangsuc);  

  void deleteTrangsuc(Long matrangsuc);  

  Optional<Trangsuc> findTrangsucById(Long matrangsuc);  
}