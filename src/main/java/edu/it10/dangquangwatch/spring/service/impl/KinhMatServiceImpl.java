package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.repository.KinhMatRepository;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import jakarta.validation.Valid;

@Service
public class KinhMatServiceImpl implements KinhMatService {
  @Autowired
  private KinhMatRepository kinhMatRepository;

  @Override
  public List<KinhMat> getAll() {
    return (List<KinhMat>) kinhMatRepository.findAll();
  }

  @Override
  public Page<KinhMat> search(String searchStr, String from, String to, Integer pageNum) {
    return kinhMatRepository.searchKinhMat(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<KinhMat> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return kinhMatRepository.searchKinhMatActive(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public KinhMat save(@Valid KinhMat kinhMat) {
    if (kinhMat.getMaKinhMat() == null) {
      kinhMat.setKichhoat(1);
      kinhMat.setSoluongdatmua(0);
      kinhMat.setSoLuong(0);
    }

    return kinhMatRepository.save(kinhMat);
  }

  @Override
  public void activate(Integer maKinhMat) {
    Optional<KinhMat> opt = kinhMatRepository.findById(maKinhMat);

    if (opt.isPresent()) {
      KinhMat kinhMat = opt.get();
      kinhMat.setKichhoat(1);

      save(kinhMat);
    }
  }

  @Override
  public void deactivate(Integer maKinhMat) {
    Optional<KinhMat> opt = kinhMatRepository.findById(maKinhMat);

    if (opt.isPresent()) {
      KinhMat kinhMat = opt.get();
      kinhMat.setKichhoat(0);

      save(kinhMat);
    }
  }

  @Override
  public Optional<KinhMat> findById(Integer maKinhMat) {
    return kinhMatRepository.findById(maKinhMat);
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<KinhMat> opt = kinhMatRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng nhập phải là số nguyên dương",
          ErrorEnum.IMPORT,
          "/admin/kinhmat/nhap?id=" + id);
    }

    if (opt.isPresent()) {
      KinhMat kinhMat = opt.get();
      kinhMat.setSoLuong(kinhMat.getSoLuong() + amount);
      save(kinhMat);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/kinhmat/");
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<KinhMat> opt = kinhMatRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng xuất phải là số nguyên dương",
          ErrorEnum.EXPORT,
          "/admin/kinhmat/xuat?id=" + id);
    }

    if (opt.isPresent()) {
      KinhMat kinhMat = opt.get();
      kinhMat.setSoLuong(kinhMat.getSoLuong() - amount);
      save(kinhMat);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/kinhmat/");
    }
  }
}
