package edu.it10.vuquangdung.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.SanPham;
import java.util.List;

@Repository
public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, Integer> {
    List<AnhSanPham> findBySanPham(SanPham sanPham);
}
