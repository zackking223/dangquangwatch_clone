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
import java.util.Objects;

@Entity
@Table(name = "phukien")
public class PhuKien {
  @Id
  @Column(name = "maphukien")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maPhuKien;

  @Column(name = "tenphukien")
  private String tenPhuKien;

  @Column(name = "giatien")
  private int giaTien;

  @Column(name = "tragop")
  private int traGop;

  @Column(name = "soluong")
  private int soLuong;

  @Column(name = "soluongdatmua")
  private Integer soluongdatmua;

  @Column(name = "kichhoat")
  private Integer kichhoat;

  @Column(name = "thongtin")
  private String thongTin;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @OneToMany(mappedBy = "phukien", targetEntity = Anhphukien.class)
  private List<Anhphukien> images;

  public String getFirstImageUrl() {
    Iterator<Anhphukien> iterator = images.iterator();

    if (iterator.hasNext()) {
      Anhphukien retrieved = iterator.next();

      return retrieved.getUrl();
    }

    return "/images/placeholder.png";
  }

  public String getGiaTienFormatted() {
    return NumberFormat.getInstance().format(this.giaTien);
  }


  public PhuKien() {
  }

  public PhuKien(int maPhuKien, String tenPhuKien, int giaTien, int traGop, int soLuong, String thongTin, String NGAYTHEM, List<Anhphukien> images) {
    this.maPhuKien = maPhuKien;
    this.tenPhuKien = tenPhuKien;
    this.giaTien = giaTien;
    this.traGop = traGop;
    this.soLuong = soLuong;
    this.thongTin = thongTin;
    this.NGAYTHEM = NGAYTHEM;
    this.images = images;
  }

  public Integer getMaPhuKien() {
    return this.maPhuKien;
  }

  public void setMaPhuKien(int maPhuKien) {
    this.maPhuKien = maPhuKien;
  }

  public String getTenPhuKien() {
    return this.tenPhuKien;
  }

  public void setTenPhuKien(String tenPhuKien) {
    this.tenPhuKien = tenPhuKien;
  }

  public int getGiaTien() {
    return this.giaTien;
  }

  public void setGiaTien(int giaTien) {
    this.giaTien = giaTien;
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

  public List<Anhphukien> getImages() {
    return this.images;
  }

  public void setImages(List<Anhphukien> images) {
    this.images = images;
  }

  public PhuKien maPhuKien(int maPhuKien) {
    setMaPhuKien(maPhuKien);
    return this;
  }

  public PhuKien tenPhuKien(String tenPhuKien) {
    setTenPhuKien(tenPhuKien);
    return this;
  }

  public PhuKien giaTien(int giaTien) {
    setGiaTien(giaTien);
    return this;
  }

  public PhuKien traGop(int traGop) {
    setTraGop(traGop);
    return this;
  }

  public PhuKien soLuong(int soLuong) {
    setSoLuong(soLuong);
    return this;
  }

  public PhuKien thongTin(String thongTin) {
    setThongTin(thongTin);
    return this;
  }

  public PhuKien NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public PhuKien images(List<Anhphukien> images) {
    setImages(images);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhuKien)) {
            return false;
        }
        PhuKien phuKien = (PhuKien) o;
        return maPhuKien == phuKien.maPhuKien && Objects.equals(tenPhuKien, phuKien.tenPhuKien) && giaTien == phuKien.giaTien && traGop == phuKien.traGop && soLuong == phuKien.soLuong && Objects.equals(thongTin, phuKien.thongTin) && Objects.equals(NGAYTHEM, phuKien.NGAYTHEM) && Objects.equals(images, phuKien.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maPhuKien, tenPhuKien, giaTien, traGop, soLuong, thongTin, NGAYTHEM, images);
  }

  @Override
  public String toString() {
    return "{" +
      " maPhuKien='" + getMaPhuKien() + "'" +
      ", tenPhuKien='" + getTenPhuKien() + "'" +
      ", giaTien='" + getGiaTien() + "'" +
      ", traGop='" + getTraGop() + "'" +
      ", soLuong='" + getSoLuong() + "'" +
      ", thongTin='" + getThongTin() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", images='" + getImages() + "'" +
      "}";
  }

  public void setMaPhuKien(Integer maPhuKien) {
    this.maPhuKien = maPhuKien;
  }

  public Integer getSoluongdatmua() {
    return soluongdatmua;
  }

  public void setSoluongdatmua(Integer soluongdatmua) {
    this.soluongdatmua = soluongdatmua;
  }

  public Integer getKichhoat() {
    return kichhoat;
  }

  public void setKichhoat(Integer kichhoat) {
    this.kichhoat = kichhoat;
  }  
}
