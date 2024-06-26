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
  @Autowired private PhuKienRepository phuKienRepository;

  @Override
  public List<PhuKien> getAllPhuKien() {
    return (List<PhuKien>) phuKienRepository.findAll();
  }

  @Override
  public Page<PhuKien> searchPhuKien(String searchStr, Integer pageNum) {
    return phuKienRepository.searchPhuKien(searchStr, PageRequest.of(pageNum, 10));
  }

  @Override
  public PhuKien savePhuKien(PhuKien phuKien) {
    return phuKienRepository.save(phuKien);
  }

  @Override
  public void deletePhuKien(Integer maPhuKien) {
    phuKienRepository.deleteById(maPhuKien);
  }

  @Override
  public Optional<PhuKien> findPhuKienById(Integer maPhuKien) {
    return phuKienRepository.findById(maPhuKien);
  }
}
