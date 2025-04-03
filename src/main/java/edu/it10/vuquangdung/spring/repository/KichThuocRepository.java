package edu.it10.vuquangdung.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.KichThuoc;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, String> {
    @Query("SELECT DISTINCT b FROM KichThuoc b WHERE UPPER(b.kichThuocId) LIKE UPPER(CONCAT('%', :searchStr, '%'))")
    Page<KichThuoc> findByIdContains(@Param("searchStr") String searchStr, Pageable pageable);

    @Query("SELECT b.kichThuocId FROM KichThuoc b")
    List<String> getAllId();
}
