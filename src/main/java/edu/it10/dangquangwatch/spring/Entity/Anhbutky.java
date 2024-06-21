package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anh_butky")
public class Anhbutky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "mabutky")
    private Integer mabutky;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    public Anhbutky() {
    }

    public Anhbutky(Integer maanh, Integer mabutky, String url, String tenanh) {
        this.maanh = maanh;
        this.mabutky = mabutky;
        this.url = url;
        this.tenanh = tenanh;
    }

    public Integer getMaanh() {
        return this.maanh;
    }

    public void setMaanh(Integer maanh) {
        this.maanh = maanh;
    }

    public Integer getMabutky() {
        return this.mabutky;
    }

    public void setMabutky(Integer mabutky) {
        this.mabutky = mabutky;
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

    public Anhbutky maanh(Integer maanh) {
        setMaanh(maanh);
        return this;
    }

    public Anhbutky mabutky(Integer mabutky) {
        setMabutky(mabutky);
        return this;
    }

    public Anhbutky url(String url) {
        setUrl(url);
        return this;
    }

    public Anhbutky tenanh(String tenanh) {
        setTenanh(tenanh);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Anhbutky)) {
            return false;
        }
        Anhbutky anhbutky = (Anhbutky) o;
        return Objects.equals(maanh, anhbutky.maanh) && Objects.equals(mabutky, anhbutky.mabutky)
                && Objects.equals(url, anhbutky.url) && Objects.equals(tenanh, anhbutky.tenanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, mabutky, url, tenanh);
    }

    @Override
    public String toString() {
        return "{" +
                " maanh='" + getMaanh() + "'" +
                ", mabutky='" + getMabutky() + "'" +
                ", url='" + getUrl() + "'" +
                ", tenanh='" + getTenanh() + "'" +
                "}";
    }

}