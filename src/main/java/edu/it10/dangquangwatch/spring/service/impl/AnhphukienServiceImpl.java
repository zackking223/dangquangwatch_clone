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
import edu.it10.dangquangwatch.spring.service.ImageUploadService;

@Service
public class AnhphukienServiceImpl implements AnhphukienService {
  @Autowired
  AnhphukienRepository anhphukienRepository;
  @Autowired
  ImageUploadService imageUploadService;

  @Override
  public List<Anhphukien> getAll() {
    return anhphukienRepository.findAll();
  }

  @Override
  public void save(Anhphukien anhphukien) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhphukien.getFile());

    anhphukien.setTenanh(uploadResult.get("public_id"));
    anhphukien.setUrl(uploadResult.get("url"));

    anhphukienRepository.save(anhphukien);
  }

  @Override
  public void delete(Integer maanh) throws IOException {
    Optional<Anhphukien> data = anhphukienRepository.findById(maanh);

    if (data.isPresent()) {
      if (data.get().isCloud()) {
        String tenanh = data.get().getTenanh();
        imageUploadService.deleteImage(tenanh);
      }
      anhphukienRepository.deleteById(maanh);
    }
  }

  @Override
  public Optional<Anhphukien> findById(Integer maanh) {
    return anhphukienRepository.findById(maanh);
  }

}
