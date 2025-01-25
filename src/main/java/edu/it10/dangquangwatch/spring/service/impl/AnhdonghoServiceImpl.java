package edu.it10.dangquangwatch.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.Anhdongho;
import edu.it10.dangquangwatch.spring.repository.AnhdonghoRepository;
import edu.it10.dangquangwatch.spring.service.AnhdonghoService;
import edu.it10.dangquangwatch.spring.service.ImageUploadService;

@Service
public class AnhdonghoServiceImpl implements AnhdonghoService {
  @Autowired AnhdonghoRepository anhdonghoRepository;
  @Autowired ImageUploadService imageUploadService;

  @Override
  public List<Anhdongho> getAll() {
    return anhdonghoRepository.findAll();
  }

  @Override
  public void save(Anhdongho anhdongho) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhdongho.getFile());
    
    anhdongho.setTenanh(uploadResult.get("public_id"));
    anhdongho.setUrl(uploadResult.get("url"));

    anhdonghoRepository.save(anhdongho);
  }

  @Override
  public void delete(Integer maanh) throws IOException {
    Optional<Anhdongho> data = anhdonghoRepository.findById(maanh);

    if (data.isPresent()) {
      if (data.get().isCloud()) {
        String tenanh = data.get().getTenanh();
        imageUploadService.deleteImage(tenanh);
      }
      anhdonghoRepository.deleteById(maanh);
    }
  }

  @Override
  public Optional<Anhdongho> findById(Integer maanh) {
    return anhdonghoRepository.findById(maanh);
  }
  
}
