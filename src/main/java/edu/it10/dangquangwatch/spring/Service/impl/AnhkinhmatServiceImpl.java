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
import edu.it10.dangquangwatch.spring.service.ImageUploadService;

@Service
public class AnhkinhmatServiceImpl implements AnhkinhmatService {
  @Autowired
  AnhkinhmatRepository anhkinhmatRepository;
  @Autowired
  ImageUploadService imageUploadService;

  @Override
  public List<Anhkinhmat> getAll() {
    return anhkinhmatRepository.findAll();
  }

  @Override
  public void save(Anhkinhmat anhkinhmat) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhkinhmat.getFile());

    anhkinhmat.setTenanh(uploadResult.get("public_id"));
    anhkinhmat.setUrl(uploadResult.get("url"));

    anhkinhmatRepository.save(anhkinhmat);
  }

  @Override
  public void delete(Integer maanh) throws IOException {
    Optional<Anhkinhmat> data = anhkinhmatRepository.findById(maanh);

    if (data.isPresent()) {
      if (data.get().isCloud()) {
        String tenanh = data.get().getTenanh();
        imageUploadService.deleteImage(tenanh);
      }
      anhkinhmatRepository.deleteById(maanh);
    }
  }

  @Override
  public Optional<Anhkinhmat> findById(Integer maanh) {
    return anhkinhmatRepository.findById(maanh);
  }

}
