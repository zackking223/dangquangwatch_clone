package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.KinhMat;

public interface KinhMatService {
  List<KinhMat> getAllKinhMat();  

  Page<KinhMat> searchKinhMat(String searchStr, Integer pageNum);

  void saveKinhMat(KinhMat kinhMat);  

  void deleteKinhMat(Integer maKinhMat);  

  Optional<KinhMat> findKinhMatById(Integer maKinhMat);  
}
