package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.repository.CrudRepository;

import edu.it10.dangquangwatch.spring.entity.KinhMat;
import java.util.List;


public interface KinhMatRepository extends CrudRepository<KinhMat, Integer> {
  List<KinhMat> findByTenSanPham(String tenSanPham);
}
