package edu.it10.vuquangdung.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

@Repository
public interface SanPhamBienTheRepository extends JpaRepository<SanPhamBienThe, Integer> {
    @Query("""
        SELECT b FROM SanPhamBienThe b
        WHERE b.kichHoat = 1 
        AND b.sanPham =: sanPham
    """)
    List<SanPhamBienThe> findAllActive(@Param("sanPham") SanPham sanPham);

    @Query("SELECT s FROM SanPhamBienThe s WHERE s.sanPham = :sanPham")
    List<SanPhamBienThe> findAll(@Param("sanPham") SanPham sanPham);

    @Query("SELECT s from SanPhamBienThe s WHERE s.id = :id")
    Optional<SanPhamBienThe> findByIdWithLock(@Param("id") Integer id);
}
