package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "butky")
public class Butky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mabutky;

    @Column(name = "tenbutky")
    private String tenbutky;

    @Column(name = "giatien")
    private Integer giatien;

    @Column(name = "soluong")
    private Integer soluong;

    @Column(name = "tragop")
    private Integer tragop;

    @Column(name = "NGAYTHEM")
    private String NGAYTHEM;

    @Column(name = "thongtin")
    private String thongtin;

    public Butky() {
    }

    public Butky(Integer mabutky, String tenbutky, Integer giatien, Integer soluong, Integer tragop, String NGAYTHEM,
            String thongtin) {
        this.mabutky = mabutky;
        this.tenbutky = tenbutky;
        this.giatien = giatien;
        this.soluong = soluong;
        this.tragop = tragop;
        this.NGAYTHEM = NGAYTHEM;
        this.thongtin = thongtin;
    }

    public Integer getMabutky() {
        return this.mabutky;
    }

    public void setMabutky(Integer mabutky) {
        this.mabutky = mabutky;
    }

    public String getTenbutky() {
        return this.tenbutky;
    }

    public void setTenbutky(String tenbutky) {
        this.tenbutky = tenbutky;
    }

    public Integer getGiatien() {
        return this.giatien;
    }

    public void setGiatien(Integer giatien) {
        this.giatien = giatien;
    }

    public Integer getSoluong() {
        return this.soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getTragop() {
        return this.tragop;
    }

    public void setTragop(Integer tragop) {
        this.tragop = tragop;
    }

    public String getNGAYTHEM() {
        return this.NGAYTHEM;
    }

    public void setNGAYTHEM(String NGAYTHEM) {
        this.NGAYTHEM = NGAYTHEM;
    }

    public String getThongtin() {
        return this.thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public Butky mabutky(Integer mabutky) {
        setMabutky(mabutky);
        return this;
    }

    public Butky tenbutky(String tenbutky) {
        setTenbutky(tenbutky);
        return this;
    }

    public Butky giatien(Integer giatien) {
        setGiatien(giatien);
        return this;
    }

    public Butky soluong(Integer soluong) {
        setSoluong(soluong);
        return this;
    }

    public Butky tragop(Integer tragop) {
        setTragop(tragop);
        return this;
    }

    public Butky NGAYTHEM(String NGAYTHEM) {
        setNGAYTHEM(NGAYTHEM);
        return this;
    }

    public Butky thongtin(String thongtin) {
        setThongtin(thongtin);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Butky)) {
            return false;
        }
        Butky butky = (Butky) o;
        return Objects.equals(mabutky, butky.mabutky) && Objects.equals(tenbutky, butky.tenbutky)
                && Objects.equals(giatien, butky.giatien) && Objects.equals(soluong, butky.soluong)
                && Objects.equals(tragop, butky.tragop) && Objects.equals(NGAYTHEM, butky.NGAYTHEM)
                && Objects.equals(thongtin, butky.thongtin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mabutky, tenbutky, giatien, soluong, tragop, NGAYTHEM, thongtin);
    }

    @Override
    public String toString() {
        return "{" +
                " mabutky='" + getMabutky() + "'" +
                ", tenbutky='" + getTenbutky() + "'" +
                ", giatien='" + getGiatien() + "'" +
                ", soluong='" + getSoluong() + "'" +
                ", tragop='" + getTragop() + "'" +
                ", NGAYTHEM='" + getNGAYTHEM() + "'" +
                ", thongtin='" + getThongtin() + "'" +
                "}";
    }

}