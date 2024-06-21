package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maChiTietDonHang;

  @Column(name = "madonhang")
  private Integer maDonHang;

  @Column(name = "loaisanpham")
  private String loaiSanPham;

  @Column(name = "soluong")
  private Integer soLuong;

  @Column(name = "giatien")
  private Integer giaTien;

  @Column(name = "masanpham")
  private Integer maSanPham;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  private String tenSanPham;
  private String anhSanPham;


  public ChiTietDonHang() {
  }

  public ChiTietDonHang(Integer maChiTietDonHang, Integer maDonHang, String loaiSanPham, Integer soLuong, Integer giaTien, Integer maSanPham, String NGAYTHEM) {
    this.maChiTietDonHang = maChiTietDonHang;
    this.maDonHang = maDonHang;
    this.loaiSanPham = loaiSanPham;
    this.soLuong = soLuong;
    this.giaTien = giaTien;
    this.maSanPham = maSanPham;
    this.NGAYTHEM = NGAYTHEM;
  }

  public Integer getMaChiTietDonHang() {
    return this.maChiTietDonHang;
  }

  public void setMaChiTietDonHang(Integer maChiTietDonHang) {
    this.maChiTietDonHang = maChiTietDonHang;
  }

  public Integer getMaDonHang() {
    return this.maDonHang;
  }

  public void setMaDonHang(Integer maDonHang) {
    this.maDonHang = maDonHang;
  }

  public String getLoaiSanPham() {
    return this.loaiSanPham;
  }

  public void setLoaiSanPham(String loaiSanPham) {
    this.loaiSanPham = loaiSanPham;
  }

  public Integer getSoLuong() {
    return this.soLuong;
  }

  public void setSoLuong(Integer soLuong) {
    this.soLuong = soLuong;
  }

  public Integer getGiaTien() {
    return this.giaTien;
  }

  public void setGiaTien(Integer giaTien) {
    this.giaTien = giaTien;
  }

  public Integer getMaSanPham() {
    return this.maSanPham;
  }

  public void setMaSanPham(Integer maSanPham) {
    this.maSanPham = maSanPham;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public String getTenSanPham() {
    return this.tenSanPham;
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham = tenSanPham;
  }

  public String getAnhSanPham() {
    return this.anhSanPham;
  }

  public void setAnhSanPham(String anhSanPham) {
    this.anhSanPham = anhSanPham;
  }

  public ChiTietDonHang maChiTietDonHang(Integer maChiTietDonHang) {
    setMaChiTietDonHang(maChiTietDonHang);
    return this;
  }

  public ChiTietDonHang maDonHang(Integer maDonHang) {
    setMaDonHang(maDonHang);
    return this;
  }

  public ChiTietDonHang loaiSanPham(String loaiSanPham) {
    setLoaiSanPham(loaiSanPham);
    return this;
  }

  public ChiTietDonHang soLuong(Integer soLuong) {
    setSoLuong(soLuong);
    return this;
  }

  public ChiTietDonHang giaTien(Integer giaTien) {
    setGiaTien(giaTien);
    return this;
  }

  public ChiTietDonHang maSanPham(Integer maSanPham) {
    setMaSanPham(maSanPham);
    return this;
  }

  public ChiTietDonHang NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public ChiTietDonHang tenSanPham(String tenSanPham) {
    setTenSanPham(tenSanPham);
    return this;
  }

  public ChiTietDonHang anhSanPham(String anhSanPham) {
    setAnhSanPham(anhSanPham);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChiTietDonHang)) {
            return false;
        }
        ChiTietDonHang chiTietDonHang = (ChiTietDonHang) o;
        return Objects.equals(maChiTietDonHang, chiTietDonHang.maChiTietDonHang) && Objects.equals(maDonHang, chiTietDonHang.maDonHang) && Objects.equals(loaiSanPham, chiTietDonHang.loaiSanPham) && Objects.equals(soLuong, chiTietDonHang.soLuong) && Objects.equals(giaTien, chiTietDonHang.giaTien) && Objects.equals(maSanPham, chiTietDonHang.maSanPham) && Objects.equals(NGAYTHEM, chiTietDonHang.NGAYTHEM) && Objects.equals(tenSanPham, chiTietDonHang.tenSanPham) && Objects.equals(anhSanPham, chiTietDonHang.anhSanPham);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maChiTietDonHang, maDonHang, loaiSanPham, soLuong, giaTien, maSanPham, NGAYTHEM, tenSanPham, anhSanPham);
  }

  @Override
  public String toString() {
    return "{" +
      " maChiTietDonHang='" + getMaChiTietDonHang() + "'" +
      ", maDonHang='" + getMaDonHang() + "'" +
      ", loaiSanPham='" + getLoaiSanPham() + "'" +
      ", soLuong='" + getSoLuong() + "'" +
      ", giaTien='" + getGiaTien() + "'" +
      ", maSanPham='" + getMaSanPham() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", tenSanPham='" + getTenSanPham() + "'" +
      ", anhSanPham='" + getAnhSanPham() + "'" +
      "}";
  }
  
}
