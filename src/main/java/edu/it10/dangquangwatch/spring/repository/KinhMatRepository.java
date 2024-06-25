package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.KinhMat;
import java.util.List;


public interface KinhMatRepository extends JpaRepository<KinhMat, Integer> {
  List<KinhMat> findByTenSanPhamContains(String tenSanPham);

  @Query("SELECT DISTINCT b FROM KinhMat b WHERE UPPER(b.tenSanPham) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')) ORDER BY b.NGAYTHEM DESC")
  Page<KinhMat> searchKinhMat(@Param("searchStr") String searchStr, Pageable pageable);
}
