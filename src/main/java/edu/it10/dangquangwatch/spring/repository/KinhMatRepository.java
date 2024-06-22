package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.KinhMat;
import java.util.List;


public interface KinhMatRepository extends JpaRepository<KinhMat, Integer> {
  List<KinhMat> findByTenSanPhamContains(String tenSanPham);
}
