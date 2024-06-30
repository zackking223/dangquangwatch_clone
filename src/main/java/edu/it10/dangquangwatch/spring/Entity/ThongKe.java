package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.NumberFormat;
import java.util.Objects;

@Entity
@Table(name = "thongke")
public class ThongKe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mathongke;

    @Column(name = "dongho")
    private Integer dongHo;

    @Column(name = "butky")
    private Integer butKy;

    @Column(name = "phukien")
    private Integer phuKien;

    @Column(name = "trangsuc")
    private Integer trangSuc;

    @Column(name = "kinhmat")
    private Integer kinhMat;

    @Column(name = "donhang")
    private Integer donHang;

    @Column(name = "donhangdahuy")
    private Integer donHangDaHuy;

    @Column(name = "donhangdagiao")
    private Integer donHangDaGiao;

    @Column(name = "khachhang")
    private Integer khachHang;

    @Column(name = "luottruycap")
    private Integer luotTruyCap;

    @Column(name = "tilechuyendoi")
    private Float tiLeChuyenDoi;

    @Column(name = "doanhthu")
    private Integer doanhThu;

    @Column(name = "von")
    private Integer von;

    @Column(name = "donhangchoxacnhan")
    private Integer donHangChoXacNhan;

    @Column(name = "donhangdaxacnhan")
    private Integer donHangDaXacNhan;

    @Column(name = "donhangdanggiao")
    private Integer donHangDangGiao;

    @Column(name = "luotdangkymoi")
    private Integer luotDangKyMoi;

    @Column(name = "chiphi")
    private Integer chiPhi;

    @Column(name = "luotxemsanpham")
    private Integer luotXemSanPham;

    @Column(name = "luotthemgiohang")
    private Integer luotThemGioHang;

    @Column(name = "luotdathang")
    private Integer luotDatHang;

    @Column(name = "luotthanhtoan")
    private Integer luotThanhToan;

    @Column(name = "luothoanthanhdon")
    private Integer luotHoanThanhDon;

    @Column(name = "NGAYTHEM")
    private String NGAYTHEM;

    public String getFormattedVND(Integer num) {
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

    public ThongKe(Integer mathongke, Integer dongHo, Integer butKy, Integer phuKien, Integer trangSuc, Integer kinhMat,
            Integer donHang, Integer donHangDaHuy, Integer donHangDaGiao, Integer khachHang, Integer luotTruyCap,
            Float tiLeChuyenDoi, Integer doanhThu, Integer von, Integer donHangChoXacNhan, Integer donHangDaXacNhan,
            Integer donHangDangGiao, Integer luotDangKyMoi, Integer chiPhi, Integer luotXemSanPham,
            Integer luotThemGioHang, Integer luotDatHang, Integer luotThanhToan, Integer luotHoanThanhDon,
            String NGAYTHEM) {
        this.mathongke = mathongke;
        this.dongHo = dongHo;
        this.butKy = butKy;
        this.phuKien = phuKien;
        this.trangSuc = trangSuc;
        this.kinhMat = kinhMat;
        this.donHang = donHang;
        this.donHangDaHuy = donHangDaHuy;
        this.donHangDaGiao = donHangDaGiao;
        this.khachHang = khachHang;
        this.luotTruyCap = luotTruyCap;
        this.tiLeChuyenDoi = tiLeChuyenDoi;
        this.doanhThu = doanhThu;
        this.von = von;
        this.donHangChoXacNhan = donHangChoXacNhan;
        this.donHangDaXacNhan = donHangDaXacNhan;
        this.donHangDangGiao = donHangDangGiao;
        this.luotDangKyMoi = luotDangKyMoi;
        this.chiPhi = chiPhi;
        this.luotXemSanPham = luotXemSanPham;
        this.luotThemGioHang = luotThemGioHang;
        this.luotDatHang = luotDatHang;
        this.luotThanhToan = luotThanhToan;
        this.luotHoanThanhDon = luotHoanThanhDon;
        this.NGAYTHEM = NGAYTHEM;
    }

    public Integer getMathongke() {
        return this.mathongke;
    }

    public void setMathongke(Integer mathongke) {
        this.mathongke = mathongke;
    }

    public Integer getDongHo() {
        return this.dongHo;
    }

    public void setDongHo(Integer dongHo) {
        this.dongHo = dongHo;
    }

    public Integer getButKy() {
        return this.butKy;
    }

    public void setButKy(Integer butKy) {
        this.butKy = butKy;
    }

    public Integer getPhuKien() {
        return this.phuKien;
    }

    public void setPhuKien(Integer phuKien) {
        this.phuKien = phuKien;
    }

    public Integer getTrangSuc() {
        return this.trangSuc;
    }

    public void setTrangSuc(Integer trangSuc) {
        this.trangSuc = trangSuc;
    }

    public Integer getKinhMat() {
        return this.kinhMat;
    }

    public void setKinhMat(Integer kinhMat) {
        this.kinhMat = kinhMat;
    }

    public Integer getDonHang() {
        return this.donHang;
    }

    public void setDonHang(Integer donHang) {
        this.donHang = donHang;
    }

    public Integer getDonHangDaHuy() {
        return this.donHangDaHuy;
    }

    public void setDonHangDaHuy(Integer donHangDaHuy) {
        this.donHangDaHuy = donHangDaHuy;
    }

    public Integer getDonHangDaGiao() {
        return this.donHangDaGiao;
    }

    public void setDonHangDaGiao(Integer donHangDaGiao) {
        this.donHangDaGiao = donHangDaGiao;
    }

    public Integer getKhachHang() {
        return this.khachHang;
    }

    public void setKhachHang(Integer khachHang) {
        this.khachHang = khachHang;
    }

    public Integer getLuotTruyCap() {
        return this.luotTruyCap;
    }

    public void setLuotTruyCap(Integer luotTruyCap) {
        this.luotTruyCap = luotTruyCap;
    }

    public Float getTiLeChuyenDoi() {
        return this.tiLeChuyenDoi;
    }

    public void setTiLeChuyenDoi(Float tiLeChuyenDoi) {
        this.tiLeChuyenDoi = tiLeChuyenDoi;
    }

    public Integer getDoanhThu() {
        return this.doanhThu;
    }

    public void setDoanhThu(Integer doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Integer getVon() {
        return this.von;
    }

    public void setVon(Integer von) {
        this.von = von;
    }

    public Integer getDonHangChoXacNhan() {
        return this.donHangChoXacNhan;
    }

    public void setDonHangChoXacNhan(Integer donHangChoXacNhan) {
        this.donHangChoXacNhan = donHangChoXacNhan;
    }

    public Integer getDonHangDaXacNhan() {
        return this.donHangDaXacNhan;
    }

    public void setDonHangDaXacNhan(Integer donHangDaXacNhan) {
        this.donHangDaXacNhan = donHangDaXacNhan;
    }

    public Integer getDonHangDangGiao() {
        return this.donHangDangGiao;
    }

    public void setDonHangDangGiao(Integer donHangDangGiao) {
        this.donHangDangGiao = donHangDangGiao;
    }

    public Integer getLuotDangKyMoi() {
        return this.luotDangKyMoi;
    }

    public void setLuotDangKyMoi(Integer luotDangKyMoi) {
        this.luotDangKyMoi = luotDangKyMoi;
    }

    public Integer getChiPhi() {
        return this.chiPhi;
    }

    public void setChiPhi(Integer chiPhi) {
        this.chiPhi = chiPhi;
    }

    public Integer getLuotXemSanPham() {
        return this.luotXemSanPham;
    }

    public void setLuotXemSanPham(Integer luotXemSanPham) {
        this.luotXemSanPham = luotXemSanPham;
    }

    public Integer getLuotThemGioHang() {
        return this.luotThemGioHang;
    }

    public void setLuotThemGioHang(Integer luotThemGioHang) {
        this.luotThemGioHang = luotThemGioHang;
    }

    public Integer getLuotDatHang() {
        return this.luotDatHang;
    }

    public void setLuotDatHang(Integer luotDatHang) {
        this.luotDatHang = luotDatHang;
    }

    public Integer getLuotThanhToan() {
        return this.luotThanhToan;
    }

    public void setLuotThanhToan(Integer luotThanhToan) {
        this.luotThanhToan = luotThanhToan;
    }

    public Integer getLuotHoanThanhDon() {
        return this.luotHoanThanhDon;
    }

    public void setLuotHoanThanhDon(Integer luotHoanThanhDon) {
        this.luotHoanThanhDon = luotHoanThanhDon;
    }

    public String getNGAYTHEM() {
        return this.NGAYTHEM;
    }

    public void setNGAYTHEM(String NGAYTHEM) {
        this.NGAYTHEM = NGAYTHEM;
    }

    public ThongKe mathongke(Integer mathongke) {
        setMathongke(mathongke);
        return this;
    }

    public ThongKe dongHo(Integer dongHo) {
        setDongHo(dongHo);
        return this;
    }

    public ThongKe butKy(Integer butKy) {
        setButKy(butKy);
        return this;
    }

    public ThongKe phuKien(Integer phuKien) {
        setPhuKien(phuKien);
        return this;
    }

    public ThongKe trangSuc(Integer trangSuc) {
        setTrangSuc(trangSuc);
        return this;
    }

    public ThongKe kinhMat(Integer kinhMat) {
        setKinhMat(kinhMat);
        return this;
    }

    public ThongKe donHang(Integer donHang) {
        setDonHang(donHang);
        return this;
    }

    public ThongKe donHangDaHuy(Integer donHangDaHuy) {
        setDonHangDaHuy(donHangDaHuy);
        return this;
    }

    public ThongKe donHangDaGiao(Integer donHangDaGiao) {
        setDonHangDaGiao(donHangDaGiao);
        return this;
    }

    public ThongKe khachHang(Integer khachHang) {
        setKhachHang(khachHang);
        return this;
    }

    public ThongKe luotTruyCap(Integer luotTruyCap) {
        setLuotTruyCap(luotTruyCap);
        return this;
    }

    public ThongKe tiLeChuyenDoi(Float tiLeChuyenDoi) {
        setTiLeChuyenDoi(tiLeChuyenDoi);
        return this;
    }

    public ThongKe doanhThu(Integer doanhThu) {
        setDoanhThu(doanhThu);
        return this;
    }

    public ThongKe von(Integer von) {
        setVon(von);
        return this;
    }

    public ThongKe donHangChoXacNhan(Integer donHangChoXacNhan) {
        setDonHangChoXacNhan(donHangChoXacNhan);
        return this;
    }

    public ThongKe donHangDaXacNhan(Integer donHangDaXacNhan) {
        setDonHangDaXacNhan(donHangDaXacNhan);
        return this;
    }

    public ThongKe donHangDangGiao(Integer donHangDangGiao) {
        setDonHangDangGiao(donHangDangGiao);
        return this;
    }

    public ThongKe luotDangKyMoi(Integer luotDangKyMoi) {
        setLuotDangKyMoi(luotDangKyMoi);
        return this;
    }

    public ThongKe chiPhi(Integer chiPhi) {
        setChiPhi(chiPhi);
        return this;
    }

    public ThongKe luotXemSanPham(Integer luotXemSanPham) {
        setLuotXemSanPham(luotXemSanPham);
        return this;
    }

    public ThongKe luotThemGioHang(Integer luotThemGioHang) {
        setLuotThemGioHang(luotThemGioHang);
        return this;
    }

    public ThongKe luotDatHang(Integer luotDatHang) {
        setLuotDatHang(luotDatHang);
        return this;
    }

    public ThongKe luotThanhToan(Integer luotThanhToan) {
        setLuotThanhToan(luotThanhToan);
        return this;
    }

    public ThongKe luotHoanThanhDon(Integer luotHoanThanhDon) {
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