package edu.it10.dangquangwatch.spring.repository.customImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.repository.custom.DonghoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DonghoRepositoryCustomImpl implements DonghoRepositoryCustom {
  @Autowired
  EntityManager entityManager;

  @Override
  public Page<Dongho> searchDongho(String searchStr, Dongho searchData, Pageable pageable) {
    StringBuilder queryString = new StringBuilder("SELECT DISTINCT d FROM Dongho d WHERE ");
    queryString.append("(UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

    if (searchData.getGiatien() != null) {
      queryString.append(" AND d.giatien >= " + searchData.getGiatien());
    }

    if (searchData.getSoluong() != null) {
      queryString.append(" AND d.soluong >= " + searchData.getSoluong());
    }

    if (searchData.getTragop() != null) {
      queryString.append(" AND d.tragop >= " + searchData.getTragop());
    }

    if (searchData.getDuongkinh() != null) {
      queryString.append(" AND d.duongkinh = " + searchData.getDuongkinh());
    }

    if (searchData.getBomay() != null && !searchData.getBomay().isEmpty()) {
      queryString.append(" AND d.bomay LIKE '%" + searchData.getBomay() + "%'");
    }

    if (searchData.getChatlieu() != null && !searchData.getChatlieu().isEmpty()) {
      queryString.append(" AND d.chatlieu LIKE '%" + searchData.getChatlieu() + "%'");
    }

    if (searchData.getGioitinh() != null && !searchData.getGioitinh().isEmpty()) {
      queryString.append(" AND d.gioitinh = '" + searchData.getGioitinh() + "'");
    }

    if (searchData.getChongnuoc() != null) {
      queryString.append(" AND d.chongnuoc >= " + searchData.getChongnuoc());
    }

    queryString.append(" ORDER BY d.NGAYTHEM");

    TypedQuery<Dongho> query = entityManager.createQuery(queryString.toString(), Dongho.class);
    query.setParameter("searchStr", searchStr);

    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());

    List<Dongho> result = query.getResultList();

    StringBuilder queryStringCount = new StringBuilder("SELECT COUNT(DISTINCT d) FROM Dongho d WHERE ");
    queryStringCount.append("(UPPER(d.tendongho) LIKE UPPER(CONCAT('%', :searchStr, '%')) OR UPPER(d.thongtin) LIKE UPPER(CONCAT('%', :searchStr, '%')))");

    if (searchData.getGiatien() != null) {
      queryStringCount.append(" AND d.giatien >= " + searchData.getGiatien());
    }

    if (searchData.getSoluong() != null) {
      queryStringCount.append(" AND d.soluong >= " + searchData.getSoluong());
    }

    if (searchData.getTragop() != null) {
      queryStringCount.append(" AND d.tragop >= " + searchData.getTragop());
    }

    if (searchData.getDuongkinh() != null) {
      queryStringCount.append(" AND d.duongkinh = " + searchData.getDuongkinh());
    }

    if (searchData.getBomay() != null && !searchData.getBomay().isEmpty()) {
      queryStringCount.append(" AND d.bomay LIKE '%" + searchData.getBomay() + "%'");
    }

    if (searchData.getChatlieu() != null && !searchData.getChatlieu().isEmpty()) {
      queryStringCount.append(" AND d.chatlieu LIKE '%" + searchData.getChatlieu() + "%'");
    }

    if (searchData.getGioitinh() != null && !searchData.getGioitinh().isEmpty()) {
      queryStringCount.append(" AND d.gioitinh = '" + searchData.getGioitinh() + "'");
    }

    if (searchData.getChongnuoc() != null) {
      queryStringCount.append(" AND d.chongnuoc >= " + searchData.getChongnuoc());
    }

    Query countQuery = entityManager.createQuery(queryStringCount.toString(), Long.class);
    countQuery.setParameter("searchStr", searchStr);

    Long count = (Long) countQuery.getSingleResult();

    return new PageImpl<Dongho>(result, pageable, count);
  }
}
