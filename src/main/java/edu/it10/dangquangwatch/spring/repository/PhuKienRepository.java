package edu.it10.dangquangwatch.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienRepository extends CrudRepository<PhuKien, Integer> {
  List<PhuKien> findByTenPhuKienContains(String tenPhuKien);
}
