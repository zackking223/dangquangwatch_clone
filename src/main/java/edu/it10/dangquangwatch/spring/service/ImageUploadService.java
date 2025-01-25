package edu.it10.dangquangwatch.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
  public Map<String, String> uploadImage(MultipartFile file) throws IOException;
  public List<Map<String, String>> uploadImages(List<MultipartFile> files) throws IOException;
  public Map<String, String> deleteImage(String publicId) throws IOException;
}
