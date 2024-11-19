package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

@Entity
@Table(name = "thongke")
public class ThongKe {
    @Id
    // MM/YYYY
    private String mathongke;

    @Column(name = "dongho")
    private Long dongHo;

    @Column(name = "butky")
    private Long butKy;

    @Column(name = "phukien")
    private Long phuKien;

    @Column(name = "trangsuc")
    private Long trangSuc;

    @Column(name = "kinhmat")
    private Long kinhMat;

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
        return this.mathongke;
    }

    public void setMathongke(String mathongke) {
        this.mathongke = mathongke;
    }

    public Long getDongHo() {
        return this.dongHo;
    }

    public void setDongHo(Long dongHo) {
        this.dongHo = dongHo;
    }

    public Long getButKy() {
        return this.butKy;
    }

    public void setButKy(Long butKy) {
        this.butKy = butKy;
    }

    public Long getPhuKien() {
        return this.phuKien;
    }

    public void setPhuKien(Long phuKien) {
        this.phuKien = phuKien;
    }

    public Long getTrangSuc() {
        return this.trangSuc;
    }

    public void setTrangSuc(Long trangSuc) {
        this.trangSuc = trangSuc;
    }

    public Long getKinhMat() {
        return this.kinhMat;
    }

    public void setKinhMat(Long kinhMat) {
        this.kinhMat = kinhMat;
    }

    public Long getDonHang() {
        return this.donHang;
    }

    public void setDonHang(Long donHang) {
        this.donHang = donHang;
    }

    public Long getDonHangDaHuy() {
        return this.donHangDaHuy;
    }

    public void setDonHangDaHuy(Long donHangDaHuy) {
        this.donHangDaHuy = donHangDaHuy;
    }

    public Long getDonHangDaGiao() {
        return this.donHangDaGiao;
    }

    public void setDonHangDaGiao(Long donHangDaGiao) {
        this.donHangDaGiao = donHangDaGiao;
    }

    public Long getKhachHang() {
        return this.khachHang;
    }

    public void setKhachHang(Long khachHang) {
        this.khachHang = khachHang;
    }

    public Long getLuotTruyCap() {
        return this.luotTruyCap;
    }

    public void setLuotTruyCap(Long luotTruyCap) {
        this.luotTruyCap = luotTruyCap;
    }

    public Float getTiLeChuyenDoi() {
        return this.tiLeChuyenDoi;
    }

    public void setTiLeChuyenDoi(Float tiLeChuyenDoi) {
        this.tiLeChuyenDoi = tiLeChuyenDoi;
    }

    public BigDecimal getDoanhThu() {
        return this.doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public BigDecimal getVon() {
        return this.von;
    }

    public void setVon(BigDecimal von) {
        this.von = von;
    }

    public Long getDonHangChoXacNhan() {
        return this.donHangChoXacNhan;
    }

    public void setDonHangChoXacNhan(Long donHangChoXacNhan) {
        this.donHangChoXacNhan = donHangChoXacNhan;
    }

    public Long getDonHangDaXacNhan() {
        return this.donHangDaXacNhan;
    }

    public void setDonHangDaXacNhan(Long donHangDaXacNhan) {
        this.donHangDaXacNhan = donHangDaXacNhan;
    }

    public Long getDonHangDangGiao() {
        return this.donHangDangGiao;
    }

    public void setDonHangDangGiao(Long donHangDangGiao) {
        this.donHangDangGiao = donHangDangGiao;
    }

    public Long getLuotDangKyMoi() {
        return this.luotDangKyMoi;
    }

    public void setLuotDangKyMoi(Long luotDangKyMoi) {
        this.luotDangKyMoi = luotDangKyMoi;
    }

    public BigDecimal getChiPhi() {
        return this.chiPhi;
    }

    public void setChiPhi(BigDecimal chiPhi) {
        this.chiPhi = chiPhi;
    }

    public Long getLuotXemSanPham() {
        return this.luotXemSanPham;
    }

    public void setLuotXemSanPham(Long luotXemSanPham) {
        this.luotXemSanPham = luotXemSanPham;
    }

    public Long getLuotThemGioHang() {
        return this.luotThemGioHang;
    }

    public void setLuotThemGioHang(Long luotThemGioHang) {
        this.luotThemGioHang = luotThemGioHang;
    }

    public Long getLuotDatHang() {
        return this.luotDatHang;
    }

    public void setLuotDatHang(Long luotDatHang) {
        this.luotDatHang = luotDatHang;
    }

    public Long getLuotThanhToan() {
        return this.luotThanhToan;
    }

    public void setLuotThanhToan(Long luotThanhToan) {
        this.luotThanhToan = luotThanhToan;
    }

    public Long getLuotHoanThanhDon() {
        return this.luotHoanThanhDon;
    }

    public void setLuotHoanThanhDon(Long luotHoanThanhDon) {
        this.luotHoanThanhDon = luotHoanThanhDon;
    }

    public String getNGAYTHEM() {
        return this.NGAYTHEM;
    }

    public void setNGAYTHEM(String NGAYTHEM) {
        this.NGAYTHEM = NGAYTHEM;
    }

    public ThongKe mathongke(String mathongke) {
        setMathongke(mathongke);
        return this;
    }

    public ThongKe dongHo(Long dongHo) {
        setDongHo(dongHo);
        return this;
    }

    public ThongKe butKy(Long butKy) {
        setButKy(butKy);
        return this;
    }

    public ThongKe phuKien(Long phuKien) {
        setPhuKien(phuKien);
        return this;
    }

    public ThongKe trangSuc(Long trangSuc) {
        setTrangSuc(trangSuc);
        return this;
    }

    public ThongKe kinhMat(Long kinhMat) {
        setKinhMat(kinhMat);
        return this;
    }

    public ThongKe donHang(Long donHang) {
        setDonHang(donHang);
        return this;
    }

    public ThongKe donHangDaHuy(Long donHangDaHuy) {
        setDonHangDaHuy(donHangDaHuy);
        return this;
    }

    public ThongKe donHangDaGiao(Long donHangDaGiao) {
        setDonHangDaGiao(donHangDaGiao);
        return this;
    }

    public ThongKe khachHang(Long khachHang) {
        setKhachHang(khachHang);
        return this;
    }

    public ThongKe luotTruyCap(Long luotTruyCap) {
        setLuotTruyCap(luotTruyCap);
        return this;
    }

    public ThongKe tiLeChuyenDoi(Float tiLeChuyenDoi) {
        setTiLeChuyenDoi(tiLeChuyenDoi);
        return this;
    }

    public ThongKe doanhThu(BigDecimal doanhThu) {
        setDoanhThu(doanhThu);
        return this;
    }

    public ThongKe von(BigDecimal von) {
        setVon(von);
        return this;
    }

    public ThongKe donHangChoXacNhan(Long donHangChoXacNhan) {
        setDonHangChoXacNhan(donHangChoXacNhan);
        return this;
    }

    public ThongKe donHangDaXacNhan(Long donHangDaXacNhan) {
        setDonHangDaXacNhan(donHangDaXacNhan);
        return this;
    }

    public ThongKe donHangDangGiao(Long donHangDangGiao) {
        setDonHangDangGiao(donHangDangGiao);
        return this;
    }

    public ThongKe luotDangKyMoi(Long luotDangKyMoi) {
        setLuotDangKyMoi(luotDangKyMoi);
        return this;
    }

    public ThongKe chiPhi(BigDecimal chiPhi) {
        setChiPhi(chiPhi);
        return this;
    }

    public ThongKe luotXemSanPham(Long luotXemSanPham) {
        setLuotXemSanPham(luotXemSanPham);
        return this;
    }

    public ThongKe luotThemGioHang(Long luotThemGioHang) {
        setLuotThemGioHang(luotThemGioHang);
        return this;
    }

    public ThongKe luotDatHang(Long luotDatHang) {
        setLuotDatHang(luotDatHang);
        return this;
    }

    public ThongKe luotThanhToan(Long luotThanhToan) {
        setLuotThanhToan(luotThanhToan);
        return this;
    }

    public ThongKe luotHoanThanhDon(Long luotHoanThanhDon) {
        setLuotHoanThanhDon(luotHoanThanhDon);
        return this;
    }

    public ThongKe NGAYTHEM(String NGAYTHEM) {
        setNGAYTHEM(NGAYTHEM);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ThongKe)) {
            return false;
        }
        ThongKe thongKe = (ThongKe) o;
        return Objects.equals(mathongke, thongKe.mathongke) && Objects.equals(dongHo, thongKe.dongHo)
                && Objects.equals(butKy, thongKe.butKy) && Objects.equals(phuKien, thongKe.phuKien)
                && Objects.equals(trangSuc, thongKe.trangSuc) && Objects.equals(kinhMat, thongKe.kinhMat)
                && Objects.equals(donHang, thongKe.donHang) && Objects.equals(donHangDaHuy, thongKe.donHangDaHuy)
                && Objects.equals(donHangDaGiao, thongKe.donHangDaGiao) && Objects.equals(khachHang, thongKe.khachHang)
                && Objects.equals(luotTruyCap, thongKe.luotTruyCap)
                && Objects.equals(tiLeChuyenDoi, thongKe.tiLeChuyenDoi) && Objects.equals(doanhThu, thongKe.doanhThu)
                && Objects.equals(von, thongKe.von) && Objects.equals(donHangChoXacNhan, thongKe.donHangChoXacNhan)
                && Objects.equals(donHangDaXacNhan, thongKe.donHangDaXacNhan)
                && Objects.equals(donHangDangGiao, thongKe.donHangDangGiao)
                && Objects.equals(luotDangKyMoi, thongKe.luotDangKyMoi) && Objects.equals(chiPhi, thongKe.chiPhi)
                && Objects.equals(luotXemSanPham, thongKe.luotXemSanPham)
                && Objects.equals(luotThemGioHang, thongKe.luotThemGioHang)
                && Objects.equals(luotDatHang, thongKe.luotDatHang)
                && Objects.equals(luotThanhToan, thongKe.luotThanhToan)
                && Objects.equals(luotHoanThanhDon, thongKe.luotHoanThanhDon)
                && Objects.equals(NGAYTHEM, thongKe.NGAYTHEM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathongke, dongHo, butKy, phuKien, trangSuc, kinhMat, donHang, donHangDaHuy, donHangDaGiao,
                khachHang, luotTruyCap, tiLeChuyenDoi, doanhThu, von, donHangChoXacNhan, donHangDaXacNhan,
                donHangDangGiao, luotDangKyMoi, chiPhi, luotXemSanPham, luotThemGioHang, luotDatHang, luotThanhToan,
                luotHoanThanhDon, NGAYTHEM);
    }

    @Override
    public String toString() {
        return "{" +
                " mathongke='" + getMathongke() + "'" +
                ", dongHo='" + getDongHo() + "'" +
                ", butKy='" + getButKy() + "'" +
                ", phuKien='" + getPhuKien() + "'" +
                ", trangSuc='" + getTrangSuc() + "'" +
                ", kinhMat='" + getKinhMat() + "'" +
                ", donHang='" + getDonHang() + "'" +
                ", donHangDaHuy='" + getDonHangDaHuy() + "'" +
                ", donHangDaGiao='" + getDonHangDaGiao() + "'" +
                ", khachHang='" + getKhachHang() + "'" +
                ", luotTruyCap='" + getLuotTruyCap() + "'" +
                ", tiLeChuyenDoi='" + getTiLeChuyenDoi() + "'" +
                ", doanhThu='" + getDoanhThu() + "'" +
                ", von='" + getVon() + "'" +
                ", donHangChoXacNhan='" + getDonHangChoXacNhan() + "'" +
                ", donHangDaXacNhan='" + getDonHangDaXacNhan() + "'" +
                ", donHangDangGiao='" + getDonHangDangGiao() + "'" +
                ", luotDangKyMoi='" + getLuotDangKyMoi() + "'" +
                ", chiPhi='" + getChiPhi() + "'" +
                ", luotXemSanPham='" + getLuotXemSanPham() + "'" +
                ", luotThemGioHang='" + getLuotThemGioHang() + "'" +
                ", luotDatHang='" + getLuotDatHang() + "'" +
                ", luotThanhToan='" + getLuotThanhToan() + "'" +
                ", luotHoanThanhDon='" + getLuotHoanThanhDon() + "'" +
                ", NGAYTHEM='" + getNGAYTHEM() + "'" +
                "}";
    }
}