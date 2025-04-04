package edu.it10.vuquangdung.spring.entity;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten")
    @NotNull(message = "Tên sản phẩm không được để trống.")
    @NotEmpty(message = "Tên sản phẩm không được để trống.")
    private String ten;

    @Column(name = "loai")
    @NotNull(message = "Loại sản phẩm không được để trống.")
    @NotEmpty(message = "Loại sản phẩm không được để trống.")
    private String loai;

    @Column(name = "thuonghieu")
    @NotNull(message = "Loại sản phẩm không được để trống.")
    @NotEmpty(message = "Loại sản phẩm không được để trống.")
    private String thuongHieu;

    @Column(name = "ngaythem")
    private String ngayThem;

    @Column(name = "thongtin", columnDefinition = "TEXT")
    @NotEmpty(message = "Thông tin không được để trống.")
    private String thongTin;

    @Column(name = "kichhoat")
    private Integer kichHoat;

    @OneToMany(mappedBy = "sanPham", targetEntity = SanPhamBienThe.class)
    List<SanPhamBienThe> bienTheList;

    @OneToMany(mappedBy = "sanPham", targetEntity = AnhSanPham.class)
    private List<AnhSanPham> images;

    public String getFirstImageUrl() {
        Iterator<AnhSanPham> iterator = images.iterator();

        if (iterator.hasNext()) {
            AnhSanPham retrieved = iterator.next();

            return retrieved.getUrl();
        }

        return "/images/placeholder.png";
    }

    public SanPhamBienThe getFirstBienThe() {
        return bienTheList.get(0);
    }

    public Integer getSoLuong() {
        int soLuong = 0;
        for (SanPhamBienThe bienThe : bienTheList) {
            if (bienThe.getKichHoat() == 1) {
                soLuong += bienThe.getSoLuong();
            }
        }
        return soLuong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String nGAYTHEM) {
        ngayThem = nGAYTHEM;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongtin) {
        this.thongTin = thongtin;
    }

    public Integer getKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(Integer kichhoat) {
        this.kichHoat = kichhoat;
    }

    public List<SanPhamBienThe> getBienTheList() {
        return bienTheList;
    }

    public void setBienTheList(List<SanPhamBienThe> bienTheList) {
        this.bienTheList = bienTheList;
    }

    public List<AnhSanPham> getImages() {
        return images;
    }

    public void setImages(List<AnhSanPham> images) {
        this.images = images;
    }
}
