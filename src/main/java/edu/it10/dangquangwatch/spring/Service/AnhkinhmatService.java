package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;

public interface AnhkinhmatService {
  List<Anhkinhmat> getAllAnhkinhmat();

  void saveAnhkinhmat(Anhkinhmat anhkinhmat) throws IOException;

  void deleteAnhkinhmat(Integer maanh);

  Optional<Anhkinhmat> findAnhkinhmatById(Integer maanh);
}
