package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "trangsuc")
public class Trangsuc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer matrangsuc;

  @Column(name = "tentrangsuc")
  private String tentrangsuc;

  @Column(name = "gia")
  private Integer gia;

  @Column(name = "soluong")
  private Integer soluong;

  @Column(name = "tragop")
  private Integer tragop;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @Column(name = "thongtin")
  private String thongtin;


  public Trangsuc() {
  }

  public Trangsuc(Integer matrangsuc, String tentrangsuc, Integer gia, Integer soluong, Integer tragop, String NGAYTHEM, String thongtin) {
    this.matrangsuc = matrangsuc;
    this.tentrangsuc = tentrangsuc;
    this.gia = gia;
    this.soluong = soluong;
    this.tragop = tragop;
    this.NGAYTHEM = NGAYTHEM;
    this.thongtin = thongtin;
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

  public Integer getGia() {
    return this.gia;
  }

  public void setGia(Integer gia) {
    this.gia = gia;
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

  public Trangsuc matrangsuc(Integer matrangsuc) {
    setMatrangsuc(matrangsuc);
    return this;
  }

  public Trangsuc tentrangsuc(String tentrangsuc) {
    setTentrangsuc(tentrangsuc);
    return this;
  }

  public Trangsuc gia(Integer gia) {
    setGia(gia);
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

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Trangsuc)) {
            return false;
        }
        Trangsuc trangsuc = (Trangsuc) o;
        return Objects.equals(matrangsuc, trangsuc.matrangsuc) && Objects.equals(tentrangsuc, trangsuc.tentrangsuc) && Objects.equals(gia, trangsuc.gia) && Objects.equals(soluong, trangsuc.soluong) && Objects.equals(tragop, trangsuc.tragop) && Objects.equals(NGAYTHEM, trangsuc.NGAYTHEM) && Objects.equals(thongtin, trangsuc.thongtin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matrangsuc, tentrangsuc, gia, soluong, tragop, NGAYTHEM, thongtin);
  }

  @Override
  public String toString() {
    return "{" +
      " matrangsuc='" + getMatrangsuc() + "'" +
      ", tentrangsuc='" + getTentrangsuc() + "'" +
      ", gia='" + getGia() + "'" +
      ", soluong='" + getSoluong() + "'" +
      ", tragop='" + getTragop() + "'" +
      ", NGAYTHEM='" + getNGAYTHEM() + "'" +
      ", thongtin='" + getThongtin() + "'" +
      "}";
  }

}