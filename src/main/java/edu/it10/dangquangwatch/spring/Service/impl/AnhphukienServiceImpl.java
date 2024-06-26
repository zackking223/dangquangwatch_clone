package edu.it10.dangquangwatch.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.Anhphukien;
import edu.it10.dangquangwatch.spring.repository.AnhphukienRepository;
import edu.it10.dangquangwatch.spring.service.AnhphukienService;

@Service
public class AnhphukienServiceImpl implements AnhphukienService {
  @Autowired AnhphukienRepository anhphukienRepository;
  @Autowired ImageUploadServiceImpl imageUploadService;

  @Override
  public List<Anhphukien> getAllAnhphukien() {
    return anhphukienRepository.findAll();
  }

  @Override
  public void saveAnhphukien(Anhphukien anhphukien) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhphukien.getFile());
    
    anhphukien.setTenanh(uploadResult.get("public_id"));
    anhphukien.setUrl(uploadResult.get("url"));

    anhphukienRepository.save(anhphukien);
  }

  @Override
  public void deleteAnhphukien(Integer maanh) {
    anhphukienRepository.deleteById(maanh);
  }

  @Override
  public Optional<Anhphukien> findAnhphukienById(Integer maanh) {
    return anhphukienRepository.findById(maanh);
  }
  
}
