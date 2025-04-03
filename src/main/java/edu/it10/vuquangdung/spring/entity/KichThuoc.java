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
@Table(name = "kichthuoc")
@JsonIgnoreProperties({"sanPhamBienThe"})
public class KichThuoc {
    @Id
    @Column(name = "kichthuoc_id")
    @NotNull(message = "Tên kích thước không được để trống.")
    @NotEmpty(message = "Tên kích thước không được để trống.")
    private String kichThuocId;

    @OneToMany(mappedBy = "kichThuoc", targetEntity = SanPhamBienThe.class)
    private List<SanPhamBienThe> sanPhamBienThe;

    public String getKichThuocId() {
        return kichThuocId;
    }

    public void setKichThuocId(String kichThuocId) {
        this.kichThuocId = kichThuocId;
    }

    public List<SanPhamBienThe> getSanPhamBienThe() {
        return sanPhamBienThe;
    }

    public void setSanPhamBienThe(List<SanPhamBienThe> sanPhamBienThe) {
        this.sanPhamBienThe = sanPhamBienThe;
    }
}
