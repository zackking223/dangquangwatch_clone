package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhphukien;

public interface AnhphukienService {
  List<Anhphukien> getAll();

  void save(Anhphukien anhphukien) throws IOException;

  void delete(Integer maanh) throws IOException;

  Optional<Anhphukien> findById(Integer maanh);
}
