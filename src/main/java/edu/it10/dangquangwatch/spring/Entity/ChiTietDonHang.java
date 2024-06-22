package edu.it10.dangquangwatch.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "chitietdonhang")
@JsonIgnoreProperties({ "donhang" })
public class ChiTietDonHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maChiTietDonHang;

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

  @ManyToOne(targetEntity = DonHang.class)
  @JoinColumn(name = "madonhang")
  private DonHang donhang;


  public ChiTietDonHang() {
  }

  public ChiTietDonHang(Integer maChiTietDonHang, String loaiSanPham, Integer soLuong, Integer giaTien, Integer maSanPham, String NGAYTHEM, DonHang donhang) {
    this.maChiTietDonHang = maChiTietDonHang;
    this.loaiSanPham = loaiSanPham;
    this.soLuong = soLuong;
    this.giaTien = giaTien;
    this.maSanPham = maSanPham;
    this.NGAYTHEM = NGAYTHEM;
    this.donhang = donhang;
  }

  public Integer getMaChiTietDonHang() {
    return this.maChiTietDonHang;
  }

  public void setMaChiTietDonHang(Integer maChiTietDonHang) {
    this.maChiTietDonHang = maChiTietDonHang;
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

  public DonHang getDonhang() {
    return this.donhang;
  }

  public void setDonhang(DonHang donhang) {
    this.donhang = donhang;
  }

  public ChiTietDonHang maChiTietDonHang(Integer maChiTietDonHang) {
    setMaChiTietDonHang(maChiTietDonHang);
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

  public ChiTietDonHang donhang(DonHang donhang) {
    setDonhang(donhang);
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
        return Objects.equals(maChiTietDonHang, chiTietDonHang.maChiTietDonHang) && Objects.equals(loaiSanPham, chiTietDonHang.loaiSanPham) && Objects.equals(soLuong, chiTietDonHang.soLuong) && Objects.equals(giaTien, chiTietDonHang.giaTien) && Objects.equals(maSanPham, chiTietDonHang.maSanPham) && Objects.equals(NGAYTHEM, chiTietDonHang.NGAYTHEM) && Objects.equals(donhang, chiTietDonHang.donhang);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maChiTietDonHang, loaiSanPham, soLuong, giaTien, maSanPham, NGAYTHEM, donhang);
  }

  @Override
  public String toString() {
    return "{" +
      " maChiTietDonHang='" + getMaChiTietDonHang() + "'" +
      ", loaiSanPham='" + getLoaiSanPham() + "'" +
      ", soLuong='" + getSoLuong() + "'" +
      ", giaTien='" + getGiaTien() + "'" +
      ", maSanPham='" + getMaSanPham() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", donhang='" + getDonhang() + "'" +
      "}";
  }
}
