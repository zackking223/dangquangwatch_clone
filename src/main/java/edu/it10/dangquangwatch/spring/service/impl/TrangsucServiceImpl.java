package edu.it10.dangquangwatch.spring.service.impl;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.repository.TrangsucRepository;
import edu.it10.dangquangwatch.spring.service.TrangsucService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrangsucServiceImpl implements TrangsucService {
  @Autowired
  private TrangsucRepository trangsucRepository;

  @Override
  public List<Trangsuc> getAll() {
    return (List<Trangsuc>) trangsucRepository.findAll();
  }

  @Override
  public Trangsuc save(@Valid Trangsuc trangsuc) {
    if (trangsuc.getMatrangsuc() == null) {
      trangsuc.setKichhoat(1);
      trangsuc.setSoluongdatmua(0);
      trangsuc.setSoluong(0);
    }
    
    return trangsucRepository.save(trangsuc);
  }

  @Override
  public void activate(Integer matrangsuc) {
    Optional<Trangsuc> opt = trangsucRepository.findById(matrangsuc);

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();
      trangsuc.setKichhoat(1);
      save(trangsuc);
    }
  }

  @Override
  public void deactivate(Integer matrangsuc) {
    Optional<Trangsuc> opt = trangsucRepository.findById(matrangsuc);

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();
      trangsuc.setKichhoat(0);
      save(trangsuc);
    }
  }

  @Override
  public Optional<Trangsuc> findById(Integer matrangsuc) {
    return trangsucRepository.findById(matrangsuc);
  }

  @Override
  public Page<Trangsuc> search(String searchStr, String from, String to, Integer pageNum) {
    return trangsucRepository.searchTrangsuc(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<Trangsuc> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return trangsucRepository.searchActiveTrangsuc(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Trangsuc> opt = trangsucRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng nhập phải là số nguyên dương",
          ErrorEnum.IMPORT,
          "/admin/trangsuc/nhap?id=" + id);
    }

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();
      trangsuc.setSoluong(trangsuc.getSoluong() + amount);
      save(trangsuc);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/trangsuc/");
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Trangsuc> opt = trangsucRepository.findById(id);

    if (amount <= 0) {
      throw new ServiceException(
          "Số lượng xuất phải là số nguyên dương",
          ErrorEnum.EXPORT,
          "/admin/trangsuc/xuat?id=" + id);
    }

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();

      if (trangsuc.getSoluong() < amount || trangsuc.getSoluong() == 0) {
        throw new ServiceException(
            "Số lượng xuất quá số lượng của sản phẩm",
            ErrorEnum.EXPORT,
            "/admin/trangsuc/xuat?id=" + id);
      }

      trangsuc.setSoluong(trangsuc.getSoluong() - amount);
      save(trangsuc);
    } else {
      throw new ServiceException(
          "Không tìm thấy sản phẩm",
          ErrorEnum.INDEX,
          "/admin/trangsuc/");
    }
  }

  @Override
  public List<Trangsuc> search(String searchStr) {
    return trangsucRepository.search(searchStr);
  }

  @Override
  public Optional<Trangsuc> findByIdWithLock(Integer matrangsuc) {
    return trangsucRepository.findByIdWithLock(matrangsuc);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    trangsucRepository.deleteById(id);
  }
}