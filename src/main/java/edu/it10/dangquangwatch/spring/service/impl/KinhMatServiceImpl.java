package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.repository.KinhMatRepository;
import edu.it10.dangquangwatch.spring.service.KinhMatService;

@Service
public class KinhMatServiceImpl implements KinhMatService {
  @Autowired private KinhMatRepository kinhMatRepository;

  @Override
  public List<KinhMat> getAllKinhMat() {
    return (List<KinhMat>) kinhMatRepository.findAll();
  }

  @Override
  public List<KinhMat> searchKinhMat(String tenSanPham) {
    return kinhMatRepository.findByTenSanPham(tenSanPham);
  }

  @Override
  public void saveKinhMat(KinhMat kinhMat) {
    kinhMatRepository.save(kinhMat);
  }

  @Override
  public void deleteKinhMat(Integer maKinhMat) {
    kinhMatRepository.deleteById(maKinhMat);
  }

  @Override
  public Optional<KinhMat> findKinhMatById(Integer maKinhMat) {
    return kinhMatRepository.findById(maKinhMat);
  }
  
}
