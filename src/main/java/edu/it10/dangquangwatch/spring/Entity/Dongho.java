package edu.it10.dangquangwatch.spring.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dongho")
public class Dongho {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long madongho;

  @Column(name = "tendongho")
  private String tendongho;

  @Column(name = "giatien")
  private String giatien;

  @Column(name = "soluong")
  private String soluong;

  @Column(name = "tragop")
  private String tragop;

  @Column(name = "duongkinh")
  private String duongkinh;

  @Column(name = "chongnuoc")
  private String chongnuoc;

  @Column(name = "bomay")
  private String bomay;

  @Column(name = "NGAYTHEM")
  private Date NGAYTHEM;

  @Column(name = "thongtin")
  private String thongtin;

  public Dongho() {
  }

  public Dongho(String tendongho, String giatien, String soluong, String tragop, String duongkinh, String chongnuoc,
      String bomay, Date NGAYTHEM, String thongtin) {
    this.tendongho = tendongho;
    this.giatien = giatien;
    this.soluong = soluong;
    this.tragop = tragop;
    this.duongkinh = duongkinh;
    this.chongnuoc = chongnuoc;
    this.bomay = bomay;
    this.NGAYTHEM = NGAYTHEM;
    this.thongtin = thongtin;
  }

  public Long getMadongho() {
    return madongho;
  }

  public void setMadongho(Long madongho) {
    this.madongho = madongho;
  }

  public String getTendongho() {
    return tendongho;
  }

  public void setTendongho(String tendongho) {
    this.tendongho = tendongho;
  }

  public String getGiatien() {
    return giatien;
  }

  public void setGiatien(String giatien) {
    this.giatien = giatien;
  }

  public String getSoluong() {
    return soluong;
  }

  public void setSoluong(String soluong) {
    this.soluong = soluong;
  }

  public String getTragop() {
    return tragop;
  }

  public void setTragop(String tragop) {
    this.tragop = tragop;
  }

  public String getDuongkinh() {
    return duongkinh;
  }

  public void setDuongkinh(String duongkinh) {
    this.duongkinh = duongkinh;
  }

  public String getChongnuoc() {
    return chongnuoc;
  }

  public void setChongnuoc(String chongnuoc) {
    this.chongnuoc = chongnuoc;
  }

  public String getBomay() {
    return bomay;
  }

  public void setBomay(String bomay) {
    this.bomay = bomay;
  }

  public Date getNgaythem() {
    return NGAYTHEM;
  }

  public void setNgaythem(Date NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public String getThongtin() {
    return thongtin;
  }

  public void setThongtin(String thongtin) {
    this.thongtin = thongtin;
  }
}