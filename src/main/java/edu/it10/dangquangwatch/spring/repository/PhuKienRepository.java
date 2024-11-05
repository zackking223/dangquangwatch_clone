package edu.it10.dangquangwatch.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.PhuKien;

public interface PhuKienRepository extends JpaRepository<PhuKien, Integer> {
  List<PhuKien> findByTenPhuKienContains(String tenPhuKien);
  Optional<PhuKien> findByTenPhuKien(String tenPhuKien);

  @Query("SELECT DISTINCT b FROM PhuKien b WHERE (UPPER(b.tenPhuKien) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to ORDER BY b.NGAYTHEM DESC")
  Page<PhuKien> searchPhuKien(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);
}
