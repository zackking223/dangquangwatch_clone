package edu.it10.vuquangdung.spring.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.it10.vuquangdung.spring.entity.ThongKe;
import edu.it10.vuquangdung.spring.helper.DateStringHelper;
import edu.it10.vuquangdung.spring.repository.ThongKeRepository;
import edu.it10.vuquangdung.spring.service.ThongKeService;
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
            thongKe.setSanPham(prev.getSanPham());

            // thongKe.setDonHangDaGiao(prev.getDonHangDaGiao());
            // thongKe.setDonHangDaHuy(prev.getDonHangDaHuy());
            thongKe.setDonHangChoXacNhan(prev.getDonHangChoXacNhan());
            thongKe.setDonHangDaXacNhan(prev.getDonHangDaXacNhan());
            thongKe.setDonHangDangGiao(prev.getDonHangDangGiao());
        } else {
            BigDecimal chiPhi = BigDecimal.ZERO;

            // Query for DongHo
            String querySanPham = """
                    SELECT SUM(d.soLuong * d.giaTien) AS tongGiaTien, COUNT(d.soLuong) AS tongSoLuong
                    FROM SanPhamBienThe d
                    """;
            Object[] sanPhamResult = (Object[]) entityManager.createQuery(querySanPham).getSingleResult();
            chiPhi = chiPhi.add(sanPhamResult[0] != null ? BigDecimal.valueOf((Long)sanPhamResult[0]) : BigDecimal.ZERO);
            thongKe.setSanPham(sanPhamResult[1] != null ? ((Number) sanPhamResult[1]).longValue() : 0L);

            thongKe.setChiPhi(chiPhi);

            // Query for KhachHang
            String queryKhachHang = """
                    SELECT COUNT(tk.username) AS tongSoLuong
                    FROM TaiKhoan tk
                    WHERE tk.loai_tai_khoan = 'ROLE_KHACHHANG'
                    """;
            Long khachHangCount = (Long) entityManager.createQuery(queryKhachHang).getSingleResult();
            thongKe.setKhachHang((khachHangCount != null ? khachHangCount : 0L));

            // Query for DonHang
            String queryDonHang = """
                    SELECT COUNT(d.*) AS soluong
                    FROM DonHang d
                    """;
            Long donHangCount = (Long) entityManager.createQuery(queryDonHang).getSingleResult();
            thongKe.setDonHang(donHangCount != null ? donHangCount : 0L);

            // Query for ChoXacNhan
            String queryChoXacNhan = """
                    SELECT COUNT(d.tinhTrang) AS soluong
                    FROM DonHang d
                    WHERE d.tinhTrang = 'Chờ xác nhận'
                    """;
            Long choXacNhanCount = (Long) entityManager.createQuery(queryChoXacNhan).getSingleResult();
            thongKe.setDonHangChoXacNhan(choXacNhanCount != null ? choXacNhanCount : 0L);

            // Query for DaXacNhan
            String queryDaXacNhan = """
                    SELECT COUNT(d.tinhTrang) AS soluong
                    FROM DonHang d
                    WHERE d.tinhTrang = 'Đã xác nhận'
                    """;
            Long daXacNhanCount = (Long) entityManager.createQuery(queryDaXacNhan).getSingleResult();
            thongKe.setDonHangDaXacNhan(daXacNhanCount != null ? daXacNhanCount : 0L);

            // Query for DangGiao
            String queryDangGiao = """
                    SELECT COUNT(d.tinhTrang) AS soluong
                    FROM DonHang d
                    WHERE d.tinhTrang = 'Đang vận chuyển'
                    """;
            Long dangGiaoCount = (Long) entityManager.createQuery(queryDangGiao).getSingleResult();
            thongKe.setDonHangDangGiao(dangGiaoCount != null ? dangGiaoCount : 0L);
        }

        thongKe.setDonHangDaGiao(0L);
        thongKe.setDonHangDaHuy(0L);

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
    public void incLuotTruyCap() {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setLuotTruyCap(thongKe.getLuotTruyCap() + 1);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void incLuotXemSanPham() {
        ThongKe thongKe = getCurrent();

        // Tăng giá trị
        thongKe.setLuotXemSanPham(thongKe.getLuotXemSanPham() + 1);

        thongKeRepository.save(thongKe);
    }

    @Override
    @Transactional
    public void incLuotThemGioHang() {
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

    @Override
    public void incDoanhThu(BigDecimal amount) {
        ThongKe thongKe = getCurrent();

        thongKe.setDoanhThu(thongKe.getDoanhThu().add(amount));

        thongKeRepository.save(thongKe);
    }

    @Override
    public ByteArrayInputStream exportThongKeToExcel(List<ThongKe> thongKeList) throws IOException {
        String[] columns = {
                "Mã thống kê", "Sản phẩm", "Đơn hàng", "Đơn hàng đã hủy", "Đơn hàng đã giao", "Khách hàng",
                "Lượt truy cập", "Tỉ lệ chuyển đổi", "Doanh thu", "Vốn",
                "Đơn hàng chờ xác nhận", "Đơn hàng đã xác nhận", "Đơn hàng đang giao",
                "Lượt đăng ký mới", "Chi phí", "Lượt xem sản phẩm",
                "Lượt thêm giỏ hàng", "Lượt đặt hàng", "Lượt thanh toán",
                "Lượt hoàn thành đơn", "Ngày tạo", "Ngày xuất"
        };

        // Tạo workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ThongKe");

        // Ghi tiêu đề vào cột đầu tiên (hàng dọc)
        for (int i = 0; i < columns.length; i++) {
            Row row = sheet.createRow(i); // Tạo từng hàng cho tiêu đề
            Cell cell = row.createCell(0); // Ghi tiêu đề vào cột 0
            cell.setCellValue(columns[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Ghi dữ liệu của từng ThongKe theo từng cột
        int colIdx = 1; // Bắt đầu từ cột thứ 1 (sau tiêu đề)
        for (ThongKe thongKe : thongKeList) {
            int rowIdx = 0; // Bắt đầu từ hàng đầu tiên
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getMathongke());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getSanPham());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHang());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHangDaHuy());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHangDaGiao());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getKhachHang());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotTruyCap());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getTiLeChuyenDoiFormatted());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDoanhThu().doubleValue());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getVon().doubleValue());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHangChoXacNhan());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHangDaXacNhan());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getDonHangDangGiao());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotDangKyMoi());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getChiPhi().doubleValue());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotXemSanPham());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotThemGioHang());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotDatHang());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotThanhToan());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getLuotHoanThanhDon());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(thongKe.getNGAYTHEM());
            sheet.getRow(rowIdx++).createCell(colIdx).setCellValue(DateStringHelper.getCurrentDateFormatted());
            colIdx++; // Chuyển sang cột tiếp theo
        }

        // Tự động điều chỉnh kích thước các hàng
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(0); // Chỉ auto-size cho cột tiêu đề
        }

        // Ghi dữ liệu ra output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }
}
