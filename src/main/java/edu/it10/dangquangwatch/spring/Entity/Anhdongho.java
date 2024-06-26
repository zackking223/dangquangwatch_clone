package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "anh_dongho")
@JsonIgnoreProperties({ "dongho" })
public class Anhdongho {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maanh;

  @Column(name = "url")
  private String url;

  @Column(name = "tenanh")
  private String tenanh;

  @ManyToOne(targetEntity = Dongho.class)
  @JoinColumn(name = "madongho")
  private Dongho dongho;

  @Transient
  private MultipartFile file;

  public Integer getMadongho() {
    return dongho.getMadongho();
  }

  public boolean isCloud() {
    return tenanh.contains("http://res.cloudinary.com");
  }

  public Anhdongho() {
  }

  public Anhdongho(Integer maanh, String url, String tenanh, Dongho dongho, MultipartFile file) {
    this.maanh = maanh;
    this.url = url;
    this.tenanh = tenanh;
    this.dongho = dongho;
    this.file = file;
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

  public Dongho getDongho() {
    return this.dongho;
  }

  public void setDongho(Dongho dongho) {
    this.dongho = dongho;
  }

  public MultipartFile getFile() {
    return this.file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
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

  public Anhdongho dongho(Dongho dongho) {
    setDongho(dongho);
    return this;
  }

  public Anhdongho file(MultipartFile file) {
    setFile(file);
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
        return Objects.equals(maanh, anhdongho.maanh) && Objects.equals(url, anhdongho.url) && Objects.equals(tenanh, anhdongho.tenanh) && Objects.equals(dongho, anhdongho.dongho) && Objects.equals(file, anhdongho.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maanh, url, tenanh, dongho, file);
  }

  @Override
  public String toString() {
    return "{" +
      " maanh='" + getMaanh() + "'" +
      ", url='" + getUrl() + "'" +
      ", tenanh='" + getTenanh() + "'" +
      ", dongho='" + getDongho() + "'" +
      ", file='" + getFile() + "'" +
      "}";
  }
}