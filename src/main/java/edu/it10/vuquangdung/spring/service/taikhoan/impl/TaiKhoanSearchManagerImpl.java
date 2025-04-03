package edu.it10.vuquangdung.spring.service.taikhoan.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.TaiKhoan;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanSearchManager;

import java.util.List;

@Service
public class TaiKhoanSearchManagerImpl implements TaiKhoanSearchManager {
    private final EntityManager entityManager;

    public TaiKhoanSearchManagerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoan(String from, String to, Integer page) {
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
                "SELECT tk FROM TaiKhoan tk WHERE tk.NGAYTHEM >= :from AND tk.NGAYTHEM <= :to",
                TaiKhoan.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<TaiKhoan> result = query.getResultList();
        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoanQuanTri(String from, String to, Integer page) {
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
                "SELECT DISTINCT tk FROM TaiKhoan tk WHERE tk.loai_tai_khoan <> 'ROLE_KHACHHANG' AND tk.NGAYTHEM >= :from AND tk.NGAYTHEM <= :to",
                TaiKhoan.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<TaiKhoan> result = query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoanKhachHang(String from, String to, Integer page) {
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
                "SELECT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and tk.NGAYTHEM >= :from AND tk.NGAYTHEM <= :to",
                TaiKhoan.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<TaiKhoan> result = query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }

    @Override
    public Page<TaiKhoan> searchTaiKhoanQuanTri(String username, String from, String to, Integer page) {
        username = "%" + username + "%";
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
                "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan <> 'ROLE_KHACHHANG' and tk.username LIKE :username and tk.NGAYTHEM >= :from AND tk.NGAYTHEM <= :to",
                TaiKhoan.class);
        query.setParameter("username", username);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<TaiKhoan> result = query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }


    @Override
    public Page<TaiKhoan> searchTaiKhoanKhachHang(String searchStr, String from, String to, Integer page) {
        searchStr = "%" + searchStr + "%";
        TypedQuery<TaiKhoan> query = entityManager.createQuery(
                "SELECT DISTINCT tk FROM TaiKhoan tk where tk.loai_tai_khoan = 'ROLE_KHACHHANG' and (tk.hoten LIKE :searchStr OR tk.diachi LIKE :searchStr OR tk.username LIKE :searchStr OR tk.sodienthoai LIKE :searchStr) and tk.NGAYTHEM >= :from AND tk.NGAYTHEM <= :to",
                TaiKhoan.class);
        query.setParameter("searchStr", searchStr);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<TaiKhoan> result = query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }

    @Override
    public Page<TaiKhoan> findTaiKhoanByUsername(String username, Integer page) {
        username = "%" + username + "%";
        TypedQuery<TaiKhoan> query = entityManager.createQuery("FROM TaiKhoan WHERE username LIKE :data", TaiKhoan.class);
        query.setParameter("data", username);
        List<TaiKhoan> result = query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, 10), result.size());
    }
}
