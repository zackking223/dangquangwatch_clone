package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.KinhMat;

public interface KinhMatService {
  List<KinhMat> getAll();

  Page<KinhMat> search(String searchStr, String from, String to, Integer pageNum);
  Page<KinhMat> searchAvaiable(String searchStr, String from, String to, Integer pageNum);
  List<KinhMat> search(String searchStr);

  KinhMat save(KinhMat kinhMat);  

  void activate(Integer maKinhMat);  
  void deactivate(Integer maKinhMat);  

  Optional<KinhMat> findById(Integer maKinhMat);
  Optional<KinhMat> findByIdWithLock(Integer maKinhMat);

  void incAmount(Integer amount, Integer id);
  void decAmount(Integer amount, Integer id);
}
