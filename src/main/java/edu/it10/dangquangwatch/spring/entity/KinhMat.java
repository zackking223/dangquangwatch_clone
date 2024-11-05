package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "kinhmat")
public class KinhMat {
  @Id
  @Column(name = "makinhmat")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maKinhMat;

  @Column(name = "tenkinhmat")
  private String tenKinhMat;

  @Column(name = "giatien")
  private int giaTien;

  @Column(name = "rongmat")
  private int rongMat; // Rộng mắt

  @Column(name = "songmui")
  private int songMui;

  @Column(name = "daigong")
  private int daiGong;

  @Column(name = "tinhnang")
  private String tinhNang;

  @Column(name = "tragop")
  private int traGop;

  @Column(name = "soluong")
  private int soLuong;

  @Column(name = "thongtin")
  private String thongTin;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @OneToMany(mappedBy = "kinhmat", targetEntity = Anhkinhmat.class)
  private List<Anhkinhmat> images;

  public String getFirstImageUrl() {
    Iterator<Anhkinhmat> iterator = images.iterator();

    if (iterator.hasNext()) {
      Anhkinhmat retrieved = iterator.next();

      return retrieved.getUrl();
    }

    return "/images/placeholder.png";
  }

  public String getGiaTienFormatted() {
    return NumberFormat.getInstance().format(this.giaTien);
  }


  public KinhMat() {
  }

  public Integer getMaKinhMat() {
    return maKinhMat;
  }

  public void setMaKinhMat(Integer maKinhMat) {
    this.maKinhMat = maKinhMat;
  }

  public String getTenKinhMat() {
    return tenKinhMat;
  }

  public void setTenKinhMat(String tenKinhMat) {
    this.tenKinhMat = tenKinhMat;
  }

  public int getGiaTien() {
    return giaTien;
  }

  public void setGiaTien(int giaTien) {
    this.giaTien = giaTien;
  }

  public int getRongMat() {
    return rongMat;
  }

  public void setRongMat(int rongMat) {
    this.rongMat = rongMat;
  }

  public int getSongMui() {
    return songMui;
  }

  public void setSongMui(int songMui) {
    this.songMui = songMui;
  }

  public int getDaiGong() {
    return daiGong;
  }

  public void setDaiGong(int daiGong) {
    this.daiGong = daiGong;
  }

  public String getTinhNang() {
    return tinhNang;
  }

  public void setTinhNang(String tinhNang) {
    this.tinhNang = tinhNang;
  }

  public int getTraGop() {
    return traGop;
  }

  public void setTraGop(int traGop) {
    this.traGop = traGop;
  }

  public int getSoLuong() {
    return soLuong;
  }

  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }

  public String getThongTin() {
    return thongTin;
  }

  public void setThongTin(String thongTin) {
    this.thongTin = thongTin;
  }

  public String getNGAYTHEM() {
    return NGAYTHEM;
  }

  public void setNGAYTHEM(String nGAYTHEM) {
    NGAYTHEM = nGAYTHEM;
  }

  public List<Anhkinhmat> getImages() {
    return images;
  }

  public void setImages(List<Anhkinhmat> images) {
    this.images = images;
  }
}
