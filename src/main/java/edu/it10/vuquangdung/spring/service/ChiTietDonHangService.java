package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.vuquangdung.spring.entity.ChiTietDonHang;

public interface ChiTietDonHangService {
  List<ChiTietDonHang> getAllCTDH();

  Page<ChiTietDonHang> searchCTDH(String searchStr, Integer pageNum);

  ChiTietDonHang saveCTDH(ChiTietDonHang ctdh);

  void deleteCTDH(Integer maChiTietDonHang);

  Optional<ChiTietDonHang> findCTDHById(Integer maChiTietDonHang);
}
