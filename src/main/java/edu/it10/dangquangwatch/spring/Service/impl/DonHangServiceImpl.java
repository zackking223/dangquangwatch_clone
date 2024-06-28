package edu.it10.dangquangwatch.spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.repository.DonHangRepository;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import jakarta.transaction.Transactional;

@Service
public class DonHangServiceImpl implements DonHangService {
  @Autowired
  DonHangRepository donHangRepository;
  @Autowired
  ChiTietDonHangServiceImpl ctdhService;

  @Override
  public Page<DonHang> getAllDonHang(int page) {
    return donHangRepository.findAll(PageRequest.of(page, 10));
  }

  @Override
  public Page<DonHang> searchDonHang(String username, String diachi, String tensanpham, String tinhtrang,
      String thanhtoan, Integer tongtien, String from, String to, int page) {
    return donHangRepository.searchDonHang(username, diachi, tensanpham, tinhtrang, thanhtoan, tongtien, from, to,
        PageRequest.of(page, 10));
  }

  @Override
  public Optional<DonHang> findDonHangById(int madonhang) {
    return donHangRepository.findById(madonhang);
  }

  @Override
  @Transactional
  public void addDonHang(DonHang donHang) {
    if (donHang.getItems() != null) {
      if (donHang.getItems().size() > 0) {
        DonHang result = donHangRepository.save(donHang);
    
        for (ChiTietDonHang item : donHang.getItems()) {
          item.setDonhang(result);
          item.setNGAYTHEM(result.getNGAYTHEM());
    
          ctdhService.saveCTDH(item);
        }
      } else {
        throw new EmptyOrNullListException("Phải có ít nhất 1 đơn hàng!");
      }
    }
  }

  @Override
  @Transactional
  public void updateDonHang(DonHang donHang) {
    donHangRepository.save(donHang);
  }

  @Override
  @Transactional
  public void deleteDonHang(int madonhang) {
    donHangRepository.deleteById(madonhang);
  }

  @Override
  public Page<DonHang> getMyDonHang(String searchStr, String tinhtrang, String thanhtoan, String username, String from,
      String to, int page) {
    return donHangRepository.getMyDonHang(searchStr, tinhtrang, thanhtoan, username, from, to,
        PageRequest.of(page, 10));
  }

  @Override
  public void removeSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();
      DonHang donhang = ctdh.getDonhang();

      donhang.setTongTien(donhang.getTongTien() - (ctdh.getGiaTien() * ctdh.getSoLuong()));

      donHangRepository.save(donhang);
      ctdhService.deleteCTDH(maCTDH);
    }
  }

  @Override
  public void addSP(int madonhang, ChiTietDonHang ctdh) {
    Optional<DonHang> data = donHangRepository.findById(madonhang);

    if (data.isPresent()) {
      DonHang donhang = data.get();
      ctdh.setDonhang(donhang);
      Integer oldTonTien = donhang.getTongTien();
      donhang.setTongTien(oldTonTien + (ctdh.getGiaTien() * ctdh.getSoLuong()));

      donHangRepository.save(donhang);
      ctdhService.saveCTDH(ctdh);
    }
  }

  @Override
  public void decSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();

      if (ctdh.getSoLuong() > 1) {
        DonHang donhang = ctdh.getDonhang();
        ctdh.setSoLuong(ctdh.getSoLuong() - 1);
        donhang.setTongTien(donhang.getTongTien() - ctdh.getGiaTien());

        donHangRepository.save(donhang);
        ctdhService.saveCTDH(ctdh);
      } else {
        removeSP(maCTDH);
      }
    }
  }

  @Override
  public void incSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();

      DonHang donhang = ctdh.getDonhang();
      ctdh.setSoLuong(ctdh.getSoLuong() + 1);
      donhang.setTongTien(donhang.getTongTien() + ctdh.getGiaTien());

      donHangRepository.save(donhang);
      ctdhService.saveCTDH(ctdh);
    }
  }
}
