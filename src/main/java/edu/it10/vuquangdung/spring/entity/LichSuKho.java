package edu.it10.vuquangdung.spring.entity;

import org.hibernate.annotations.CreationTimestamp;

import edu.it10.vuquangdung.spring.entity.enumeration.KhoAction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lichsukho")
public class LichSuKho {
  @Id
  @Column(name = "malichsu")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int maLichSu;

  @Column(name = "hanhdong")
  private String hanhDong;

  @Column(name = "thongtin", columnDefinition = "TEXT")
  private String thongTin;

  @Column(name = "thoigian")
  @CreationTimestamp
  private String thoiGian;

  @Column(name = "nguoithuchien")
  private String nguoiThucHien;

  public LichSuKho() {
  }

  public LichSuKho(int maLichSu, String hanhDong, String thongTin, String thoiGian, String nguoiThucHien) {
    this.maLichSu = maLichSu;
    this.hanhDong = hanhDong;
    this.thongTin = thongTin;
    this.thoiGian = thoiGian;
    this.nguoiThucHien = nguoiThucHien;
  }

  public int getMaLichSu() {
    return maLichSu;
  }

  public void setMaLichSu(int maLichSu) {
    this.maLichSu = maLichSu;
  }

  public String getHanhDong() {
    return hanhDong;
  }

  public void setHanhDong(String hanhDong) {
    this.hanhDong = hanhDong;
  }

  public void setHanhDong(KhoAction hanhDong) {
    this.hanhDong = hanhDong.getValue();
  }

  public String getThongTin() {
    return thongTin;
  }

  public void setThongTin(String thongTin) {
    this.thongTin = thongTin;
  }

  public String getThoiGian() {
    return thoiGian;
  }

  public void setThoiGian(String thoiGian) {
    this.thoiGian = thoiGian;
  }

  public String getNguoiThucHien() {
    return nguoiThucHien;
  }

  public void setNguoiThucHien(String nguoiThucHien) {
    this.nguoiThucHien = nguoiThucHien;
  }
}
