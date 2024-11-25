package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trangsuc")
public class Trangsuc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer matrangsuc;

  @Column(name = "tentrangsuc")
  @NotNull(message = "Tên trang sức không được để trống.")
  @NotEmpty(message = "Tên trang sức không được để trống.")
  private String tentrangsuc;

  @Column(name = "giatien")
  @NotNull(message = "Giá tiền không được để trống.")
  private Integer giaTien;

  @Column(name = "soluong")
  @NotNull(message = "Số lượng không được để trống.")
  private Integer soluong;

  @Column(name = "soluongdatmua")
  private Integer soluongdatmua;

  @Column(name = "kichhoat")
  private Integer kichhoat;

  @Column(name = "tragop")
  @NotNull(message = "Trả góp không được để trống.")
  private Integer tragop;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @Column(name = "thongtin")
  @NotNull(message = "Thông tin không được để trống.")
  @NotEmpty(message = "Thông tin không được để trống.")
  private String thongtin;

  @OneToMany(mappedBy = "trangsuc", targetEntity = Anhtrangsuc.class)
  private List<Anhtrangsuc> images;

  public String getFirstImageUrl() {
    Iterator<Anhtrangsuc> iterator = images.iterator();

    if (iterator.hasNext()) {
      Anhtrangsuc retrieved = iterator.next();

      return retrieved.getUrl();
    }

    return "/images/placeholder.png";
  }

  public String getGiaTienFormatted() {
    return NumberFormat.getInstance().format(this.giaTien);
  }


  public Trangsuc() {
  }

  public Trangsuc(Integer matrangsuc, String tentrangsuc, Integer gia, Integer soluong, Integer tragop, String NGAYTHEM, String thongtin, List<Anhtrangsuc> images) {
    this.matrangsuc = matrangsuc;
    this.tentrangsuc = tentrangsuc;
    this.giaTien = gia;
    this.soluong = soluong;
    this.tragop = tragop;
    this.NGAYTHEM = NGAYTHEM;
    this.thongtin = thongtin;
    this.images = images;
  }

  public Integer getMatrangsuc() {
    return this.matrangsuc;
  }

  public void setMatrangsuc(Integer matrangsuc) {
    this.matrangsuc = matrangsuc;
  }

  public String getTentrangsuc() {
    return this.tentrangsuc;
  }

  public void setTentrangsuc(String tentrangsuc) {
    this.tentrangsuc = tentrangsuc;
  }

  public Integer getGiaTien() {
    return this.giaTien;
  }

  public void setGiaTien(Integer gia) {
    this.giaTien = gia;
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

  public List<Anhtrangsuc> getImages() {
    return this.images;
  }

  public void setImages(List<Anhtrangsuc> images) {
    this.images = images;
  }

  public Trangsuc matrangsuc(Integer matrangsuc) {
    setMatrangsuc(matrangsuc);
    return this;
  }

  public Trangsuc tentrangsuc(String tentrangsuc) {
    setTentrangsuc(tentrangsuc);
    return this;
  }

  public Trangsuc gia(Integer gia) {
    setGiaTien(gia);
    return this;
  }

  public Trangsuc soluong(Integer soluong) {
    setSoluong(soluong);
    return this;
  }

  public Trangsuc tragop(Integer tragop) {
    setTragop(tragop);
    return this;
  }

  public Trangsuc NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public Trangsuc thongtin(String thongtin) {
    setThongtin(thongtin);
    return this;
  }

  public Trangsuc images(List<Anhtrangsuc> images) {
    setImages(images);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Trangsuc)) {
            return false;
        }
        Trangsuc trangsuc = (Trangsuc) o;
        return Objects.equals(matrangsuc, trangsuc.matrangsuc) && Objects.equals(tentrangsuc, trangsuc.tentrangsuc) && Objects.equals(giaTien, trangsuc.giaTien) && Objects.equals(soluong, trangsuc.soluong) && Objects.equals(tragop, trangsuc.tragop) && Objects.equals(NGAYTHEM, trangsuc.NGAYTHEM) && Objects.equals(thongtin, trangsuc.thongtin) && Objects.equals(images, trangsuc.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matrangsuc, tentrangsuc, giaTien, soluong, tragop, NGAYTHEM, thongtin, images);
  }

  @Override
  public String toString() {
    return "{" +
      " matrangsuc='" + getMatrangsuc() + "'" +
      ", tentrangsuc='" + getTentrangsuc() + "'" +
      ", gia='" + getGiaTien() + "'" +
      ", soluong='" + getSoluong() + "'" +
      ", tragop='" + getTragop() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", thongtin='" + getThongtin() + "'" +
      ", images='" + getImages() + "'" +
      "}";
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