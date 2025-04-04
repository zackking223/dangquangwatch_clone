package edu.it10.vuquangdung.spring.entity.request;

import java.util.List;

import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

public class AddSanPhamRequest {
    private SanPham sanPham;
    private List<SanPhamBienThe> bienTheList;
    private List<AnhSanPham> images;

    public SanPham getSanPham() {
        return sanPham;
    }
    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
    public List<SanPhamBienThe> getBienTheList() {
        return bienTheList;
    }
    public void setBienTheList(List<SanPhamBienThe> bienThes) {
        this.bienTheList = bienThes;
    }
    public List<AnhSanPham> getImages() {
        return images;
    }
    public void setImages(List<AnhSanPham> files) {
        this.images = files;
    }
}
