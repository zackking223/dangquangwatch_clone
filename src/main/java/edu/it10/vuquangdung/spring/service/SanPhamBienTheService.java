package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

public interface SanPhamBienTheService {
    List<SanPhamBienThe> getAll(SanPham sanPham);
    List<SanPhamBienThe> getAvaiable(SanPham sanPham);

    Optional<SanPhamBienThe> findById(Integer id);
    Optional<SanPhamBienThe> findByIdWithLock(Integer id);

    SanPhamBienThe save(SanPhamBienThe sanPhamBienThe);

    void incAmount(Integer amount, Integer id);
    void decAmount(Integer amount, Integer id);
    void activate(Integer id);
    void deactivate(Integer id);
    void delete(Integer id);
}
