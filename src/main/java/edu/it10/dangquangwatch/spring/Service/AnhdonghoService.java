package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhdongho;

public interface AnhdonghoService {
  List<Anhdongho> getAll();

  void save(Anhdongho anhdongho) throws IOException;

  void delete(Integer maanh) throws IOException;

  Optional<Anhdongho> findById(Integer maanh);
}
