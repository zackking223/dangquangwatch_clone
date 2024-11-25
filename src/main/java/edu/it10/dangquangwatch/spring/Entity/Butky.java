package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "butky")
public class Butky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mabutky;

    @Column(name = "tenbutky")
    @NotNull(message = "Tên bút ký không được để trống.")
    @NotEmpty(message = "Tên bút ký không được để trống.")
    private String tenbutky;

    @Column(name = "giatien")
    @NotNull(message = "Giá tiền không được để trống.")
    private Integer giatien;

    @Column(name = "soluong")
    private Integer soluong;

    @Column(name = "soluongdatmua")
    private Integer soluongdatmua;

    @Column(name = "kichhoat")
    private Integer kichhoat;

    @Column(name = "tragop")
    @NotNull(message = "Trả góp không được để trống.")
    private Integer tragop;

    @Column(name = "NGAYTHEM")
    private String NGAYTHEM;

    @Column(name = "thongtin")
    @NotNull(message = "Thông tin không được để trống.")
    @NotEmpty(message = "Thông tin không được để trống.")
    private String thongtin;

    @OneToMany(mappedBy = "butky", targetEntity = Anhbutky.class)
    private List<Anhbutky> images;

    public String getFirstImageUrl() {
        Iterator<Anhbutky> iterator = images.iterator();

        if (iterator.hasNext()) {
            Anhbutky retrieved = iterator.next();

            return retrieved.getUrl();
        }

        return "/images/placeholder.png";
    }

    public String getGiatienFormatted() {
        return NumberFormat.getInstance().format(this.giatien);
    }

    public Butky() {
    }

    public Butky(Integer mabutky, String tenbutky, Integer giatien, Integer soluong, Integer tragop, String NGAYTHEM,
            String thongtin, List<Anhbutky> images) {
        this.mabutky = mabutky;
        this.tenbutky = tenbutky;
        this.giatien = giatien;
        this.soluong = soluong;
        this.tragop = tragop;
        this.NGAYTHEM = NGAYTHEM;
        this.thongtin = thongtin;
        this.images = images;
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

    public List<Anhbutky> getImages() {
        return this.images;
    }

    public void setImages(List<Anhbutky> images) {
        this.images = images;
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

    public Butky images(List<Anhbutky> images) {
        setImages(images);
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
                && Objects.equals(thongtin, butky.thongtin) && Objects.equals(images, butky.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mabutky, tenbutky, giatien, soluong, tragop, NGAYTHEM, thongtin, images);
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
                ", images='" + getImages() + "'" +
                "}";
    }

    public Integer getSoluongdatmua() {
        return soluongdatmua;
    }

    public void setSoluongdatmua(Integer soluongdatmua) {
        this.soluongdatmua = soluongdatmua;
    }

    public Integer getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(Integer kichhoat) {
        this.kichhoat = kichhoat;
    }
}