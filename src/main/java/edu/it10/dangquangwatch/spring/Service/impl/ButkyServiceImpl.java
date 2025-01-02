package edu.it10.dangquangwatch.spring.service.impl;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.repository.ButkyRepository;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ButkyServiceImpl implements ButkyService {
  @Autowired
  private ButkyRepository butkyRepository;

  @Override
  public List<Butky> getAll() {
    return (List<Butky>) butkyRepository.findAll();
  }

  @Override
  public Butky save(@Valid Butky butky) {
    if (butky.getMabutky() == null) {
      butky.setKichhoat(1);
      butky.setSoluongdatmua(0);
      butky.setSoluong(0);
    }

    return butkyRepository.save(butky);
  }

  @Override
  public void activate(Integer mabutky) {
    Optional<Butky> opt = butkyRepository.findById(mabutky);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setKichhoat(1);
      save(butky);
    }
  }

  @Override
  public void deactivate(Integer mabutky) {
    Optional<Butky> opt = butkyRepository.findById(mabutky);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setKichhoat(0);
      save(butky);
    }
  }

  @Override
  public Optional<Butky> findById(Integer mabutky) {
    return butkyRepository.findById(mabutky);
  }

  @Override
  public Page<Butky> search(String searchStr, String from, String to, Integer pageNum) {
    return butkyRepository.searchButKy(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<Butky> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return butkyRepository.searchActiveButKy(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Butky> opt = butkyRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng nhập phải là số nguyên dương",
          ErrorEnum.IMPORT,
          "/admin/butky/nhap?id=" + id);
    }

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setSoluong(butky.getSoluong() + amount);
      save(butky);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/butky/");
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Butky> opt = butkyRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng xuất phải là số nguyên dương",
          ErrorEnum.EXPORT,
          "/admin/butky/xuat?id=" + id);
    }

    if (opt.isPresent()) {
      Butky butky = opt.get();

      if (butky.getSoluong() < amount || butky.getSoluong() == 0) {
        throw new ServiceException(
            "Số lượng xuất quá số lượng của sản phẩm",
            ErrorEnum.EXPORT,
            "/admin/butky/xuat?id=" + id);
      }
      
      butky.setSoluong(butky.getSoluong() - amount);
      save(butky);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/butky/");
    }
  }

  @Override
  public List<Butky> search(String searchStr) {
    return butkyRepository.search(searchStr);
  }

  @Override
  public Optional<Butky> findByIdWithLock(Integer mabutky) {
    return butkyRepository.findByIdWithLock(mabutky);
  }
}