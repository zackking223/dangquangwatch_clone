package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import jakarta.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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

  @Query("SELECT DISTINCT b FROM Trangsuc b WHERE (UPPER(b.tentrangsuc) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.NGAYTHEM >= :from AND b.NGAYTHEM <= :to AND b.kichhoat = 1 AND b.soluong > 0 ORDER BY b.NGAYTHEM DESC")
  Page<Trangsuc> searchActiveTrangsuc(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

  @Query("SELECT DISTINCT b FROM Trangsuc b WHERE (UPPER(b.tentrangsuc) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%'))) AND b.kichhoat = 1 AND b.soluong > 0 ORDER BY b.NGAYTHEM DESC")
  List<Trangsuc> search(String searchStr);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT t FROM Trangsuc t WHERE t.matrangsuc = :id")
  Optional<Trangsuc> findByIdWithLock(@Param("id") Integer id);
}