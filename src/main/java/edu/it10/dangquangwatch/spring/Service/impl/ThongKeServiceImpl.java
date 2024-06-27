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
    private ThongKeRepository thongkeRepository;

    @Override
    public List<ThongKe> getAllThongKe() {
        return (List<ThongKe>) thongkeRepository.findAll();
    }

    @Override
    public Optional<ThongKe> findThongKeById(Integer mathongKe) {
        return thongkeRepository.findById(mathongKe);
    }
}
