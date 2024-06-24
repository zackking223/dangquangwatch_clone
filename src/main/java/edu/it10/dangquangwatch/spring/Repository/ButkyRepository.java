package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Butky;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
  

@Repository  
public interface ButkyRepository extends JpaRepository<Butky, Integer> {
  Page<Butky> findByTenbutkyContains(String tenbutky, Pageable pageable);
  
  @Query("SELECT DISTINCT b FROM Butky b WHERE (UPPER(b.tenbutky) IN :upperValues OR UPPER(b.thongtin) IN :upperValues) ORDER BY b.NGAYTHEM DESC")
  Page<Butky> searchButKy(@Param("upperValues") List<String> upperValues, Pageable pageable);
}