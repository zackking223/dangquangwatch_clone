package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Butky;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
  

@Repository  
public interface ButkyRepository extends JpaRepository<Butky, Integer> {
  Page<Butky> findByTenbutkyContains(@Param("searchStr") String searchStr, Pageable pageable);
  
  @Query("SELECT DISTINCT b FROM Butky b WHERE UPPER(b.tenbutky) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')) ORDER BY b.NGAYTHEM DESC")
  Page<Butky> searchButKy(@Param("searchStr") String searchStr, Pageable pageable);
}