package edu.it10.vuquangdung.spring.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.repository.AnhSanPhamRepository;
import edu.it10.vuquangdung.spring.service.AnhSanPhamService;
import edu.it10.vuquangdung.spring.service.ImageUploadService;

@Service
public class AnhSanPhamServiceImpl implements AnhSanPhamService {
    @Autowired
    AnhSanPhamRepository anhSanPhamRepository;
    @Autowired
    ImageUploadService imageUploadService;

    @Override
    public List<AnhSanPham> getAll(SanPham sanPham) {
        return anhSanPhamRepository.findBySanPham(sanPham);
    }

    @Override
    public void save(AnhSanPham anhSanPham) throws IOException {
        Map<String, String> uploadResult = imageUploadService.uploadImage(anhSanPham.getFile());

        anhSanPham.setTenanh(uploadResult.get("public_id"));
        anhSanPham.setUrl(uploadResult.get("url"));

        anhSanPhamRepository.save(anhSanPham);
    }

    @Override
    public void delete(Integer id) throws IOException {
        Optional<AnhSanPham> data = anhSanPhamRepository.findById(id);

        if (data.isPresent()) {
            if (data.get().isCloud()) {
                String tenanh = data.get().getTenanh();
                imageUploadService.deleteImage(tenanh);
            }
            anhSanPhamRepository.deleteById(id);
        }
    }

    @Override
    public Optional<AnhSanPham> findById(Integer id) {
        return anhSanPhamRepository.findById(id);
    }
}
