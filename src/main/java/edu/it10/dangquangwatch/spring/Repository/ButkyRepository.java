package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Butky;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository  
public interface ButkyRepository extends JpaRepository<Butky, Integer> {
  Page<Butky> findByTenbutkyContains(@Param("searchStr") String searchStr, Pageable pageable);
  
  @Query("SELECT DISTINCT b FROM Butky b WHERE (UPPER(b.tenbutky) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to ORDER BY b.NGAYTHEM DESC")
  Page<Butky> searchButKy(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT b FROM Butky b WHERE (UPPER(b.tenbutky) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to AND b.kichhoat = 1 AND b.soluong > 0 ORDER BY b.NGAYTHEM DESC")
  Page<Butky> searchActiveButKy(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT b FROM Butky b WHERE (UPPER(b.tenbutky) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.kichhoat = 1 AND b.soluong > 0 ORDER BY b.NGAYTHEM DESC")
  List<Butky> search(@Param("searchStr") String searchStr);

  Optional<Butky> findByTenbutky(String tenbutky);
}