package edu.it10.vuquangdung.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.SanPham;

public interface AnhSanPhamService {
    List<AnhSanPham> getAll(SanPham sanPham);
    void save(AnhSanPham anhSanPham) throws IOException;
    void delete(Integer id) throws IOException;
    Optional<AnhSanPham> findById(Integer id);
}
