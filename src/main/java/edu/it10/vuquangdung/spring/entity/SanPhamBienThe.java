package edu.it10.vuquangdung.spring.entity;

import java.text.NumberFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sanpham_bienthe")
@JsonIgnoreProperties({ "sanPham" }) 
public class SanPhamBienThe {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = SanPham.class)
    @JoinColumn(name = "sanpham_id")
    private SanPham sanPham;

    @ManyToOne(targetEntity = KichThuoc.class)
    @JoinColumn(name = "kichthuoc_id")
    private KichThuoc kichThuoc;

    @ManyToOne(targetEntity = MauSac.class)
    @JoinColumn(name = "mausac_id")
    private MauSac mauSac;

    @Column(name = "soluong")
    @NotNull(message = "Số lượng không được để trống.")
    private Integer soLuong;

    @Column(name = "soluongdatmua")
    private Integer soLuongDatMua;

    @Column(name = "kichhoat")
    private Integer kichHoat;

    @Column(name = "giatien")
    @NotNull(message = "Giá tiền không được để trống.")
    private Integer giaTien;

    public String getGiaTienFormatted() {
        return NumberFormat.getInstance().format(this.giaTien);
    }

    public Integer getId() {
        return id;
    }

    public Integer getSanPhamId() {
        return sanPham.getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soluong) {
        this.soLuong = soluong;
    }

    public Integer getSoLuongDatMua() {
        return soLuongDatMua;
    }

    public void setSoLuongDatMua(Integer soluongdatmua) {
        this.soLuongDatMua = soluongdatmua;
    }

    public Integer getKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(Integer kichhoat) {
        this.kichHoat = kichhoat;
    }

    public Integer getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Integer giatien) {
        this.giaTien = giatien;
    }
}
