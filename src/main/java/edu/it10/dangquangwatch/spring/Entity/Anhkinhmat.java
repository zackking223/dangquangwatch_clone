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
@Table(name = "anh_kinhmat")
@JsonIgnoreProperties({ "kinhmat" })
public class Anhkinhmat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    @ManyToOne(targetEntity = KinhMat.class)
    @JoinColumn(name = "makinhmat")
    private KinhMat kinhmat;


    public Anhkinhmat() {
    }

    public Anhkinhmat(Integer maanh, String url, String tenanh, KinhMat kinhmat) {
        this.maanh = maanh;
        this.url = url;
        this.tenanh = tenanh;
        this.kinhmat = kinhmat;
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

    public KinhMat getKinhmat() {
        return this.kinhmat;
    }

    public void setKinhmat(KinhMat kinhmat) {
        this.kinhmat = kinhmat;
    }

    public Anhkinhmat maanh(Integer maanh) {
        setMaanh(maanh);
        return this;
    }

    public Anhkinhmat url(String url) {
        setUrl(url);
        return this;
    }

    public Anhkinhmat tenanh(String tenanh) {
        setTenanh(tenanh);
        return this;
    }

    public Anhkinhmat kinhmat(KinhMat kinhmat) {
        setKinhmat(kinhmat);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Anhkinhmat)) {
            return false;
        }
        Anhkinhmat anhkinhmat = (Anhkinhmat) o;
        return Objects.equals(maanh, anhkinhmat.maanh) && Objects.equals(url, anhkinhmat.url) && Objects.equals(tenanh, anhkinhmat.tenanh) && Objects.equals(kinhmat, anhkinhmat.kinhmat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, url, tenanh, kinhmat);
    }

    @Override
    public String toString() {
        return "{" +
            " maanh='" + getMaanh() + "'" +
            ", url='" + getUrl() + "'" +
            ", tenanh='" + getTenanh() + "'" +
            ", kinhmat='" + getKinhmat() + "'" +
            "}";
    }
}