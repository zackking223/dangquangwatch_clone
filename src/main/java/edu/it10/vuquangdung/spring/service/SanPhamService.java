package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.it10.vuquangdung.spring.entity.SanPham; 

public interface SanPhamService {
    List<SanPham> getAll();
    List<SanPham> search(String searchStr);
    Page<SanPham> search(String searchStr, String from, String to, int pageNum);
    Page<SanPham> search(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTienFrom,
            Integer giaTienTo,
            String from,
            String to,
            Pageable pageable);
    Page<SanPham> searchAvaiable(String searchStr, String from, String to, int pageNum);

    SanPham save(SanPham sanPham);

    Optional<SanPham> findById(Integer id);
    Optional<SanPham> findByIdWithLock(Integer id);
 
    void activate(Integer id);
    void deactivate(Integer id);
    void delete(Integer id);
}
