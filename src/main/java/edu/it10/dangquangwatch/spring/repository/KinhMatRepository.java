package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.KinhMat;
import java.util.List;
import java.util.Optional;


public interface KinhMatRepository extends JpaRepository<KinhMat, Integer> {
  List<KinhMat> findByTenKinhMatContains(String tenKinhMat);

  @Query("SELECT DISTINCT b FROM KinhMat b WHERE (UPPER(b.tenKinhMat) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to ORDER BY b.NGAYTHEM DESC")
  Page<KinhMat> searchKinhMat(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  Optional<KinhMat> findByTenKinhMat(String tenKinhMat);
}
