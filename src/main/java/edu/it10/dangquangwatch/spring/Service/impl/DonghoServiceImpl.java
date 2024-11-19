package edu.it10.dangquangwatch.spring.service.impl;

import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.repository.DonghoRepository;
import edu.it10.dangquangwatch.spring.service.DonghoService;

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
  public Dongho save(Dongho dongho) {
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
    return donghoRepository.findByTendonghoContains(tendongho, from, to, pageable);
  }

  @Override
  public Page<Dongho> searchAvaiable(String tendongho, String from, String to, int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    return donghoRepository.findByTendonghoContainsAndAvaiable(tendongho, from, to, pageable);
  }

  @Override
  public Page<Dongho> search(String searchStr, Dongho searchData, String from, String to, int pageNum) {
    return donghoRepository.searchDongHo(searchStr, searchData, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Dongho> opt = donghoRepository.findById(id);

    if (opt.isPresent()) {
      Dongho dongho = opt.get();
      dongho.setSoluong(dongho.getSoluong() + amount);
      save(dongho);
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Dongho> opt = donghoRepository.findById(id);

    if (opt.isPresent()) {
      Dongho dongho = opt.get();
      dongho.setSoluong(dongho.getSoluong() - amount);
      save(dongho);
    }
  }
}