package edu.it10.dangquangwatch.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderPaymentStatus;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderStatus;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.payment.GlobalCardInfo;
import edu.it10.dangquangwatch.spring.payment.LocalCardInfo;

public interface DonHangService {
  Page<DonHang> getAllDonHang(int page);
  
  Page<DonHang> getMyDonHang(String searchStr, String tinhtrang, String thanhtoan, String username, String from, String to, int page);

  Optional<DonHang> findDonHangById(int madonhang);

  DonHang validate(DonHang donHang, TaiKhoan taiKhoan);

  DonHang save(DonHang donHang, OrderStatus status, OrderPaymentStatus paymentStatus);
  ApiResponse checkOut(DonHang donHang);
  ApiResponse checkOut(DonHang donHang, GlobalCardInfo cardInfo);
  ApiResponse checkOut(DonHang donHang, LocalCardInfo cardInfo);

  void updateDonHang(DonHang donHang);

  void updateStatus(Integer madonhang, OrderStatus status, OrderPaymentStatus paymentStatus);
  
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
  Page<DonHang> searchDonHang(String username, String hoten, String diachi, String tensanpham, String tinhtrang, String thanhtoan, Integer tongtien, String from, String to, int page);
}
