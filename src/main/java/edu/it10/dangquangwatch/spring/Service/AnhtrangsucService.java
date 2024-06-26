package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;

public interface AnhtrangsucService {
  List<Anhtrangsuc> getAllAnhtrangsuc();

  void saveAnhtrangsuc(Anhtrangsuc anhtrAnhtrangsuc) throws IOException;

  void deleteAnhtrangsuc(Integer maanh);

  Optional<Anhtrangsuc> findAnhtrangsucById(Integer maanh);
}
