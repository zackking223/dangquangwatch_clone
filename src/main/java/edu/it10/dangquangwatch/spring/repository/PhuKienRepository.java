package edu.it10.dangquangwatch.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.PhuKien;
import jakarta.persistence.LockModeType;

public interface PhuKienRepository extends JpaRepository<PhuKien, Integer> {
  List<PhuKien> findByTenPhuKienContains(String tenPhuKien);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT p from PhuKien p WHERE p.maPhuKien = :id")
  Optional<PhuKien> findByIdWithLock(@Param("id") Integer id);

  @Query("SELECT DISTINCT b FROM PhuKien b WHERE (UPPER(b.tenPhuKien) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to ORDER BY b.NGAYTHEM DESC")
  Page<PhuKien> searchPhuKien(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT b FROM PhuKien b WHERE (UPPER(b.tenPhuKien) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to AND b.kichhoat = 1 AND b.soLuong > 0 ORDER BY b.NGAYTHEM DESC")
  Page<PhuKien> searchActivePhuKien(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT b FROM PhuKien b WHERE (UPPER(b.tenPhuKien) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.kichhoat = 1 AND b.soLuong > 0 ORDER BY b.NGAYTHEM DESC")
  List<PhuKien> search(String searchStr);
}
