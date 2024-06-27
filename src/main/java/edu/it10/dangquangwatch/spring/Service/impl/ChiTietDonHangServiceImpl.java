package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.repository.ChiTietDonHangRepository;
import edu.it10.dangquangwatch.spring.service.ChiTietDonHangService;

@Service
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {
  @Autowired ChiTietDonHangRepository ctdhRepository;

  @Override
  public List<ChiTietDonHang> getAllCTDH() {
    return ctdhRepository.findAll();
  }

  @Override
  public Page<ChiTietDonHang> searchCTDH(String searchStr, Integer pageNum) {
    return ctdhRepository.searchCTDH(searchStr, PageRequest.of(pageNum, 10));
  }

  @Override
  public ChiTietDonHang saveCTDH(ChiTietDonHang ctdh) {
    return ctdhRepository.save(ctdh);
  }

  @Override
  public void deleteCTDH(Integer maChiTietDonHang) {
    ctdhRepository.deleteById(maChiTietDonHang);
  }

  @Override
  public Optional<ChiTietDonHang> findCTDHById(Integer maChiTietDonHang) {
    return ctdhRepository.findById(maChiTietDonHang);
  }
}
