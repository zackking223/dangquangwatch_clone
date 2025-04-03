package edu.it10.vuquangdung.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import edu.it10.vuquangdung.spring.entity.MauSac;

public interface MauSacService {
    List<MauSac> getAll();
    Page<MauSac> search(String searchStr, int pageNum);

    MauSac save(MauSac mauSac);

    Optional<MauSac> findById(String id);

    void delete(String id);
}
