package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "diachi")
  private String diachi;

  @Column(name = "loai_tai_khoan")
  private String loai_tai_khoan;


  public TaiKhoan() {
  }

  public TaiKhoan(String username, String password, String diachi, String loai_tai_khoan) {
    this.username = username;
    this.password = password;
    this.diachi = diachi;
    this.loai_tai_khoan = loai_tai_khoan;
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

  public TaiKhoan username(String username) {
    setUsername(username);
    return this;
  }

  public TaiKhoan password(String password) {
    setPassword(password);
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

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaiKhoan)) {
            return false;
        }
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return Objects.equals(username, taiKhoan.username) && Objects.equals(password, taiKhoan.password) && Objects.equals(diachi, taiKhoan.diachi) && Objects.equals(loai_tai_khoan, taiKhoan.loai_tai_khoan);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, diachi, loai_tai_khoan);
  }

  @Override
  public String toString() {
    return "{" +
      " username='" + getUsername() + "'" +
      ", password='" + getPassword() + "'" +
      ", diachi='" + getDiachi() + "'" +
      ", loai_tai_khoan='" + getLoai_tai_khoan() + "'" +
      "}";
  }
  
}
