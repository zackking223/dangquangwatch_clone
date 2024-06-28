package edu.it10.dangquangwatch.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.entity.DonHang;

public interface DonHangService {
  Page<DonHang> getAllDonHang(int page);
  
  Page<DonHang> getMyDonHang(String searchStr, String tinhtrang, String thanhtoan, String username, String from, String to, int page);

  Optional<DonHang> findDonHangById(int madonhang);

  void addDonHang(DonHang donHang);

  void updateDonHang(DonHang donHang);
  
  void deleteDonHang(int madonhang);

  void removeSP(int maCTDH);

  void addSP(int madonhang, ChiTietDonHang ctdh);

  void incSP(int maCTDH);

  void decSP(int maCTDH);
  
  /**
   * @param searchStr : Tìm kiếm bằng tên người dùng & địa chỉ & tên sản phẩm mua
   * @param page : Số trang
   * @return : Phân trang đơn hàng dựa theo số trang, mỗi trang tối đa 10 dòng
   */
  Page<DonHang> searchDonHang(String hoten, String diachi, String tensanpham, String tinhtrang, String thanhtoan, Integer tongtien, String from, String to, int page);
}
