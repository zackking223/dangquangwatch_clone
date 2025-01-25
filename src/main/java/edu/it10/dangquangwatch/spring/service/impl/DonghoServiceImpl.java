package edu.it10.dangquangwatch.spring.service.impl;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.repository.DonghoRepository;
import edu.it10.dangquangwatch.spring.service.DonghoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonghoServiceImpl implements DonghoService {
  @Autowired
  private DonghoRepository donghoRepository;

  @Override
  public List<Dongho> getAllDongho() {
    return donghoRepository.findAll();
  }

  @Override
  public Dongho save(@Valid Dongho dongho) {
    if (dongho.getMadongho() == null) {
      dongho.setKichhoat(1);
      dongho.setSoluongdatmua(0);
      dongho.setSoluong(0);
    }

    return donghoRepository.save(dongho);
  }

  @Override
  public void activate(Integer madongho) {
    Optional<Dongho> opt = donghoRepository.findById(madongho);

    if (opt.isPresent()) {
      Dongho dongho = opt.get();
      dongho.setKichhoat(1);
      save(dongho);
    }
  }

  @Override
  public void deactivate(Integer madongho) {
    Optional<Dongho> opt = donghoRepository.findById(madongho);

    if (opt.isPresent()) {
      Dongho dongho = opt.get();
      dongho.setKichhoat(0);
      save(dongho);
    }
  }

  @Override
  public Optional<Dongho> findById(Integer madongho) {
    return donghoRepository.findById(madongho);
  }

  @Override
  public Page<Dongho> getAll(String tendongho, String from, String to, int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    return donghoRepository.searchDongho(tendongho, from, to, pageable);
  }

  @Override
  public Page<Dongho> searchAvaiable(String tendongho, String from, String to, int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    return donghoRepository.searchActiveDongho(tendongho, from, to, pageable);
  }

  @Override
  public Page<Dongho> search(String searchStr, Dongho searchData, String from, String to, int pageNum) {
    return donghoRepository.searchDongHo(searchStr, searchData, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Dongho> opt = donghoRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng nhập phải là số nguyên dương",
          ErrorEnum.IMPORT,
          "/admin/dongho/nhap?id=" + id);
    }

    if (opt.isPresent()) {
      Dongho dongho = opt.get();
      dongho.setSoluong(dongho.getSoluong() + amount);
      save(dongho);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/dongho/");
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Dongho> opt = donghoRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng xuất phải là số nguyên dương",
          ErrorEnum.EXPORT,
          "/admin/dongho/xuat?id=" + id);
    }

    if (opt.isPresent()) {
      Dongho dongho = opt.get();

      if (dongho.getSoluong() < amount || dongho.getSoluong() == 0) {
        throw new ServiceException(
            "Số lượng xuất quá số lượng của sản phẩm",
            ErrorEnum.EXPORT,
            "/admin/dongho/xuat?id=" + id);
      }

      dongho.setSoluong(dongho.getSoluong() - amount);
      save(dongho);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/dongho/");
    }
  }

  @Override
  public List<Dongho> search(String searchStr) {
    return donghoRepository.search(searchStr);
  }

  @Override
  public Optional<Dongho> findByIdWithLock(Integer id) {
    return donghoRepository.findByIdWithLock(id);
  }
}