package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Integer> {

  @Query("SELECT DISTINCT b FROM ChiTietDonHang b JOIN b.donhang dh JOIN dh.taikhoan tk WHERE UPPER(b.tensanpham) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(tk.username) LIKE UPPER(CONCAT('%', :searchStr, '%')) ORDER BY b.NGAYTHEM DESC")
  Page<ChiTietDonHang> searchCTDH(@Param("searchStr") String searchStr, Pageable pageable);
}
