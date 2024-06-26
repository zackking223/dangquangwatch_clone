package edu.it10.dangquangwatch.spring.service;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  

import java.util.List;  
import java.util.Optional;

import org.springframework.data.domain.Page;  

public interface TrangsucService {  
  List<Trangsuc> getAllTrangsuc();  

  Trangsuc saveTrangsuc(Trangsuc trangsuc);  

  Page<Trangsuc> searchTrangsuc(String searchStr, Integer pageNum);

  void deleteTrangsuc(Integer matrangsuc);  

  Optional<Trangsuc> findTrangsucById(Integer matrangsuc);  
}