package edu.it10.vuquangdung.spring.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

public class AddSanPhamRequest {
    private SanPham sanPham;
    private List<SanPhamBienThe> bienThes;
    private List<MultipartFile> files;

    public SanPham getSanPham() {
        return sanPham;
    }
    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
    public List<SanPhamBienThe> getBienThes() {
        return bienThes;
    }
    public void setBienThes(List<SanPhamBienThe> bienThes) {
        this.bienThes = bienThes;
    }
    public List<MultipartFile> getFiles() {
        return files;
    }
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
