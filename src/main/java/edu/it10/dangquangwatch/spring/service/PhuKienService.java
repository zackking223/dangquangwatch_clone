package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienService {
  List<PhuKien> getAll();

  Page<PhuKien> search(String searchStr, String from, String to, Integer pageNum);
  Page<PhuKien> searchAvaiable(String searchStr, String from, String to, Integer pageNum);
  List<PhuKien> search(String searchStr);

  PhuKien savePhuKien(PhuKien phuKien);

  void activate(Integer maPhuKien);
  void deactivate(Integer maPhuKien);

  Optional<PhuKien> findById(Integer maPhuKien);

  void incAmount(Integer amount, Integer id);
  void decAmount(Integer amount, Integer id);
}
