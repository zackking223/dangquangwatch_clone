package edu.it10.vuquangdung.spring.entity;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "anh_sanpham")
@JsonIgnoreProperties({ "sanPham" })
public class AnhSanPham {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "tenanh")
    private String tenanh;

    @Column(name = "mausac_id")
    private String mauSacId;

    @ManyToOne(targetEntity = SanPham.class)
    @JoinColumn(name = "sanpham_id")
    private SanPham sanPham;

    @Transient
    private MultipartFile file;
    
    public Integer getMaSanPham() {
        return sanPham.getId();
    }

    public boolean isCloud() {
        return url.contains("http://res.cloudinary.com");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTenanh() {
        return tenanh;
    }

    public void setTenanh(String tenanh) {
        this.tenanh = tenanh;
    }
    
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMauSacId() {
        return mauSacId;
    }

    public void setMauSacId(String mauSacId) {
        this.mauSacId = mauSacId;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }  
}
