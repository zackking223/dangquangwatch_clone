package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.repository.custom.DonHangRepositoryCustom;

public interface DonHangRepository extends JpaRepository<DonHang, Integer>, DonHangRepositoryCustom {
  @Query(value = "SELECT * FROM donhang dh " +
      "WHERE dh.username = :username " +
      "AND (UPPER(dh.diachi) LIKE UPPER(CONCAT('%', :searchStr, '%')) " +
      "     OR EXISTS (SELECT 1 FROM chitietdonhang ctdh " +
      "                WHERE ctdh.madonhang = dh.madonhang " +
      "                AND UPPER(ctdh.tensanpham) LIKE UPPER(CONCAT('%', :searchStr, '%')))) " +
      "AND UPPER(dh.tinhtrang) LIKE UPPER(CONCAT('%', :tinhtrang, '%')) " +
      "AND UPPER(dh.thanhtoan) LIKE UPPER(CONCAT('%', :thanhtoan, '%')) " +
      "AND dh.NGAYTHEM >= :fromD " +
      "AND dh.NGAYTHEM <= :toD", nativeQuery = true)
  Page<DonHang> getMyDonHang(@Param("searchStr") String searchStr,
      @Param("tinhtrang") String tinhtrang,
      @Param("thanhtoan") String thanhtoan,
      @Param("username") String username,
      @Param("fromD") String from,
      @Param("toD") String to,
      Pageable pageable);
}