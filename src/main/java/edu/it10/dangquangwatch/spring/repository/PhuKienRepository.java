package edu.it10.dangquangwatch.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienRepository extends JpaRepository<PhuKien, Integer> {
  List<PhuKien> findByTenPhuKienContains(String tenPhuKien);
}
