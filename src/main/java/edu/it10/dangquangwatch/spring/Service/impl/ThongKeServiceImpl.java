package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.repository.ThongKeRepository;
import edu.it10.dangquangwatch.spring.service.ThongKeService;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private ThongKeRepository thongKeRepository;

    @Override
    public List<ThongKe> getAllThongKe() {
        return (List<ThongKe>) thongKeRepository.findAll();
    }

    @Override
    public Optional<ThongKe> findThongKeById(Integer mathongKe) {
        return thongKeRepository.findById(mathongKe);
    }

    @Override
    public void tangluottruycap() {
        // Lấy bản ghi ThongKe (ví dụ: bản ghi có id = 1)
        ThongKe thongKe = thongKeRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi thống kê"));

        // Tăng giá trị
        thongKe.setLuotTruyCap(thongKe.getLuotTruyCap() + 1);

        // Lưu lại vào cơ sở dữ liệu
        thongKeRepository.save(thongKe);
    }

    @Override
    public void tangluotxemsanpham() {
        // Lấy bản ghi ThongKe (ví dụ: bản ghi có id = 1)
        ThongKe thongKe = thongKeRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi thống kê"));

        // Tăng giá trị
        thongKe.setLuotXemSanPham(thongKe.getLuotXemSanPham() + 1);

        // Lưu lại vào cơ sở dữ liệu
        thongKeRepository.save(thongKe);
    }
}
