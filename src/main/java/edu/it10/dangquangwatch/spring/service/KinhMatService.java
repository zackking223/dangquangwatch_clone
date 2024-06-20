package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.KinhMat;

public interface KinhMatService {
  List<KinhMat> getAllKinhMat();  

  List<KinhMat> searchKinhMat(String tenSanPham);

  void saveKinhMat(KinhMat kinhMat);  

  void deleteKinhMat(Integer maKinhMat);  

  Optional<KinhMat> findKinhMatById(Integer maKinhMat);  
}
