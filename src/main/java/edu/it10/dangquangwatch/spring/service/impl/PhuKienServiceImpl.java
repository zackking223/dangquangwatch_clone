package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.repository.PhuKienRepository;
import edu.it10.dangquangwatch.spring.service.PhuKienService;

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
  public PhuKien savePhuKien(PhuKien phuKien) {
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

    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();
      phuKien.setSoLuong(phuKien.getSoLuong() + amount);
      phuKienRepository.save(phuKien);
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<PhuKien> opt = phuKienRepository.findById(id);

    if (opt.isPresent()) {
      PhuKien phuKien = opt.get();
      phuKien.setSoLuong(phuKien.getSoLuong() - amount);
      phuKienRepository.save(phuKien);
    }
  }
}
