package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anh_phukien")
public class Anhphukien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "maphukien")
    private Integer maphukien;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    public Anhphukien() {
    }

    public Anhphukien(Integer maanh, Integer maphukien, String url, String tenanh) {
        this.maanh = maanh;
        this.maphukien = maphukien;
        this.url = url;
        this.tenanh = tenanh;
    }

    public Integer getMaanh() {
        return this.maanh;
    }

    public void setMaanh(Integer maanh) {
        this.maanh = maanh;
    }

    public Integer getMaphukien() {
        return this.maphukien;
    }

    public void setMaphukien(Integer maphukien) {
        this.maphukien = maphukien;
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

    public Anhphukien maanh(Integer maanh) {
        setMaanh(maanh);
        return this;
    }

    public Anhphukien maphukien(Integer maphukien) {
        setMaphukien(maphukien);
        return this;
    }

    public Anhphukien url(String url) {
        setUrl(url);
        return this;
    }

    public Anhphukien tenanh(String tenanh) {
        setTenanh(tenanh);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Anhphukien)) {
            return false;
        }
        Anhphukien anhphukien = (Anhphukien) o;
        return Objects.equals(maanh, anhphukien.maanh) && Objects.equals(maphukien, anhphukien.maphukien)
                && Objects.equals(url, anhphukien.url) && Objects.equals(tenanh, anhphukien.tenanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, maphukien, url, tenanh);
    }

    @Override
    public String toString() {
        return "{" +
                " maanh='" + getMaanh() + "'" +
                ", maphukien='" + getMaphukien() + "'" +
                ", url='" + getUrl() + "'" +
                ", tenanh='" + getTenanh() + "'" +
                "}";
    }

}