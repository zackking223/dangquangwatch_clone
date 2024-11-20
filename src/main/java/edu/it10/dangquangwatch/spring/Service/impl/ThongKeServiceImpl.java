package edu.it10.dangquangwatch.spring.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.helper.DateStringHelper;
import edu.it10.dangquangwatch.spring.repository.ThongKeRepository;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private ThongKeRepository thongKeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    ThongKe initThongKe() {
        ThongKe thongKe = new ThongKe();
        thongKe.setMathongke(getCurrentId());

        Optional<ThongKe> prevOpt = thongKeRepository.findById(getPreviousId());

        // donhang, khachhang
        // chiphi
        // dongho, kinhmat, phukien, trangsuc, butky
        if (prevOpt.isPresent()) {
            ThongKe prev = prevOpt.get();

            thongKe.setDonHang(prev.getDonHang());
            thongKe.setKhachHang(prev.getKhachHang());
            thongKe.setChiPhi(prev.getChiPhi());

            thongKe.setDongHo(prev.getDongHo());
            thongKe.setKinhMat(prev.getKinhMat());
            thongKe.setPhuKien(prev.getPhuKien());
            thongKe.setTrangSuc(prev.getTrangSuc());
            thongKe.setButKy(prev.getButKy());
        } else {
            BigDecimal chiPhi = BigDecimal.ZERO;

            // Query for DongHo
            String queryDongHo = """
                    SELECT SUM(d.soluong * d.giatien) AS tongGiaTien, COUNT(d.soluong) AS tongSoLuong
                    FROM dongho d
                    """;
            Object[] dongHoResult = (Object[]) entityManager.createQuery(queryDongHo).getSingleResult();
            chiPhi = chiPhi.add(dongHoResult[0] != null ? (BigDecimal) dongHoResult[0] : BigDecimal.ZERO);
            thongKe.setDongHo(dongHoResult[1] != null ? ((Number) dongHoResult[1]).longValue() : 0L);

            // Query for TrangSuc
            String queryTrangSuc = """
                    SELECT SUM(t.soluong * t.giatien) AS tongGiaTien, COUNT(t.soluong) AS tongSoLuong
                    FROM trangsuc t
                    """;
            Object[] trangSucResult = (Object[]) entityManager.createQuery(queryTrangSuc).getSingleResult();
            chiPhi = chiPhi
                    .add(trangSucResult[0] != null ? (BigDecimal) trangSucResult[0] : BigDecimal.ZERO);
            thongKe.setTrangSuc(trangSucResult[1] != null ? ((Number) trangSucResult[1]).longValue() : 0L);

            // Query for ButKy
            String queryButKy = """
                    SELECT SUM(b.soluong * b.giatien) AS tongGiaTien, COUNT(b.soluong) AS tongSoLuong
                    FROM butky b
                    """;
            Object[] butKyResult = (Object[]) entityManager.createQuery(queryButKy).getSingleResult();
            chiPhi = chiPhi.add(butKyResult[0] != null ? (BigDecimal) butKyResult[0] : BigDecimal.ZERO);
            thongKe.setButKy(butKyResult[1] != null ? ((Number) butKyResult[1]).longValue() : 0L);

            // Query for PhuKien
            String queryPhuKien = """
                    SELECT SUM(p.soluong * p.giatien) AS tongGiaTien, COUNT(p.soluong) AS tongSoLuong
                    FROM phukien p
                    """;
            Object[] phuKienResult = (Object[]) entityManager.createQuery(queryPhuKien).getSingleResult();
            chiPhi = chiPhi.add(phuKienResult[0] != null ? (BigDecimal) phuKienResult[0] : BigDecimal.ZERO);
            thongKe.setPhuKien(phuKienResult[1] != null ? ((Number) phuKienResult[1]).longValue() : 0L);

            // Query for KinhMat
            String queryKinhMat = """
                    SELECT SUM(k.soluong * k.giatien) AS tongGiaTien, COUNT(k.soluong) AS tongSoLuong
                    FROM kinhmat k
                    """;
            Object[] kinhMatResult = (Object[]) entityManager.createQuery(queryKinhMat).getSingleResult();
            chiPhi = chiPhi.add(kinhMatResult[0] != null ? (BigDecimal) kinhMatResult[0] : BigDecimal.ZERO);
            thongKe.setKinhMat(kinhMatResult[1] != null ? ((Number) kinhMatResult[1]).longValue() : 0L);

            thongKe.setChiPhi(chiPhi);

            // Query for KhachHang
            String queryKhachHang = """
                    SELECT COUNT(tk.username) AS tongSoLuong
                    FROM taikhoan tk
                    WHERE tk.loai_tai_khoan = 'ROLE_KHACHHANG'
                    """;
            Long khachHangCount = (Long) entityManager.createQuery(queryKhachHang).getSingleResult();
            thongKe.setKhachHang((khachHangCount != null ? khachHangCount : 0L));
        }

        thongKe.setChiPhi(BigDecimal.valueOf(0));

        thongKe.setDoanhThu(BigDecimal.valueOf(0));
        thongKe.setVon(BigDecimal.valueOf(0));

        thongKe.setTiLeChuyenDoi(Float.valueOf(0));
        thongKe.setLuotTruyCap(0L);
        thongKe.setLuotDangKyMoi(0L);
        thongKe.setLuotXemSanPham(0L);
        thongKe.setLuotThemGioHang(0L);

        thongKe.setLuotDatHang(0L);
        thongKe.setLuotHoanThanhDon(0L);
        thongKe.setLuotThanhToan(0L);

        thongKe.setDonHangDaGiao(0L);
        thongKe.setDonHangDaHuy(0L);
        thongKe.setDonHangDaXacNhan(0L);
        thongKe.setDonHangDangGiao(0L);

        thongKe.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());

        return thongKe;
    }

    String getPreviousId() {
        return DateStringHelper.getPreviousMonthYear();
    }

    String getCurrentId() {
        return DateStringHelper.getCurrentMonthYear();
    }

    @Override
    @Transactional
    public ThongKe getCurrent() {
        Optional<ThongKe> opt = thongKeRepository.findById(getCurrentId());

        if (opt.isPresent()) {
            return opt.get();
        } else {
            return initThongKe();
        }
    }

    @Override
    public List<ThongKe> getAllThongKe() {
        return (List<ThongKe>) thongKeRepository.findAll();
    }

    @Override
    public Optional<ThongKe> findThongKeById(String mathongKe) {
        return thongKeRepository.findById(mathongKe);
    }

    @Override
    @Transactional
    public void tangluottruycap() {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setLuotTruyCap(thongKe.getLuotTruyCap() + 1);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void tangluotxemsanpham() {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setLuotXemSanPham(thongKe.getLuotXemSanPham() + 1);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void tangluotthemgiohang() {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setLuotThemGioHang(thongKe.getLuotThemGioHang() + 1);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void updateVon(BigDecimal newCapital) {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setVon(newCapital);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void incVon(BigDecimal amount) {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setVon(thongKe.getVon().add(amount));

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void decVon(BigDecimal amount) {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        BigDecimal oldVon = thongKe.getVon();

        if (oldVon.compareTo(amount) <= 0 || oldVon.compareTo(BigDecimal.valueOf(1)) < 0) {
            thongKe.setVon(BigDecimal.ZERO);
        } else {
            thongKe.setVon(thongKe.getVon().subtract(amount));
        }
        thongKeRepository.save(thongKe);
    }

    @Override
    public void updateTiLeChuyenDoi(Float newTiLe) {
        ThongKe thongKe = getCurrent();

        thongKe.setTiLeChuyenDoi(newTiLe);

        thongKeRepository.save(thongKe);
    }

    @Override
    public void incChiPhi(BigDecimal amount) {
        ThongKe thongKe = getCurrent();

        thongKe.setChiPhi(thongKe.getChiPhi().add(amount));

        thongKeRepository.save(thongKe);
    }
}
