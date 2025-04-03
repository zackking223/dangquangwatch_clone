package edu.it10.vuquangdung.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.MauSac;
import edu.it10.vuquangdung.spring.repository.MauSacRepository;
import edu.it10.vuquangdung.spring.service.MauSacService;
import jakarta.transaction.Transactional;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Page<MauSac> search(String searchStr, int pageNum) {
        return mauSacRepository.search(searchStr, null);
    }

    @Override
    @Transactional
    public MauSac save(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public Optional<MauSac> findById(String id) {
        return mauSacRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        mauSacRepository.deleteById(id);
    }
}
