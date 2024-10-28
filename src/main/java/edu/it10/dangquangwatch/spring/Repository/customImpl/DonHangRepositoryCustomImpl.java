package edu.it10.dangquangwatch.spring.repository.customImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.repository.custom.DonHangRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class DonHangRepositoryCustomImpl implements DonHangRepositoryCustom {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Page<DonHang> searchDonHang(
      String username,
      String hoten,
      String diachi,
      String tensanpham,
      String tinhtrang,
      String thanhtoan,
      Integer tongtien,
      String from,
      String to,
      Pageable pageable) {
    StringBuilder jpql = new StringBuilder("SELECT DISTINCT dh FROM DonHang dh " +
        "JOIN dh.taikhoan tk " +
        "JOIN dh.items ctdh " +
        "WHERE 1=1");

    List<String> conditions = new ArrayList<>();
    if (username != null && !username.isEmpty()) {
      conditions.add("UPPER(tk.username) LIKE UPPER(:username)");
    }
    if (hoten != null && !hoten.isEmpty()) {
      conditions.add("UPPER(tk.hoten) LIKE UPPER(:hoten)");
    }
    if (diachi != null && !diachi.isEmpty()) {
      conditions.add("UPPER(dh.diaChi) LIKE UPPER(:diachi)");
    }
    if (tinhtrang != null && !tinhtrang.isEmpty()) {
      conditions.add("UPPER(dh.tinhTrang) LIKE UPPER(:tinhtrang)");
    }
    if (thanhtoan != null && !thanhtoan.isEmpty()) {
      conditions.add("UPPER(dh.thanhToan) LIKE UPPER(:thanhtoan)");
    }
    if (tongtien > 0) {
      conditions.add("dh.tongTien >= :tongtien");
    }
    if (tensanpham != null && !tensanpham.isEmpty()) {
      conditions.add(
          "EXISTS (SELECT 1 FROM ChiTietDonHang ctdh WHERE ctdh.donhang = dh AND UPPER(ctdh.tensanpham) LIKE UPPER(:tensanpham))");
    }

    if (from != null) {
      conditions.add("dh.NGAYTHEM >= \'" + from + "\'");
    }

    if (to != null) {
      conditions.add("dh.NGAYTHEM <= \'" + to + "\'");
    }

    if (!conditions.isEmpty()) {
      jpql.append(" AND (");
      jpql.append(String.join(" AND ", conditions));
      jpql.append(")");
    }

    TypedQuery<DonHang> query = entityManager.createQuery(jpql.toString(), DonHang.class);

    if (username != null && !username.isEmpty()) {
      query.setParameter("username", "%" + username + "%");
    }
    if (hoten != null && !hoten.isEmpty()) {
      query.setParameter("hoten", "%" + hoten + "%");
    }
    if (diachi != null && !diachi.isEmpty()) {
      query.setParameter("diachi", "%" + diachi + "%");
    }
    if (tinhtrang != null && !tinhtrang.isEmpty()) {
      query.setParameter("tinhtrang", "%" + tinhtrang + "%");
    }
    if (thanhtoan != null && !thanhtoan.isEmpty()) {
      query.setParameter("thanhtoan", "%" + thanhtoan + "%");
    }
    if (tensanpham != null && !tensanpham.isEmpty()) {
      query.setParameter("tensanpham", "%" + tensanpham + "%");
    }
    if (tongtien > 0) {
      query.setParameter("tongtien", tongtien);
    }

    // Get the total number of results
    int totalRows = query.getResultList().size();

    // Apply pagination
    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());

    List<DonHang> donHangList = query.getResultList();
    return new PageImpl<>(donHangList, pageable, totalRows);
  }
}
