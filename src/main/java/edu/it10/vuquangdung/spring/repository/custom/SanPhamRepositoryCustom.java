package edu.it10.vuquangdung.spring.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Service
public class SanPhamRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    Page<SanPham> search(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTien,
            String from,
            String to,
            Pageable pageable) {
        StringBuilder queryString = new StringBuilder("SELECT DISTINCT s FROM SanPham s WHERE ");
        queryString.append(
                "(UPPER(s.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

        if (!kichThuoc.isEmpty()) {
            queryString.append(" AND s.kichThuoc = '" + kichThuoc + "'");
        }
        if (!mauSac.isEmpty()) {
            queryString.append(" AND s.mauSac = '" + mauSac + "'");
        }
        if (!loai.isEmpty()) {
            queryString.append(" AND s.loai = '" + loai + "'");
        }
        if (giaTien > 0) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb WHERE sb.sanPham = s AND sb.giaTien >= "
                    + giaTien.toString() + " ) ");
        }
        if (from != null) {
            queryString.append(" AND s.ngayThem >= '" + from + "'");
        }
        if (to != null) {
            queryString.append(" AND s.ngayThem <= '" + to + "'");
        }

        queryString.append(" ORDER BY s.ngayThem");

        TypedQuery<SanPham> query = entityManager.createQuery(queryString.toString(), SanPham.class);
        query.setParameter("searchStr", searchStr);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<SanPham> result = query.getResultList();
        Long count = countResult(searchStr, kichThuoc, mauSac, loai, giaTien, from, to);

        return new PageImpl<SanPham>(result, pageable, count);
    }

    private Long countResult(
            String searchStr,
            String kichThuoc,
            String mauSac,
            String loai,
            Integer giaTien,
            String from,
            String to) {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(DISTINCT s) FROM SanPham s WHERE ");
        queryString.append(
                "(UPPER(s.ten) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thuongHieu) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(s.thongTin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

        if (!kichThuoc.isEmpty()) {
            queryString.append(" AND s.kichThuoc = '" + kichThuoc + "'");
        }
        if (!mauSac.isEmpty()) {
            queryString.append(" AND s.mauSac = '" + mauSac + "'");
        }
        if (!loai.isEmpty()) {
            queryString.append(" AND s.loai = '" + loai + "'");
        }
        if (giaTien > 0) {
            queryString.append(" AND EXISTS (SELECT 1 FROM SanPhamBienThe sb WHERE sb.sanPham = s AND sb.giaTien >= "
                    + giaTien.toString() + " ) ");
        }
        if (from != null) {
            queryString.append(" AND s.ngayThem >= '" + from + "'");
        }
        if (to != null) {
            queryString.append(" AND s.ngayThem <= '" + to + "'");
        }

        queryString.append(" ORDER BY s.ngayThem");

        Query query = entityManager.createQuery(queryString.toString(), SanPham.class);
        query.setParameter("searchStr", searchStr);
        return (Long) query.getSingleResult();
    }
}
