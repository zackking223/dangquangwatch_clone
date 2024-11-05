package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
  

@Repository  
public interface TrangsucRepository extends JpaRepository<Trangsuc, Integer> {
  List<Trangsuc> findByTentrangsucContains(String tentrangsuc);

  Optional<Trangsuc> findByTentrangsuc(String tentrangsuc);

  @Query("SELECT DISTINCT b FROM Trangsuc b WHERE (UPPER(b.tentrangsuc) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to ORDER BY b.NGAYTHEM DESC")
  Page<Trangsuc> searchTrangsuc(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);
}