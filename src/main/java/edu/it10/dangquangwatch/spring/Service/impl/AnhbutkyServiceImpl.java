package edu.it10.dangquangwatch.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.Anhbutky;
import edu.it10.dangquangwatch.spring.repository.AnhbutkyRepository;
import edu.it10.dangquangwatch.spring.service.AnhbutkyService;

@Service
public class AnhbutkyServiceImpl implements AnhbutkyService {
  @Autowired AnhbutkyRepository anhbutkyRepository;
  @Autowired ImageUploadServiceImpl imageUploadService;

  @Override
  public List<Anhbutky> getAllAnhbutky() {
    return anhbutkyRepository.findAll();
  }

  @Override
  public void saveAnhbutky(Anhbutky anhbutky) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhbutky.getFile());
    
    anhbutky.setTenanh(uploadResult.get("public_id"));
    anhbutky.setUrl(uploadResult.get("url"));

    anhbutkyRepository.save(anhbutky);
  }

  @Override
  public void deleteAnhbutky(Integer maanh) {
    anhbutkyRepository.deleteById(maanh);
  }

  @Override
  public Optional<Anhbutky> findAnhbutkyById(Integer maanh) {
    return anhbutkyRepository.findById(maanh);
  }
  
}
