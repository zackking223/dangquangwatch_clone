package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anh_kinhmat")
public class Anhkinhmat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "makinhmat")
    private Integer makinhmat;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    public Anhkinhmat() {
    }

    public Anhkinhmat(Integer maanh, Integer makinhmat, String url, String tenanh) {
        this.maanh = maanh;
        this.makinhmat = makinhmat;
        this.url = url;
        this.tenanh = tenanh;
    }

    public Integer getMaanh() {
        return this.maanh;
    }

    public void setMaanh(Integer maanh) {
        this.maanh = maanh;
    }

    public Integer getMakinhmat() {
        return this.makinhmat;
    }

    public void setMakinhmat(Integer makinhmat) {
        this.makinhmat = makinhmat;
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

    public Anhkinhmat maanh(Integer maanh) {
        setMaanh(maanh);
        return this;
    }

    public Anhkinhmat makinhmat(Integer makinhmat) {
        setMakinhmat(makinhmat);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Anhkinhmat)) {
            return false;
        }
        Anhkinhmat anhkinhmat = (Anhkinhmat) o;
        return Objects.equals(maanh, anhkinhmat.maanh) && Objects.equals(makinhmat, anhkinhmat.makinhmat)
                && Objects.equals(url, anhkinhmat.url) && Objects.equals(tenanh, anhkinhmat.tenanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, makinhmat, url, tenanh);
    }

    @Override
    public String toString() {
        return "{" +
                " maanh='" + getMaanh() + "'" +
                ", makinhmat='" + getMakinhmat() + "'" +
                ", url='" + getUrl() + "'" +
                ", tenanh='" + getTenanh() + "'" +
                "}";
    }

}