package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhbutky;

public interface AnhbutkyService {
  List<Anhbutky> getAllAnhbutky();

  void saveAnhbutky(Anhbutky anhbutky) throws IOException;

  void deleteAnhbutky(Integer maanh) throws IOException;

  Optional<Anhbutky> findAnhbutkyById(Integer maanh);
}
