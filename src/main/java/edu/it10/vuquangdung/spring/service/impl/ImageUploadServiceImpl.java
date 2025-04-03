package edu.it10.vuquangdung.spring.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.it10.vuquangdung.spring.service.ImageUploadService;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
  @Autowired
  Cloudinary cloudinary;

  private static final List<String> ACCEPTED_IMAGE_TYPES = List.of("image/jpeg", "image/png", "image/gif", "image/bmp",
      "image/webp");

  @Override
  public Map<String, String> uploadImage(MultipartFile file) throws IOException {
    if (!isImage(file)) {
      throw new IllegalArgumentException("File is not an accepted image type");
    }

    var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "shop_quan_ao"));
    return Map.of(
        "url", (String) uploadResult.get("url"),
        "public_id", (String) uploadResult.get("public_id"),
        "original_filename", (String) uploadResult.get("original_filename"));
  }

  @Override
  public List<Map<String, String>> uploadImages(List<MultipartFile> files) throws IOException {
    List<Map<String, String>> uploadResults = new ArrayList<>();
    for (MultipartFile file : files) {
      var uploadResult = uploadImage(file);
      uploadResults.add(Map.of(
          "url", (String) uploadResult.get("url"),
          "public_id", (String) uploadResult.get("public_id"),
          "original_filename", (String) uploadResult.get("original_filename")));
    }
    return uploadResults;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, String> deleteImage(String publicId) throws IOException {
    return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
  }

  private boolean isImage(MultipartFile file) {
    return ACCEPTED_IMAGE_TYPES.contains(file.getContentType());
  }
}
