package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "donhang")
public class DonHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maDonHang;

  @Column(name = "username")
  private String username;

  @Column(name = "tongtien")
  private Integer tongTien;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;


  public DonHang() {
  }

  public DonHang(Integer maDonHang, String username, Integer tongTien, String NGAYTHEM) {
    this.maDonHang = maDonHang;
    this.username = username;
    this.tongTien = tongTien;
    this.NGAYTHEM = NGAYTHEM;
  }

  public Integer getMaDonHang() {
    return this.maDonHang;
  }

  public void setMaDonHang(Integer maDonHang) {
    this.maDonHang = maDonHang;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getTongTien() {
    return this.tongTien;
  }

  public void setTongTien(Integer tongTien) {
    this.tongTien = tongTien;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public DonHang maDonHang(Integer maDonHang) {
    setMaDonHang(maDonHang);
    return this;
  }

  public DonHang username(String username) {
    setUsername(username);
    return this;
  }

  public DonHang tongTien(Integer tongTien) {
    setTongTien(tongTien);
    return this;
  }

  public DonHang NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DonHang)) {
            return false;
        }
        DonHang donHang = (DonHang) o;
        return Objects.equals(maDonHang, donHang.maDonHang) && Objects.equals(username, donHang.username) && Objects.equals(tongTien, donHang.tongTien) && Objects.equals(NGAYTHEM, donHang.NGAYTHEM);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maDonHang, username, tongTien, NGAYTHEM);
  }

  @Override
  public String toString() {
    return "{" +
      " maDonHang='" + getMaDonHang() + "'" +
      ", username='" + getUsername() + "'" +
      ", tongTien='" + getTongTien() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      "}";
  }
}
