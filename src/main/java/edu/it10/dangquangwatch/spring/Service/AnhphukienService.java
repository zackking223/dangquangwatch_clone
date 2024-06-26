package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhphukien;

public interface AnhphukienService {
  List<Anhphukien> getAllAnhphukien();

  void saveAnhphukien(Anhphukien anhphukien) throws IOException;

  void deleteAnhphukien(Integer maanh);

  Optional<Anhphukien> findAnhphukienById(Integer maanh);
}
