package edu.it10.vuquangdung.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.KichThuoc;
import edu.it10.vuquangdung.spring.repository.KichThuocRepository;
import edu.it10.vuquangdung.spring.service.KichThuocService;
import jakarta.transaction.Transactional;

@Service
public class KichThuocServiceImpl implements KichThuocService {
    @Autowired
    KichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public Page<KichThuoc> search(String searchStr, int pageNum) {
        return kichThuocRepository.findByIdContains(searchStr, PageRequest.of(pageNum, 10));
    }

    @Override
    @Transactional
    public KichThuoc save(KichThuoc kichThuoc) {
        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public Optional<KichThuoc> findById(String id) {
        return kichThuocRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        kichThuocRepository.deleteById(id);
    }

    @Override
    public List<String> getAllId() {
        return kichThuocRepository.getAllId();
    }
}
