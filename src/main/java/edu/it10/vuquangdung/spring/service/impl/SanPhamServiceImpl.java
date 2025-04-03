package edu.it10.vuquangdung.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.repository.SanPhamRepository;
import edu.it10.vuquangdung.spring.service.SanPhamService;
import jakarta.transaction.Transactional;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> search(String searchStr) {
        return sanPhamRepository.findByTenContains(searchStr);
    }

    @Override
    public Page<SanPham> search(String searchStr, String from, String to, int pageNum) {
        return sanPhamRepository.search(searchStr, from, to, PageRequest.of(pageNum, 10));
    }

    @Override
    public Page<SanPham> searchAvaiable(String searchStr, String from, String to, int pageNum) {
        return sanPhamRepository.searchActiveSanPham(searchStr, from, to, PageRequest.of(pageNum, 10));
    }

    @Override
    public Optional<SanPham> findById(Integer id) {
        return sanPhamRepository.findById(id);
    }

    @Override
    public Optional<SanPham> findByIdWithLock(Integer id) {
        return sanPhamRepository.findByIdWithLock(id);
    }

    @Override
    @Transactional
    public SanPham save(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void activate(Integer id) {
        Optional<SanPham> opt = sanPhamRepository.findById(id);

        if (opt.isPresent()) {
            SanPham sanPham = opt.get();
            sanPham.setKichHoat(1);
            save(sanPham);
        }
    }

    @Override
    public void deactivate(Integer id) {
        Optional<SanPham> opt = sanPhamRepository.findById(id);

        if (opt.isPresent()) {
            SanPham sanPham = opt.get();
            sanPham.setKichHoat(0);
            save(sanPham);
        }
    }

    @Override
    public void delete(Integer id) {
        sanPhamRepository.deleteById(id);
    }
}
