package edu.it10.vuquangdung.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
@Table(name = "thongke")
public class ThongKe {
    @Id
    // MM/YYYY
    private String mathongke;

    @Column(name = "sanpham")
    private Long sanpham;

    @Column(name = "donhang")
    private Long donHang;

    @Column(name = "donhangdahuy")
    private Long donHangDaHuy;

    @Column(name = "donhangdagiao")
    private Long donHangDaGiao;

    @Column(name = "khachhang")
    private Long khachHang;

    @Column(name = "luottruycap")
    private Long luotTruyCap;

    @Column(name = "tilechuyendoi")
    private Float tiLeChuyenDoi;

    @Column(name = "doanhthu")
    private BigDecimal doanhThu;

    @Column(name = "von")
    private BigDecimal von;

    @Column(name = "donhangchoxacnhan")
    private Long donHangChoXacNhan;

    @Column(name = "donhangdaxacnhan")
    private Long donHangDaXacNhan;

    @Column(name = "donhangdanggiao")
    private Long donHangDangGiao;

    @Column(name = "luotdangkymoi")
    private Long luotDangKyMoi;

    @Column(name = "chiphi")
    private BigDecimal chiPhi;

    @Column(name = "luotxemsanpham")
    private Long luotXemSanPham;

    @Column(name = "luotthemgiohang")
    private Long luotThemGioHang;

    @Column(name = "luotdathang")
    private Long luotDatHang;

    @Column(name = "luotthanhtoan")
    private Long luotThanhToan;

    @Column(name = "luothoanthanhdon")
    private Long luotHoanThanhDon;

    @Column(name = "NGAYTHEM")
    private String NGAYTHEM;

    public String getMaThongKeFormatted() {
        return "Tháng " + mathongke.split("/")[0] + ", năm " + mathongke.split("/")[1];
    }

    public String getFormattedVND(Long num) {
        return NumberFormat.getInstance().format(num) + " đ";
    }

    public Float tinhTile() {
        Float tile = (float) this.luotHoanThanhDon / (float) this.luotTruyCap;
        return tile * 100;
    }

    public String getTiLeChuyenDoiFormatted() {
        Float tile = tinhTile();
        return Float.toString(tile) + "%";
    }

    public ThongKe() {
    }

    public String getMathongke() {
        return mathongke;
    }

    public void setMathongke(String mathongke) {
        this.mathongke = mathongke;
    }

    public Long getSanpham() {
        return sanpham;
    }

    public void setSanpham(Long sanpham) {
        this.sanpham = sanpham;
    }

    public Long getDonHang() {
        return donHang;
    }

    public void setDonHang(Long donHang) {
        this.donHang = donHang;
    }

    public Long getDonHangDaHuy() {
        return donHangDaHuy;
    }

    public void setDonHangDaHuy(Long donHangDaHuy) {
        this.donHangDaHuy = donHangDaHuy;
    }

    public Long getDonHangDaGiao() {
        return donHangDaGiao;
    }

    public void setDonHangDaGiao(Long donHangDaGiao) {
        this.donHangDaGiao = donHangDaGiao;
    }

    public Long getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(Long khachHang) {
        this.khachHang = khachHang;
    }

    public Long getLuotTruyCap() {
        return luotTruyCap;
    }

    public void setLuotTruyCap(Long luotTruyCap) {
        this.luotTruyCap = luotTruyCap;
    }

    public Float getTiLeChuyenDoi() {
        return tiLeChuyenDoi;
    }

    public void setTiLeChuyenDoi(Float tiLeChuyenDoi) {
        this.tiLeChuyenDoi = tiLeChuyenDoi;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public BigDecimal getVon() {
        return von;
    }

    public void setVon(BigDecimal von) {
        this.von = von;
    }

    public Long getDonHangChoXacNhan() {
        return donHangChoXacNhan;
    }

    public void setDonHangChoXacNhan(Long donHangChoXacNhan) {
        this.donHangChoXacNhan = donHangChoXacNhan;
    }

    public Long getDonHangDaXacNhan() {
        return donHangDaXacNhan;
    }

    public void setDonHangDaXacNhan(Long donHangDaXacNhan) {
        this.donHangDaXacNhan = donHangDaXacNhan;
    }

    public Long getDonHangDangGiao() {
        return donHangDangGiao;
    }

    public void setDonHangDangGiao(Long donHangDangGiao) {
        this.donHangDangGiao = donHangDangGiao;
    }

    public Long getLuotDangKyMoi() {
        return luotDangKyMoi;
    }

    public void setLuotDangKyMoi(Long luotDangKyMoi) {
        this.luotDangKyMoi = luotDangKyMoi;
    }

    public BigDecimal getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(BigDecimal chiPhi) {
        this.chiPhi = chiPhi;
    }

    public Long getLuotXemSanPham() {
        return luotXemSanPham;
    }

    public void setLuotXemSanPham(Long luotXemSanPham) {
        this.luotXemSanPham = luotXemSanPham;
    }

    public Long getLuotThemGioHang() {
        return luotThemGioHang;
    }

    public void setLuotThemGioHang(Long luotThemGioHang) {
        this.luotThemGioHang = luotThemGioHang;
    }

    public Long getLuotDatHang() {
        return luotDatHang;
    }

    public void setLuotDatHang(Long luotDatHang) {
        this.luotDatHang = luotDatHang;
    }

    public Long getLuotThanhToan() {
        return luotThanhToan;
    }

    public void setLuotThanhToan(Long luotThanhToan) {
        this.luotThanhToan = luotThanhToan;
    }

    public Long getLuotHoanThanhDon() {
        return luotHoanThanhDon;
    }

    public void setLuotHoanThanhDon(Long luotHoanThanhDon) {
        this.luotHoanThanhDon = luotHoanThanhDon;
    }

    public String getNGAYTHEM() {
        return NGAYTHEM;
    }

    public void setNGAYTHEM(String nGAYTHEM) {
        NGAYTHEM = nGAYTHEM;
    }
}