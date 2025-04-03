package edu.it10.vuquangdung.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.Loai;
import edu.it10.vuquangdung.spring.repository.LoaiRepository;
import edu.it10.vuquangdung.spring.service.LoaiService;

@Service
public class LoaiServiceImpl implements LoaiService {
    @Autowired
    LoaiRepository loaiRepository;

    @Override
    public List<Loai> getAll() {
        return loaiRepository.findAll();
    }

    @Override
    public Page<Loai> search(String searchStr, int pageNum) {
        return loaiRepository.findByIdContains(searchStr, PageRequest.of(pageNum, 10));
    }

    @Override
    public Loai save(Loai loai) {
        return loaiRepository.save(loai);
    }

    @Override
    public Optional<Loai> findById(String id) {
        return loaiRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        loaiRepository.deleteById(id);
    }

    @Override
    public List<String> getAllId() {
        return loaiRepository.getAllId();
    }
}
