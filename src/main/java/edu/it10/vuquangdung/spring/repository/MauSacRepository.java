package edu.it10.vuquangdung.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.MauSac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, String> {
    @Query("SELECT DISTINCT b FROM MauSac b WHERE UPPER(b.mauSacId) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(b.ten) LIKE UPPER(CONCAT('%', :searchStr, '%'))")
    Page<MauSac> search(@Param("searchStr") String searchStr, Pageable pageable);
}
