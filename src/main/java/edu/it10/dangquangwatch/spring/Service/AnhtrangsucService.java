package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;

public interface AnhtrangsucService {
  List<Anhtrangsuc> getAll();

  void save(Anhtrangsuc anhtrAnhtrangsuc) throws IOException;

  void delete(Integer maanh) throws IOException;

  Optional<Anhtrangsuc> findById(Integer maanh);
}
