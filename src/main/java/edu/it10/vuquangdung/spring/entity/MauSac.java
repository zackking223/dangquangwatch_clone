package edu.it10.vuquangdung.spring.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mausac")
@JsonIgnoreProperties({ "sanPhamBienThe" }) 
public class MauSac {
    @Id
    @Column(name = "mausac_id")
    @NotNull(message = "Mã màu không được để trống.")
    @NotEmpty(message = "Mã màu không được để trống.")
    private String mauSacId;

    @Column(name = "ten")
    @NotNull(message = "Tên màu không được để trống.")
    @NotEmpty(message = "Tên màu không được để trống.")
    private String ten;    

    @OneToMany(mappedBy = "mauSac", targetEntity = SanPhamBienThe.class)
    private List<SanPhamBienThe> sanPhamBienThe;

    public String getMauSacId() {
        return mauSacId;
    }

    public void setMauSacId(String mauSacId) {
        this.mauSacId = mauSacId;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<SanPhamBienThe> getSanPhamBienThe() {
        return sanPhamBienThe;
    }

    public void setSanPhamBienThe(List<SanPhamBienThe> sanPhamBienThe) {
        this.sanPhamBienThe = sanPhamBienThe;
    }
}
