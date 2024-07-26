package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import java.util.List;


public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
  List<TaiKhoan> findBySodienthoai(String sodienthoai);
}
