package edu.it10.vuquangdung.spring.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.SanPham;
import jakarta.persistence.LockModeType;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    List<SanPham> findByTenContains(@Param("searchStr") String searchStr);

    @Query("""
        SELECT DISTINCT b 
        FROM SanPham b 
        WHERE (
            UPPER(b.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) 
            OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')) 
            OR UPPER(b.loai) LIKE UPPER(CONCAT('%', :searchStr, '%')) 
            OR UPPER(b.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%'))
        ) 
        AND b.ngayThem >= :from 
        AND b.ngayThem <= :to 
        ORDER BY b.ngayThem DESC
    """)
    Page<SanPham> search(@Param("searchStr") String searchStr, @Param("from") String from, @Param("to") String to, Pageable pageable);

    @Query("""
        SELECT DISTINCT b FROM SanPham b
        WHERE 
            (
                UPPER(b.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) 
                OR UPPER(b.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%'))
                OR UPPER(b.loai) LIKE UPPER(CONCAT('%', :searchStr, '%'))
                OR UPPER(b.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%'))
            ) 
            AND b.ngayThem >= :from 
            AND b.ngayThem <= :to 
            AND b.kichHoat = 1 
            AND EXISTS (
                SELECT 1 FROM SanPhamBienThe sb 
                WHERE sb.sanPham = b AND sb.soLuong > 0 AND sb.kichHoat = 1
            )
        ORDER BY b.ngayThem DESC
    """)
    Page<SanPham> searchActiveSanPham(@Param("searchStr") String searchStr, @Param("from") String from,
            @Param("to") String to, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b from SanPham b WHERE b.id = :id")
    Optional<SanPham> findByIdWithLock(@Param("id") Integer id);
}
