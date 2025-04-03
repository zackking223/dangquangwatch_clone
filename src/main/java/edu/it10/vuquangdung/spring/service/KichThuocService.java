package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.vuquangdung.spring.entity.KichThuoc;

public interface KichThuocService {
    List<KichThuoc> getAll();
    List<String> getAllId();
    Page<KichThuoc> search(String searchStr, int pageNum);
    
    KichThuoc save(KichThuoc kichThuoc);
    
    Optional<KichThuoc> findById(String id);

    void delete(String id);
}
