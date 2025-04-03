package edu.it10.vuquangdung.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.AppCustomException.ErrorEnum;
import edu.it10.vuquangdung.spring.AppCustomException.ServiceException;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;
import edu.it10.vuquangdung.spring.repository.SanPhamBienTheRepository;
import edu.it10.vuquangdung.spring.service.SanPhamBienTheService;
import jakarta.transaction.Transactional;

@Service
public class SanPhamBienTheServiceImpl implements SanPhamBienTheService {
    @Autowired
    SanPhamBienTheRepository sanPhamBienTheRepository;

    @Override
    public List<SanPhamBienThe> getAll(SanPham sanPham) {
        return sanPhamBienTheRepository.findAll(sanPham);
    }

    @Override
    public List<SanPhamBienThe> getAvaiable(SanPham sanPham) {
        return sanPhamBienTheRepository.findAllActive(sanPham);
    }

    @Override
    public Optional<SanPhamBienThe> findById(Integer id) {
        return sanPhamBienTheRepository.findById(id);
    }

    @Override
    public Optional<SanPhamBienThe> findByIdWithLock(Integer id) {
        return sanPhamBienTheRepository.findByIdWithLock(id);
    }

    @Override
    @Transactional
    public SanPhamBienThe save(SanPhamBienThe sanPhamBienThe) {
        return sanPhamBienTheRepository.save(sanPhamBienThe);
    }

    @Override
    public void incAmount(Integer amount, Integer id) {
        Optional<SanPhamBienThe> opt = sanPhamBienTheRepository.findById(id);

        if (amount <= 0) {
            throw new ServiceException(
                    "Số lượng nhập phải là số nguyên dương",
                    ErrorEnum.IMPORT,
                    "/admin/sanpham/nhap?id=" + id);
        }

        if (opt.isPresent()) {
            SanPhamBienThe sanPham = opt.get();
            sanPham.setSoLuong(sanPham.getSoLuong() + amount);
            save(sanPham);
        } else {
            throw new ServiceException(
                    "Không tìm thấy sản phẩm",
                    ErrorEnum.INDEX,
                    "/admin/sanpham/");
        }
    }

    @Override
    public void decAmount(Integer amount, Integer id) {
        Optional<SanPhamBienThe> opt = sanPhamBienTheRepository.findById(id);

        if (amount <= 0) {
            throw new ServiceException(
                    "Số lượng xuất phải là số nguyên dương",
                    ErrorEnum.EXPORT,
                    "/admin/sanpham/xuat?id=" + id);
        }

        if (opt.isPresent()) {
            SanPhamBienThe sanPham = opt.get();

            if (sanPham.getSoLuong() < amount || sanPham.getSoLuong() == 0) {
                throw new ServiceException(
                        "Số lượng xuất quá số lượng của sản phẩm",
                        ErrorEnum.EXPORT,
                        "/admin/sanpham/xuat?id=" + id);
            }

            sanPham.setSoLuong(sanPham.getSoLuong() - amount);
            save(sanPham);
        } else {
            throw new ServiceException(
                    "Không tìm thấy sản phẩm",
                    ErrorEnum.INDEX,
                    "/admin/sanpham/");
        }
    }

    @Override
    public void activate(Integer id) {
        Optional<SanPhamBienThe> opt = sanPhamBienTheRepository.findById(id);

        if (opt.isPresent()) {
            SanPhamBienThe sanPham = opt.get();
            sanPham.setKichHoat(1);
            save(sanPham);
        }
    }

    @Override
    public void deactivate(Integer id) {
        Optional<SanPhamBienThe> opt = sanPhamBienTheRepository.findById(id);

        if (opt.isPresent()) {
            SanPhamBienThe sanPham = opt.get();
            sanPham.setKichHoat(0);
            save(sanPham);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        sanPhamBienTheRepository.deleteById(id);
    }
}
