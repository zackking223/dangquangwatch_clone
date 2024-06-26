package edu.it10.dangquangwatch.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.repository.custom.DonHangRepositoryCustom;

public interface DonHangRepository extends JpaRepository<DonHang, Integer>, DonHangRepositoryCustom {}
