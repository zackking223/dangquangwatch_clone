package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienService {
  List<PhuKien> getAllPhuKien();

  List<PhuKien> searchPhuKien(String tenPhuKien);

  void savePhuKien(PhuKien phuKien);

  void deletePhuKien(Integer maPhuKien);

  Optional<PhuKien> findPhuKienById(Integer maPhuKien);
}
