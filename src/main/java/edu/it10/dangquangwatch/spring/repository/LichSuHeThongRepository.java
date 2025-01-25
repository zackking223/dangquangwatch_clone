package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.it10.dangquangwatch.spring.entity.LichSuHeThong;

public interface LichSuHeThongRepository extends JpaRepository<LichSuHeThong, Integer> {
  @Query("SELECT DISTINCT d FROM LichSuHeThong d WHERE UPPER(d.hanhDong) LIKE UPPER(CONCAT('%', :hanhDong, '%')) OR UPPER(d.thongTin) LIKE UPPER(CONCAT('%', :thongTin, '%')) OR UPPER(d.nguoiThucHien) LIKE UPPER(CONCAT('%', :nguoiThucHien, '%')) AND d.thoiGian >= :from AND d.thoiGian <= :to ORDER BY d.thoiGian")
  Page<LichSuHeThong> searchLichSuHeThong(@Param("thongTin") String thongTin, @Param("hanhDong") String hanhDong, @Param("nguoiThucHien") String nguoiThucHien, @Param("from") String from, @Param("to") String to, Pageable pageable);
}
