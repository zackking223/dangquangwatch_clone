package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anh_dongho")
public class Anhdongho {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maanh;

  @Column(name = "madongho")
  private Integer madongho;

  @Column(name = "url")
  private String url;

  @Column(name = "tenanh")
  private String tenanh;

  public Anhdongho() {
  }

  public Anhdongho(Integer maanh, Integer madongho, String url, String tenanh) {
    this.maanh = maanh;
    this.madongho = madongho;
    this.url = url;
    this.tenanh = tenanh;
  }

  public Integer getMaanh() {
    return this.maanh;
  }

  public void setMaanh(Integer maanh) {
    this.maanh = maanh;
  }

  public Integer getMadongho() {
    return this.madongho;
  }

  public void setMadongho(Integer madongho) {
    this.madongho = madongho;
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

  public Anhdongho maanh(Integer maanh) {
    setMaanh(maanh);
    return this;
  }

  public Anhdongho madongho(Integer madongho) {
    setMadongho(madongho);
    return this;
  }

  public Anhdongho url(String url) {
    setUrl(url);
    return this;
  }

  public Anhdongho tenanh(String tenanh) {
    setTenanh(tenanh);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Anhdongho)) {
      return false;
    }
    Anhdongho anhdongho = (Anhdongho) o;
    return Objects.equals(maanh, anhdongho.maanh) && Objects.equals(madongho, anhdongho.madongho)
        && Objects.equals(url, anhdongho.url) && Objects.equals(tenanh, anhdongho.tenanh);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maanh, madongho, url, tenanh);
  }

  @Override
  public String toString() {
    return "{" +
        " maanh='" + getMaanh() + "'" +
        ", madongho='" + getMadongho() + "'" +
        ", url='" + getUrl() + "'" +
        ", tenanh='" + getTenanh() + "'" +
        "}";
  }

}