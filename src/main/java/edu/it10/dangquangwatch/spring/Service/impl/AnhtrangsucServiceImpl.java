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
import edu.it10.dangquangwatch.spring.service.ImageUploadService;

@Service
public class AnhtrangsucServiceImpl implements AnhtrangsucService {
  @Autowired
  AnhtrangsucRepository anhtrangsucRepository;
  @Autowired
  ImageUploadService imageUploadService;

  @Override
  public List<Anhtrangsuc> getAll() {
    return anhtrangsucRepository.findAll();
  }

  @Override
  public void save(Anhtrangsuc anhtrangsuc) throws IOException {
    Map<String, String> uploadResult = imageUploadService.uploadImage(anhtrangsuc.getFile());

    anhtrangsuc.setTenanh(uploadResult.get("public_id"));
    anhtrangsuc.setUrl(uploadResult.get("url"));

    anhtrangsucRepository.save(anhtrangsuc);
  }

  @Override
  public void delete(Integer maanh) throws IOException {
    Optional<Anhtrangsuc> data = anhtrangsucRepository.findById(maanh);

    if (data.isPresent()) {
      if (data.get().isCloud()) {
        String tenanh = data.get().getTenanh();
        imageUploadService.deleteImage(tenanh);
      }
      anhtrangsucRepository.deleteById(maanh);
    }
  }

  @Override
  public Optional<Anhtrangsuc> findById(Integer maanh) {
    return anhtrangsucRepository.findById(maanh);
  }

}
