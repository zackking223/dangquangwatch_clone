package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.repository.CrudRepository;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import java.util.List;

public interface DonHangRepository extends CrudRepository<DonHang, Integer> {
  List<DonHang> findByUsernameContains(String username);
}
