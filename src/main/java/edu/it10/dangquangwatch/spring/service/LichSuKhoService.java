package edu.it10.dangquangwatch.spring.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.LichSuKho;
import edu.it10.dangquangwatch.spring.entity.enumeration.KhoAction;
import edu.it10.dangquangwatch.spring.repository.LichSuKhoRepository;

@Service
public class LichSuKhoService {
  @Autowired
  private LichSuKhoRepository lichSuKhoRepository;
  @Autowired
  private ThongKeService thongKeService;

  public Page<LichSuKho> search(String thongTin, String hanhDong, String nguoiThucHien, String from, String to, int pageNum) {
    return lichSuKhoRepository.searchLichSuKho(thongTin, hanhDong, nguoiThucHien, from, to, PageRequest.of(pageNum, 10));
  }

  public void NhapKho(String thongTin, String username, BigDecimal giaTien) {
    LichSuKho lichSuKho = new LichSuKho();
    lichSuKho.setHanhDong(KhoAction.Import);
    lichSuKho.setThongTin(thongTin);
    lichSuKho.setNguoiThucHien(username);
    lichSuKhoRepository.save(lichSuKho);

    thongKeService.incChiPhi(giaTien);
  }

  public void XuatKho(String thongTin, String username) {
    LichSuKho lichSuKho = new LichSuKho();
    lichSuKho.setHanhDong(KhoAction.Export);
    lichSuKho.setThongTin(thongTin);
    lichSuKho.setNguoiThucHien(username);
    lichSuKhoRepository.save(lichSuKho);
  }
}
