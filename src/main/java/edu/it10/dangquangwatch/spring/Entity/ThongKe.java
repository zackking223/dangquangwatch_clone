package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "donhangdahuy")
    private Integer donHangDahuy;

    @Column(name = "donhangdagiao")
    private Integer donHangDaGiao;

    @Column(name = "khachhang")
    private Integer khachHang;

    @Column(name = "luongtruycap")
    private Integer luongTruyCap;

    @Column(name = "tilechuyendoi")
    private Integer tiLeChuyenDoi;

    @Column(name = "doanhthu")
    private Integer doanhThu;

    @Column(name = "von")
    private Integer von;

    @Column(name = "NGAYTHEM")
    private String NGAYTHEM;

    public ThongKe() {
    }

    public ThongKe(Integer mathongke, Integer dongHo, Integer butKy, Integer phuKien, Integer trangSuc, Integer kinhMat,
            Integer donHangDahuy, Integer donHangDaGiao, Integer khachHang, Integer luongTruyCap, Integer tiLeChuyenDoi,
            Integer doanhThu, Integer von, String NGAYTHEM) {
        this.mathongke = mathongke;
        this.dongHo = dongHo;
        this.butKy = butKy;
        this.phuKien = phuKien;
        this.trangSuc = trangSuc;
        this.kinhMat = kinhMat;
        this.donHangDahuy = donHangDahuy;
        this.donHangDaGiao = donHangDaGiao;
        this.khachHang = khachHang;
        this.luongTruyCap = luongTruyCap;
        this.tiLeChuyenDoi = tiLeChuyenDoi;
        this.doanhThu = doanhThu;
        this.von = von;
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

    public Integer getDonHangDahuy() {
        return this.donHangDahuy;
    }

    public void setDonHangDahuy(Integer donHangDahuy) {
        this.donHangDahuy = donHangDahuy;
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

    public Integer getLuongTruyCap() {
        return this.luongTruyCap;
    }

    public void setLuongTruyCap(Integer luongTruyCap) {
        this.luongTruyCap = luongTruyCap;
    }

    public Integer getTiLeChuyenDoi() {
        return this.tiLeChuyenDoi;
    }

    public void setTiLeChuyenDoi(Integer tiLeChuyenDoi) {
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

    public ThongKe donHangDahuy(Integer donHangDahuy) {
        setDonHangDahuy(donHangDahuy);
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

    public ThongKe luongTruyCap(Integer luongTruyCap) {
        setLuongTruyCap(luongTruyCap);
        return this;
    }

    public ThongKe tiLeChuyenDoi(Integer tiLeChuyenDoi) {
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
                && Objects.equals(donHangDahuy, thongKe.donHangDahuy)
                && Objects.equals(donHangDaGiao, thongKe.donHangDaGiao) && Objects.equals(khachHang, thongKe.khachHang)
                && Objects.equals(luongTruyCap, thongKe.luongTruyCap)
                && Objects.equals(tiLeChuyenDoi, thongKe.tiLeChuyenDoi) && Objects.equals(doanhThu, thongKe.doanhThu)
                && Objects.equals(von, thongKe.von) && Objects.equals(NGAYTHEM, thongKe.NGAYTHEM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathongke, dongHo, butKy, phuKien, trangSuc, kinhMat, donHangDahuy, donHangDaGiao,
                khachHang, luongTruyCap, tiLeChuyenDoi, doanhThu, von, NGAYTHEM);
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
                ", donHangDahuy='" + getDonHangDahuy() + "'" +
                ", donHangDaGiao='" + getDonHangDaGiao() + "'" +
                ", khachHang='" + getKhachHang() + "'" +
                ", luongTruyCap='" + getLuongTruyCap() + "'" +
                ", tiLeChuyenDoi='" + getTiLeChuyenDoi() + "'" +
                ", doanhThu='" + getDoanhThu() + "'" +
                ", von='" + getVon() + "'" +
                ", NGAYTHEM='" + getNGAYTHEM() + "'" +
                "}";
    }

}