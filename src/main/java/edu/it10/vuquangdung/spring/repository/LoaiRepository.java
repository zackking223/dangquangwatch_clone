package edu.it10.vuquangdung.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.it10.vuquangdung.spring.entity.Loai;

@Repository
public interface LoaiRepository extends JpaRepository<Loai, String> {
    @Query("SELECT DISTINCT b FROM Loai b WHERE UPPER(b.loaiId) LIKE UPPER(CONCAT('%', :searchStr, '%'))")
    Page<Loai> findByIdContains(@Param("searchStr") String searchStr, Pageable pageable);

    @Query("SELECT b.loaiId FROM Loai b")
    List<String> getAllId();
}
