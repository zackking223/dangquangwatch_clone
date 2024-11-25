package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.repository.PhuKienRepository;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import jakarta.validation.Valid;

@Service
public class PhuKienServiceImpl implements PhuKienService {
  @Autowired
  private PhuKienRepository phuKienRepository;

  @Override
  public List<PhuKien> getAll() {
    return (List<PhuKien>) phuKienRepository.findAll();
  }

  @Override
  public Page<PhuKien> search(String searchStr, String from, String to, Integer pageNum) {
    return phuKienRepository.searchPhuKien(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<PhuKien> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return phuKienRepository.searchActivePhuKien(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public PhuKien save(@Valid PhuKien phuKien) {
    if (phuKien.getMaPhuKien() == null) {
      phuKien.setKichhoat(1);
      phuKien.setSoluongdatmua(0);
      phuKien.setSoLuong(0);
    }

    return phuKienRepository.save(phuKien);
  }

  @Override
  public void activate(Integer maPhuKien) {
    Optional<PhuKien> opt = phuKienRepository.findById(maPhuKien);
    
    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();

      phuKien.setKichhoat(1);

      phuKienRepository.save(phuKien);
    }
  }

  @Override
  public void deactivate(Integer maPhuKien) {
    Optional<PhuKien> opt = phuKienRepository.findById(maPhuKien);
    
    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();

      phuKien.setKichhoat(0);

      phuKienRepository.save(phuKien);
    }
  }

  @Override
  public Optional<PhuKien> findById(Integer maPhuKien) {
    return phuKienRepository.findById(maPhuKien);
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<PhuKien> opt = phuKienRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng nhập phải là số nguyên dương",
          ErrorEnum.IMPORT,
          "/admin/phukien/nhap?id=" + id);
    }

    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();
      phuKien.setSoLuong(phuKien.getSoLuong() + amount);
      phuKienRepository.save(phuKien);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/phukien/");
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<PhuKien> opt = phuKienRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng xuất phải là số nguyên dương",
          ErrorEnum.EXPORT,
          "/admin/phukien/xuat?id=" + id);
    }

    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();
      phuKien.setSoLuong(phuKien.getSoLuong() - amount);
      phuKienRepository.save(phuKien);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/phukien/");
    }
  }

  @Override
  public List<PhuKien> search(String searchStr) {
    return phuKienRepository.search(searchStr);
  }
}
