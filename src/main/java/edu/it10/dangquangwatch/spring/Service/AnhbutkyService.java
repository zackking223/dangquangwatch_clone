package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhbutky;

public interface AnhbutkyService {
  List<Anhbutky> getAll();

  void save(Anhbutky anhbutky) throws IOException;

  void delete(Integer maanh) throws IOException;

  Optional<Anhbutky> findById(Integer maanh);
}
