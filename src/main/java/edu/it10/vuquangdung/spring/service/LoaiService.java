package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.vuquangdung.spring.entity.Loai;

public interface LoaiService {
    List<Loai> getAll();
    List<String> getAllId();
    Page<Loai> search(String searchStr, int pageNum);
    
    Loai save(Loai loai);
    
    Optional<Loai> findById(String id);

    void delete(String id);
}
