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

@Entity
@Table(name = "anh_dongho")
public class Anhdongho {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maanh;

  @Column(name = "url")
  private String url;

  @Column(name = "tenanh")
  private String tenanh;

  @ManyToOne(targetEntity = Dongho.class)
  @JoinColumn(name = "madongho", nullable = false)
  private Dongho dongHo;
  

  public Anhdongho() {
  }

  public Anhdongho(Integer maanh, String url, String tenanh, Dongho dongHo) {
    this.maanh = maanh;
    this.url = url;
    this.tenanh = tenanh;
    this.dongHo = dongHo;
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

  public Dongho getDongHo() {
    return this.dongHo;
  }

  public void setDongHo(Dongho dongHo) {
    this.dongHo = dongHo;
  }

  public Anhdongho maanh(Integer maanh) {
    setMaanh(maanh);
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

  public Anhdongho dongHo(Dongho dongHo) {
    setDongHo(dongHo);
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
        return Objects.equals(maanh, anhdongho.maanh) && Objects.equals(url, anhdongho.url) && Objects.equals(tenanh, anhdongho.tenanh) && Objects.equals(dongHo, anhdongho.dongHo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maanh, url, tenanh, dongHo);
  }

  @Override
  public String toString() {
    return "{" +
      " maanh='" + getMaanh() + "'" +
      ", url='" + getUrl() + "'" +
      ", tenanh='" + getTenanh() + "'" +
      ", dongHo='" + getDongHo() + "'" +
      "}";
  }
}