package edu.it10.dangquangwatch.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienService {
  List<PhuKien> getAllPhuKien();

  Page<PhuKien> searchPhuKien(String searchStr, Integer pageNum);

  PhuKien savePhuKien(PhuKien phuKien);

  void deletePhuKien(Integer maPhuKien);

  Optional<PhuKien> findPhuKienById(Integer maPhuKien);
}
