package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
  List<DonHang> findByUsernameContains(String username);
}
