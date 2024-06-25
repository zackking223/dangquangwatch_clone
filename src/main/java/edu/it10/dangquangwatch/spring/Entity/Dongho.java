package edu.it10.dangquangwatch.spring.entity;

import java.text.NumberFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

import java.util.List;

import java.util.Iterator;

@Entity
@Table(name = "dongho")
public class Dongho {
  @Id
  @Column(name = "madongho")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer madongho;

  @Column(name = "tendongho")
  private String tendongho;

  @Column(name = "giatien")
  private Integer giatien;

  @Column(name = "soluong")
  private Integer soluong;

  @Column(name = "tragop")
  private Integer tragop;

  @Column(name = "duongkinh")
  private Integer duongkinh;

  @Column(name = "chongnuoc")
  private Integer chongnuoc;

  @Column(name = "bomay")
  private String bomay;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @Column(name = "thongtin")
  private String thongtin;

  @Column(name = "chatlieu")
  private String chatlieu;

  @Column(name = "gioitinh")
  private String gioitinh;

  @OneToMany(mappedBy = "dongho", targetEntity = Anhdongho.class)
  private List<Anhdongho> images;

  public String getFirstImageUrl() {
    Iterator<Anhdongho> iterator = images.iterator();

    if (iterator.hasNext()) {
      Anhdongho retrieved = iterator.next();

      return retrieved.getUrl();
    }

    return "/images/placeholder.png";
  }

  public String getGiatienFormatted() {
    return NumberFormat.getInstance().format(this.giatien);
  }


  
  public Dongho() {
  }

  public Dongho(Integer madongho, String tendongho, Integer giatien, Integer soluong, Integer tragop, Integer duongkinh, Integer chongnuoc, String bomay, String NGAYTHEM, String thongtin, String chatlieu, String gioitinh, List<Anhdongho> images) {
    this.madongho = madongho;
    this.tendongho = tendongho;
    this.giatien = giatien;
    this.soluong = soluong;
    this.tragop = tragop;
    this.duongkinh = duongkinh;
    this.chongnuoc = chongnuoc;
    this.bomay = bomay;
    this.NGAYTHEM = NGAYTHEM;
    this.thongtin = thongtin;
    this.chatlieu = chatlieu;
    this.gioitinh = gioitinh;
    this.images = images;
  }

  public Integer getMadongho() {
    return this.madongho;
  }

  public void setMadongho(Integer madongho) {
    this.madongho = madongho;
  }

  public String getTendongho() {
    return this.tendongho;
  }

  public void setTendongho(String tendongho) {
    this.tendongho = tendongho;
  }

  public Integer getGiatien() {
    return this.giatien;
  }

  public void setGiatien(Integer giatien) {
    this.giatien = giatien;
  }

  public Integer getSoluong() {
    return this.soluong;
  }

  public void setSoluong(Integer soluong) {
    this.soluong = soluong;
  }

  public Integer getTragop() {
    return this.tragop;
  }

  public void setTragop(Integer tragop) {
    this.tragop = tragop;
  }

  public Integer getDuongkinh() {
    return this.duongkinh;
  }

  public void setDuongkinh(Integer duongkinh) {
    this.duongkinh = duongkinh;
  }

  public Integer getChongnuoc() {
    return this.chongnuoc;
  }

  public void setChongnuoc(Integer chongnuoc) {
    this.chongnuoc = chongnuoc;
  }

  public String getBomay() {
    return this.bomay;
  }

  public void setBomay(String bomay) {
    this.bomay = bomay;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public String getThongtin() {
    return this.thongtin;
  }

  public void setThongtin(String thongtin) {
    this.thongtin = thongtin;
  }

  public String getChatlieu() {
    return this.chatlieu;
  }

  public void setChatlieu(String chatlieu) {
    this.chatlieu = chatlieu;
  }

  public String getGioitinh() {
    return this.gioitinh;
  }

  public void setGioitinh(String gioitinh) {
    this.gioitinh = gioitinh;
  }

  public List<Anhdongho> getImages() {
    return this.images;
  }

  public void setImages(List<Anhdongho> images) {
    this.images = images;
  }

  public Dongho madongho(Integer madongho) {
    setMadongho(madongho);
    return this;
  }

  public Dongho tendongho(String tendongho) {
    setTendongho(tendongho);
    return this;
  }

  public Dongho giatien(Integer giatien) {
    setGiatien(giatien);
    return this;
  }

  public Dongho soluong(Integer soluong) {
    setSoluong(soluong);
    return this;
  }

  public Dongho tragop(Integer tragop) {
    setTragop(tragop);
    return this;
  }

  public Dongho duongkinh(Integer duongkinh) {
    setDuongkinh(duongkinh);
    return this;
  }

  public Dongho chongnuoc(Integer chongnuoc) {
    setChongnuoc(chongnuoc);
    return this;
  }

  public Dongho bomay(String bomay) {
    setBomay(bomay);
    return this;
  }

  public Dongho NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public Dongho thongtin(String thongtin) {
    setThongtin(thongtin);
    return this;
  }

  public Dongho chatlieu(String chatlieu) {
    setChatlieu(chatlieu);
    return this;
  }

  public Dongho gioitinh(String gioitinh) {
    setGioitinh(gioitinh);
    return this;
  }

  public Dongho images(List<Anhdongho> images) {
    setImages(images);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Dongho)) {
            return false;
        }
        Dongho dongho = (Dongho) o;
        return Objects.equals(madongho, dongho.madongho) && Objects.equals(tendongho, dongho.tendongho) && Objects.equals(giatien, dongho.giatien) && Objects.equals(soluong, dongho.soluong) && Objects.equals(tragop, dongho.tragop) && Objects.equals(duongkinh, dongho.duongkinh) && Objects.equals(chongnuoc, dongho.chongnuoc) && Objects.equals(bomay, dongho.bomay) && Objects.equals(NGAYTHEM, dongho.NGAYTHEM) && Objects.equals(thongtin, dongho.thongtin) && Objects.equals(chatlieu, dongho.chatlieu) && Objects.equals(gioitinh, dongho.gioitinh) && Objects.equals(images, dongho.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(madongho, tendongho, giatien, soluong, tragop, duongkinh, chongnuoc, bomay, NGAYTHEM, thongtin, chatlieu, gioitinh, images);
  }

  @Override
  public String toString() {
    return "{" +
      " madongho='" + getMadongho() + "'" +
      ", tendongho='" + getTendongho() + "'" +
      ", giatien='" + getGiatien() + "'" +
      ", soluong='" + getSoluong() + "'" +
      ", tragop='" + getTragop() + "'" +
      ", duongkinh='" + getDuongkinh() + "'" +
      ", chongnuoc='" + getChongnuoc() + "'" +
      ", bomay='" + getBomay() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", thongtin='" + getThongtin() + "'" +
      ", chatlieu='" + getChatlieu() + "'" +
      ", gioitinh='" + getGioitinh() + "'" +
      ", images='" + getImages() + "'" +
      "}";
  }  
}