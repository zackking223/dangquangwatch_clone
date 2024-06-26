package edu.it10.dangquangwatch.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;
import edu.it10.dangquangwatch.spring.repository.AnhkinhmatRepository;
import edu.it10.dangquangwatch.spring.service.AnhkinhmatService;

@Service
public class AnhkinhmatServiceImpl implements AnhkinhmatService {
  @Autowired AnhkinhmatRepository anhkinhmatRepository;
  @Autowired ImageUploadServiceImpl imageUploadService;

  @Override
  public List<Anhkinhmat> getAllAnhkinhmat() {
    return anhkinhmatRepository.findAll();
  }

  @Override
  public void saveAnhkinhmat(Anhkinhmat anhkinhmat) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhkinhmat.getFile());
    
    anhkinhmat.setTenanh(uploadResult.get("public_id"));
    anhkinhmat.setUrl(uploadResult.get("url"));

    anhkinhmatRepository.save(anhkinhmat);
  }

  @Override
  public void deleteAnhkinhmat(Integer maanh) {
    anhkinhmatRepository.deleteById(maanh);
  }

  @Override
  public Optional<Anhkinhmat> findAnhkinhmatById(Integer maanh) {
    return anhkinhmatRepository.findById(maanh);
  }
  
}
