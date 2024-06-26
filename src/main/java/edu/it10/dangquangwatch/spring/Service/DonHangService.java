package edu.it10.dangquangwatch.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.DonHang;

public interface DonHangService {
  Page<DonHang> getAllDonHang(int page);

  Optional<DonHang> findDonHangById(int madonhang);

  void saveDonHang(DonHang donHang);
  
  void deleteDonHang(int madonhang);
  
  /**
   * @param searchStr : Tìm kiếm bằng tên người dùng & địa chỉ & tên sản phẩm mua
   * @param page : Số trang
   * @return : Phân trang đơn hàng dựa theo số trang, mỗi trang tối đa 10 dòng
   */
  Page<DonHang> searchDonHang(String hoten, String diachi, String tensanpham, String tinhtrang, String thanhtoan, Integer tongtien, int page);
}
