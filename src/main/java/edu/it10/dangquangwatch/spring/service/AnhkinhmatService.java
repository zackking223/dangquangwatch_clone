package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;

public interface AnhkinhmatService {
  List<Anhkinhmat> getAll();

  void save(Anhkinhmat anhkinhmat) throws IOException;

  void delete(Integer maanh) throws IOException;

  Optional<Anhkinhmat> findById(Integer maanh);
}
