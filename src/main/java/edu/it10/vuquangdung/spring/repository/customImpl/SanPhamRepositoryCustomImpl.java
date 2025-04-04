package edu.it10.vuquangdung.spring.repository.customImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.repository.custom.SanPhamRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class SanPhamRepositoryCustomImpl implements SanPhamRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<SanPham> search(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTienFrom,
            Integer giaTienTo,
            String from,
            String to,
            Pageable pageable) {
        StringBuilder queryString = new StringBuilder("SELECT DISTINCT s FROM SanPham s WHERE ");
        queryString.append(
                "(UPPER(s.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

        if (!kichThuoc.isEmpty()) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb1 WHERE sb1.sanPham = s AND sb1.kichThuoc.kichThuocId = '" + kichThuoc + "' ) ");
        }
        if (!mauSac.isEmpty()) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb2 WHERE sb2.sanPham = s AND sb2.mauSac.mauSacId = '" + mauSac + "' ) ");
        }
        if (!loai.isEmpty()) {
            queryString.append(" AND s.loai = '" + loai + "'");
        }
        if (giaTienFrom > 0) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb3 WHERE sb3.sanPham = s AND sb3.giaTien >= " + giaTienFrom.toString() + " ) ");
        }

        if (giaTienTo > 0 && giaTienTo > giaTienFrom) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb3 WHERE sb3.sanPham = s AND sb3.giaTien <= " + giaTienTo.toString() + " ) ");
        }

        if (from != null && !from.isEmpty()) {
            queryString.append(" AND s.ngayThem >= '" + from + "'");
        }
        if (to != null && !to.isEmpty()) {
            queryString.append(" AND s.ngayThem <= '" + to + "'");
        }

        queryString.append(" ORDER BY s.ngayThem");

        TypedQuery<SanPham> query = entityManager.createQuery(queryString.toString(), SanPham.class);
        query.setParameter("searchStr", searchStr);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<SanPham> result = query.getResultList();
        Long count = countResult(searchStr, kichThuoc, mauSac, loai, giaTienFrom, giaTienTo, from, to);

        return new PageImpl<SanPham>(result, pageable, count);
    }

    private Long countResult(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTienFrom,
            Integer giaTienTo,
            String from,
            String to) {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(DISTINCT s) FROM SanPham s WHERE ");
        queryString.append(
                "(UPPER(s.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

        if (!kichThuoc.isEmpty()) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb1 WHERE sb1.sanPham = s AND sb1.kichThuoc.kichThuocId = '" + kichThuoc + "' ) ");
        }
        if (!mauSac.isEmpty()) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb2 WHERE sb2.sanPham = s AND sb2.mauSac.mauSacId = '" + mauSac + "' ) ");
        }
        if (!loai.isEmpty()) {
            queryString.append(" AND s.loai = '" + loai + "'");
        }
        if (giaTienFrom > 0) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb3 WHERE sb3.sanPham = s AND sb3.giaTien >= " + giaTienFrom.toString() + " ) ");
        }
        if (giaTienTo > 0 && giaTienTo > giaTienFrom) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb3 WHERE sb3.sanPham = s AND sb3.giaTien <= " + giaTienTo.toString() + " ) ");
        }
        if (from != null) {
            queryString.append(" AND s.ngayThem >= '" + from + "'");
        }
        if (to != null) {
            queryString.append(" AND s.ngayThem <= '" + to + "'");
        }

        queryString.append(" ORDER BY s.ngayThem");

        Query query = entityManager.createQuery(queryString.toString());
        query.setParameter("searchStr", searchStr);
        return (Long) query.getSingleResult();
    }
}
