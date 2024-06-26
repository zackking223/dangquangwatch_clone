package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhdongho;

public interface AnhdonghoService {
  List<Anhdongho> getAllAnhdongho();

  void saveAnhdongho(Anhdongho anhdongho) throws IOException;

  void deleteAnhdongho(Integer maanh);

  Optional<Anhdongho> findAnhdonghoById(Integer maanh);
}
