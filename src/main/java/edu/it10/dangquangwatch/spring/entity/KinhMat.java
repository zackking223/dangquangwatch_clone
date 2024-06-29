package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.CascadeType;
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
import java.util.Objects;

@Entity
@Table(name = "kinhmat")
public class KinhMat {
  @Id
  @Column(name = "makinhmat")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int maKinhMat;

  @Column(name = "tensanpham")
  private String tenSanPham;

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

  @OneToMany(mappedBy = "kinhmat", targetEntity = Anhkinhmat.class, cascade = CascadeType.ALL, orphanRemoval = true)
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

  public KinhMat(int maKinhMat, String tenSanPham, int giaTien, int rongMat, int songMui, int daiGong, String tinhNang, int traGop, int soLuong, String thongTin, String NGAYTHEM, List<Anhkinhmat> images) {
    this.maKinhMat = maKinhMat;
    this.tenSanPham = tenSanPham;
    this.giaTien = giaTien;
    this.rongMat = rongMat;
    this.songMui = songMui;
    this.daiGong = daiGong;
    this.tinhNang = tinhNang;
    this.traGop = traGop;
    this.soLuong = soLuong;
    this.thongTin = thongTin;
    this.NGAYTHEM = NGAYTHEM;
    this.images = images;
  }

  public int getMaKinhMat() {
    return this.maKinhMat;
  }

  public void setMaKinhMat(int maKinhMat) {
    this.maKinhMat = maKinhMat;
  }

  public String getTenSanPham() {
    return this.tenSanPham;
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham = tenSanPham;
  }

  public int getGiaTien() {
    return this.giaTien;
  }

  public void setGiaTien(int giaTien) {
    this.giaTien = giaTien;
  }

  public int getRongMat() {
    return this.rongMat;
  }

  public void setRongMat(int rongMat) {
    this.rongMat = rongMat;
  }

  public int getSongMui() {
    return this.songMui;
  }

  public void setSongMui(int songMui) {
    this.songMui = songMui;
  }

  public int getDaiGong() {
    return this.daiGong;
  }

  public void setDaiGong(int daiGong) {
    this.daiGong = daiGong;
  }

  public String getTinhNang() {
    return this.tinhNang;
  }

  public void setTinhNang(String tinhNang) {
    this.tinhNang = tinhNang;
  }

  public int getTraGop() {
    return this.traGop;
  }

  public void setTraGop(int traGop) {
    this.traGop = traGop;
  }

  public int getSoLuong() {
    return this.soLuong;
  }

  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }

  public String getThongTin() {
    return this.thongTin;
  }

  public void setThongTin(String thongTin) {
    this.thongTin = thongTin;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public List<Anhkinhmat> getImages() {
    return this.images;
  }

  public void setImages(List<Anhkinhmat> images) {
    this.images = images;
  }

  public KinhMat maKinhMat(int maKinhMat) {
    setMaKinhMat(maKinhMat);
    return this;
  }

  public KinhMat tenSanPham(String tenSanPham) {
    setTenSanPham(tenSanPham);
    return this;
  }

  public KinhMat giaTien(int giaTien) {
    setGiaTien(giaTien);
    return this;
  }

  public KinhMat rongMat(int rongMat) {
    setRongMat(rongMat);
    return this;
  }

  public KinhMat songMui(int songMui) {
    setSongMui(songMui);
    return this;
  }

  public KinhMat daiGong(int daiGong) {
    setDaiGong(daiGong);
    return this;
  }

  public KinhMat tinhNang(String tinhNang) {
    setTinhNang(tinhNang);
    return this;
  }

  public KinhMat traGop(int traGop) {
    setTraGop(traGop);
    return this;
  }

  public KinhMat soLuong(int soLuong) {
    setSoLuong(soLuong);
    return this;
  }

  public KinhMat thongTin(String thongTin) {
    setThongTin(thongTin);
    return this;
  }

  public KinhMat NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public KinhMat images(List<Anhkinhmat> images) {
    setImages(images);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof KinhMat)) {
            return false;
        }
        KinhMat kinhMat = (KinhMat) o;
        return maKinhMat == kinhMat.maKinhMat && Objects.equals(tenSanPham, kinhMat.tenSanPham) && giaTien == kinhMat.giaTien && rongMat == kinhMat.rongMat && songMui == kinhMat.songMui && daiGong == kinhMat.daiGong && Objects.equals(tinhNang, kinhMat.tinhNang) && traGop == kinhMat.traGop && soLuong == kinhMat.soLuong && Objects.equals(thongTin, kinhMat.thongTin) && Objects.equals(NGAYTHEM, kinhMat.NGAYTHEM) && Objects.equals(images, kinhMat.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maKinhMat, tenSanPham, giaTien, rongMat, songMui, daiGong, tinhNang, traGop, soLuong, thongTin, NGAYTHEM, images);
  }

  @Override
  public String toString() {
    return "{" +
      " maKinhMat='" + getMaKinhMat() + "'" +
      ", tenSanPham='" + getTenSanPham() + "'" +
      ", giaTien='" + getGiaTien() + "'" +
      ", rongMat='" + getRongMat() + "'" +
      ", songMui='" + getSongMui() + "'" +
      ", daiGong='" + getDaiGong() + "'" +
      ", tinhNang='" + getTinhNang() + "'" +
      ", traGop='" + getTraGop() + "'" +
      ", soLuong='" + getSoLuong() + "'" +
      ", thongTin='" + getThongTin() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", images='" + getImages() + "'" +
      "}";
  }
  
}
