package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.repository.custom.DonHangRepositoryCustom;

public interface DonHangRepository extends JpaRepository<DonHang, Integer>, DonHangRepositoryCustom {
  @Query("SELECT dh FROM DonHang dh JOIN dh.taikhoan tk JOIN dh.items ctdh WHERE tk.username = :username AND UPPER(dh.diaChi) LIKE UPPER(CONCAT('%', :searchStr, '%')) AND UPPER(dh.tinhTrang) LIKE UPPER(CONCAT('%', :tinhtrang, '%')) AND UPPER(dh.thanhToan) LIKE UPPER(CONCAT('%', :thanhtoan, '%')) AND EXISTS (SELECT 1 FROM ChiTietDonHang ctdh WHERE ctdh.donhang = dh AND UPPER(ctdh.tensanpham) LIKE UPPER(CONCAT('%', :searchStr, '%')) )")
  Page<DonHang> getMyDonHang(@Param("searchStr") String searchStr, @Param("tinhtrang") String tinhtrang, @Param("thanhtoan") String thanhtoan, @Param("username") String username, Pageable pageable);
}
