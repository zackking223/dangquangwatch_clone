package edu.it10.dangquangwatch.spring.repository;

import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.repository.custom.DonghoRepositoryCustom;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface DonghoRepository extends JpaRepository<Dongho, Integer>, DonghoRepositoryCustom {
  @Query("SELECT DISTINCT d FROM Dongho d WHERE UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.chatlieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.gioitinh) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.bomay) LIKE UPPER(CONCAT('%', :searchStr, '%')) AND d.NGAYTHEM >= :from AND d.NGAYTHEM <= :to ORDER BY d.NGAYTHEM DESC")
  Page<Dongho> searchDongho(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);
  
  @Query("SELECT DISTINCT d FROM Dongho d WHERE UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.chatlieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.gioitinh) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.bomay) LIKE UPPER(CONCAT('%', :searchStr, '%')) AND d.NGAYTHEM >= :from AND d.NGAYTHEM <= :to AND d.kichhoat = 1 AND d.soluong > 0 ORDER BY d.NGAYTHEM DESC")
  Page<Dongho> searchActiveDongho(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT d FROM Dongho d WHERE UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.chatlieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.gioitinh) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.bomay) LIKE UPPER(CONCAT('%', :searchStr, '%')) AND d.kichhoat = 1 AND d.soluong > 0 ORDER BY d.NGAYTHEM DESC")
  List<Dongho> search(@Param("searchStr") String searchStr);

  Optional<Dongho> findByTendongho(String tendongho);
}