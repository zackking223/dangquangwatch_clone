package edu.it10.dangquangwatch.spring.repository;

import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.repository.custom.DonghoRepositoryCustom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonghoRepository extends JpaRepository<Dongho, Integer>, DonghoRepositoryCustom {
  @Query("SELECT DISTINCT d FROM Dongho d WHERE UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.chatlieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.gioitinh) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.bomay) LIKE UPPER(CONCAT('%', :searchStr, '%')) ORDER BY d.NGAYTHEM")
  Page<Dongho> findByTendonghoContains(@Param("searchStr") String searchStr, Pageable pageable);
}