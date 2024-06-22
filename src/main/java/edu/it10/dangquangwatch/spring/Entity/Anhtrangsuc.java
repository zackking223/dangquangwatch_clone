package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "anh_trangsuc")
@JsonIgnoreProperties({ "trangsuc" })
public class Anhtrangsuc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maanh;

  @Column(name = "url")
  private String url;

  @Column(name = "tenanh")
  private String tenanh;

  @ManyToOne(targetEntity = Trangsuc.class)
  @JoinColumn(name = "matrangsuc")
  private Trangsuc trangsuc;


  public Anhtrangsuc() {
  }

  public Anhtrangsuc(Integer maanh, String url, String tenanh, Trangsuc trangsuc) {
    this.maanh = maanh;
    this.url = url;
    this.tenanh = tenanh;
    this.trangsuc = trangsuc;
  }

  public Integer getMaanh() {
    return this.maanh;
  }

  public void setMaanh(Integer maanh) {
    this.maanh = maanh;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTenanh() {
    return this.tenanh;
  }

  public void setTenanh(String tenanh) {
    this.tenanh = tenanh;
  }

  public Trangsuc getTrangsuc() {
    return this.trangsuc;
  }

  public void setTrangsuc(Trangsuc trangsuc) {
    this.trangsuc = trangsuc;
  }

  public Anhtrangsuc maanh(Integer maanh) {
    setMaanh(maanh);
    return this;
  }

  public Anhtrangsuc url(String url) {
    setUrl(url);
    return this;
  }

  public Anhtrangsuc tenanh(String tenanh) {
    setTenanh(tenanh);
    return this;
  }

  public Anhtrangsuc trangsuc(Trangsuc trangsuc) {
    setTrangsuc(trangsuc);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Anhtrangsuc)) {
            return false;
        }
        Anhtrangsuc anhtrangsuc = (Anhtrangsuc) o;
        return Objects.equals(maanh, anhtrangsuc.maanh) && Objects.equals(url, anhtrangsuc.url) && Objects.equals(tenanh, anhtrangsuc.tenanh) && Objects.equals(trangsuc, anhtrangsuc.trangsuc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maanh, url, tenanh, trangsuc);
  }

  @Override
  public String toString() {
    return "{" +
      " maanh='" + getMaanh() + "'" +
      ", url='" + getUrl() + "'" +
      ", tenanh='" + getTenanh() + "'" +
      ", trangsuc='" + getTrangsuc() + "'" +
      "}";
  }
}