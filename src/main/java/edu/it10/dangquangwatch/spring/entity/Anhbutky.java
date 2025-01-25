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
@Table(name = "anh_butky")
@JsonIgnoreProperties({ "butky" })

public class Anhbutky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maanh;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    @ManyToOne(targetEntity = Butky.class)
    @JoinColumn(name = "mabutky")
    private Butky butky;

    @Transient
    private MultipartFile file;

    public boolean isCloud() {
        return url.contains("http://res.cloudinary.com");
    }

    public Anhbutky() {
    }

    public Anhbutky(Integer maanh, String url, String tenanh, Butky butky, MultipartFile file) {
        this.maanh = maanh;
        this.url = url;
        this.tenanh = tenanh;
        this.butky = butky;
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

    public Butky getButky() {
        return this.butky;
    }

    public void setButky(Butky butky) {
        this.butky = butky;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Anhbutky maanh(Integer maanh) {
        setMaanh(maanh);
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

    public Anhbutky butky(Butky butky) {
        setButky(butky);
        return this;
    }

    public Anhbutky file(MultipartFile file) {
        setFile(file);
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
        return Objects.equals(maanh, anhbutky.maanh) && Objects.equals(url, anhbutky.url)
                && Objects.equals(tenanh, anhbutky.tenanh) && Objects.equals(butky, anhbutky.butky)
                && Objects.equals(file, anhbutky.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maanh, url, tenanh, butky, file);
    }

    @Override
    public String toString() {
        return "{" +
                " maanh='" + getMaanh() + "'" +
                ", url='" + getUrl() + "'" +
                ", tenanh='" + getTenanh() + "'" +
                ", butky='" + getButky() + "'" +
                ", file='" + getFile() + "'" +
                "}";
    }

}