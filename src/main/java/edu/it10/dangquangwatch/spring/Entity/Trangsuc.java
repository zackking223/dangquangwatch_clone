package edu.it10.dangquangwatch.spring.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trangsuc")
public class Trangsuc {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long matrangsuc;

  @Column(name = "tentrangsuc")
  private String tentrangsuc;

  @Column(name = "gia")
  private String gia;

  @Column(name = "soluong")
  private String soluong;

  @Column(name = "tragop")
  private String tragop;

  @Column(name = "NGAYTHEM")
  private Date NGAYTHEM;

  @Column(name = "thongtin")
  private String thongtin;

  public Trangsuc() {}

  public Trangsuc(String tentrangsuc, String gia, String soluong, String tragop, Date NGAYTHEM, String thongtin) {  
    this.tentrangsuc = tentrangsuc;  
    this.gia = gia;  
    this.soluong = soluong; 
    this.tragop = tragop;  
    this.NGAYTHEM = NGAYTHEM;  
    this.thongtin = thongtin;  
  }

  public Long getMatrangsuc() {
    return matrangsuc;
  }

  public void setMatrangsuc(Long matrangsuc) {
    this.matrangsuc = matrangsuc;
  }

  public String getTentrangsuc() {
    return tentrangsuc;
  }

  public void setTentrangsuc(String tentrangsuc) {
    this.tentrangsuc = tentrangsuc;
  }

  public String getGia() {
    return gia;
  }

  public void setGia(String gia) {
    this.gia = gia;
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