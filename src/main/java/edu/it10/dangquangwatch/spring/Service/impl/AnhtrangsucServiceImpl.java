package edu.it10.dangquangwatch.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;
import edu.it10.dangquangwatch.spring.repository.AnhtrangsucRepository;
import edu.it10.dangquangwatch.spring.service.AnhtrangsucService;

@Service
public class AnhtrangsucServiceImpl implements AnhtrangsucService {
  @Autowired AnhtrangsucRepository anhtrangsucRepository;
  @Autowired ImageUploadServiceImpl imageUploadService;

  @Override
  public List<Anhtrangsuc> getAllAnhtrangsuc() {
    return anhtrangsucRepository.findAll();
  }

  @Override
  public void saveAnhtrangsuc(Anhtrangsuc anhtrangsuc) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhtrangsuc.getFile());
    
    anhtrangsuc.setTenanh(uploadResult.get("public_id"));
    anhtrangsuc.setUrl(uploadResult.get("url"));

    anhtrangsucRepository.save(anhtrangsuc);
  }

  @Override
  public void deleteAnhtrangsuc(Integer maanh) {
    anhtrangsucRepository.deleteById(maanh);
  }

  @Override
  public Optional<Anhtrangsuc> findAnhtrangsucById(Integer maanh) {
    return anhtrangsucRepository.findById(maanh);
  }
  
}
