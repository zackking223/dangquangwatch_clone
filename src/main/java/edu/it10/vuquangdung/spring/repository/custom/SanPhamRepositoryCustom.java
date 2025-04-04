package edu.it10.vuquangdung.spring.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.it10.vuquangdung.spring.entity.SanPham;

public interface SanPhamRepositoryCustom {
    Page<SanPham> search(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTienFrom,
            Integer giaTienTo,
            String from,
            String to,
            Pageable pageable);
}
