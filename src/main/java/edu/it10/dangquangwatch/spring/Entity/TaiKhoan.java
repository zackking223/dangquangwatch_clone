package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "taikhoan")
@JsonIgnoreProperties({ "donHangList" })
public class TaiKhoan {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "hoten")
  private String hoten;

  @Column(name = "sodienthoai", unique = true, nullable = true)
  private String sodienthoai;

  @Column(name = "diachi")
  private String diachi;

  @Column(name = "loai_tai_khoan")
  private String loai_tai_khoan;

  @Column(name = "enabled")
  private Integer enabled;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @OneToMany(mappedBy = "taikhoan")
  private List<DonHang> donHangList;

  public boolean isAdmin() {
    return loai_tai_khoan == "ROLE_QUANTRI";
  }

  public TaiKhoan() {
  }

  public TaiKhoan(String username, String password, String hoten, String sodienthoai, String diachi,
      String loai_tai_khoan, Integer enabled, String NGAYTHEM, List<DonHang> donHangList) {
    this.username = username;
    this.password = password;
    this.hoten = hoten;
    this.sodienthoai = sodienthoai;
    this.diachi = diachi;
    this.loai_tai_khoan = loai_tai_khoan;
    this.enabled = enabled;
    this.NGAYTHEM = NGAYTHEM;
    this.donHangList = donHangList;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getHoten() {
    return this.hoten;
  }

  public void setHoten(String hoten) {
    this.hoten = hoten;
  }

  public String getSodienthoai() {
    return this.sodienthoai;
  }

  public void setSodienthoai(String sodienthoai) {
    this.sodienthoai = sodienthoai;
  }

  public String getDiachi() {
    return this.diachi;
  }

  public void setDiachi(String diachi) {
    this.diachi = diachi;
  }

  public String getLoai_tai_khoan() {
    return this.loai_tai_khoan;
  }

  public void setLoai_tai_khoan(String loai_tai_khoan) {
    this.loai_tai_khoan = loai_tai_khoan;
  }

  public Integer getEnabled() {
    return this.enabled;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public List<DonHang> getDonHangList() {
    return this.donHangList;
  }

  public void setDonHangList(List<DonHang> donHangList) {
    this.donHangList = donHangList;
  }

  public TaiKhoan username(String username) {
    setUsername(username);
    return this;
  }

  public TaiKhoan password(String password) {
    setPassword(password);
    return this;
  }

  public TaiKhoan hoten(String hoten) {
    setHoten(hoten);
    return this;
  }

  public TaiKhoan sodienthoai(String sodienthoai) {
    setSodienthoai(sodienthoai);
    return this;
  }

  public TaiKhoan diachi(String diachi) {
    setDiachi(diachi);
    return this;
  }

  public TaiKhoan loai_tai_khoan(String loai_tai_khoan) {
    setLoai_tai_khoan(loai_tai_khoan);
    return this;
  }

  public TaiKhoan enabled(Integer enabled) {
    setEnabled(enabled);
    return this;
  }

  public TaiKhoan NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public TaiKhoan donHangList(List<DonHang> donHangList) {
    setDonHangList(donHangList);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof TaiKhoan)) {
      return false;
    }
    TaiKhoan taiKhoan = (TaiKhoan) o;
    return Objects.equals(username, taiKhoan.username) && Objects.equals(password, taiKhoan.password)
        && Objects.equals(hoten, taiKhoan.hoten) && Objects.equals(sodienthoai, taiKhoan.sodienthoai)
        && Objects.equals(diachi, taiKhoan.diachi) && Objects.equals(loai_tai_khoan, taiKhoan.loai_tai_khoan)
        && Objects.equals(enabled, taiKhoan.enabled) && Objects.equals(NGAYTHEM, taiKhoan.NGAYTHEM)
        && Objects.equals(donHangList, taiKhoan.donHangList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, hoten, sodienthoai, diachi, loai_tai_khoan, enabled, NGAYTHEM, donHangList);
  }

  @Override
  public String toString() {
    return "{" +
        " username='" + getUsername() + "'" +
        ", password='" + getPassword() + "'" +
        ", hoten='" + getHoten() + "'" +
        ", sodienthoai='" + getSodienthoai() + "'" +
        ", diachi='" + getDiachi() + "'" +
        ", loai_tai_khoan='" + getLoai_tai_khoan() + "'" +
        ", enabled='" + getEnabled() + "'" +
        ", NGAYTHEM='" + getNGAYTHEM() + "'" +
        ", donHangList='" + getDonHangList() + "'" +
        "}";
  }
}
