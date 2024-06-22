package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

@Entity
@Table(name = "anh_phukien")
@JsonIgnoreProperties({ "phukien" })

public class Anhphukien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    @ManyToOne(targetEntity = PhuKien.class)
    @JoinColumn(name = "maphukien")
    private PhuKien phukien;
    

    public Anhphukien() {
    }

    public Anhphukien(Integer maanh, String url, String tenanh, PhuKien phukien) {
        this.maanh = maanh;
        this.url = url;
        this.tenanh = tenanh;
        this.phukien = phukien;
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

    public PhuKien getPhukien() {
        return this.phukien;
    }

    public void setPhukien(PhuKien phukien) {
        this.phukien = phukien;
    }

    public Anhphukien maanh(Integer maanh) {
        setMaanh(maanh);
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

    public Anhphukien phukien(PhuKien phukien) {
        setPhukien(phukien);
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
        return Objects.equals(maanh, anhphukien.maanh) && Objects.equals(url, anhphukien.url) && Objects.equals(tenanh, anhphukien.tenanh) && Objects.equals(phukien, anhphukien.phukien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, url, tenanh, phukien);
    }

    @Override
    public String toString() {
        return "{" +
            " maanh='" + getMaanh() + "'" +
            ", url='" + getUrl() + "'" +
            ", tenanh='" + getTenanh() + "'" +
            ", phukien='" + getPhukien() + "'" +
            "}";
    }
}