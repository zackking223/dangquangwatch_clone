package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anh_trangsuc")
public class Anhtrangsuc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maanh;

  @Column(name = "matrangsuc")
  private Integer matrangsuc;

  @Column(name = "url")
  private String url;

  @Column(name = "tenanh")
  private String tenanh;

  public Anhtrangsuc() {
  }

  public Anhtrangsuc(Integer maanh, Integer matrangsuc, String url, String tenanh) {
    this.maanh = maanh;
    this.matrangsuc = matrangsuc;
    this.url = url;
    this.tenanh = tenanh;
  }

  public Integer getMaanh() {
    return this.maanh;
  }

  public void setMaanh(Integer maanh) {
    this.maanh = maanh;
  }

  public Integer getMatrangsuc() {
    return this.matrangsuc;
  }

  public void setMatrangsuc(Integer matrangsuc) {
    this.matrangsuc = matrangsuc;
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

  public Anhtrangsuc maanh(Integer maanh) {
    setMaanh(maanh);
    return this;
  }

  public Anhtrangsuc matrangsuc(Integer matrangsuc) {
    setMatrangsuc(matrangsuc);
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

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Anhtrangsuc)) {
      return false;
    }
    Anhtrangsuc anhtrangsuc = (Anhtrangsuc) o;
    return Objects.equals(maanh, anhtrangsuc.maanh) && Objects.equals(matrangsuc, anhtrangsuc.matrangsuc)
        && Objects.equals(url, anhtrangsuc.url) && Objects.equals(tenanh, anhtrangsuc.tenanh);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maanh, matrangsuc, url, tenanh);
  }

  @Override
  public String toString() {
    return "{" +
        " maanh='" + getMaanh() + "'" +
        ", matrangsuc='" + getMatrangsuc() + "'" +
        ", url='" + getUrl() + "'" +
        ", tenanh='" + getTenanh() + "'" +
        "}";
  }
}