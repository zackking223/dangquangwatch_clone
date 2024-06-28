package edu.it10.dangquangwatch.spring.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.it10.dangquangwatch.spring.entity.DonHang;

public interface DonHangRepositoryCustom {
  /**
   * @param hoten Tên người mua
   * @param diachi Địa chỉ giao hàng
   * @param tensanpham Tên sản phẩm mua
   * @param pageable Thông số phân trang
   * @return Phân trang đơn hàng
   */
  Page<DonHang> searchDonHang(String hoten, String diachi, String tensanpham, String tinhtrang, String thanhtoan, Integer tongtien, String from, String to, Pageable pageable);
}
