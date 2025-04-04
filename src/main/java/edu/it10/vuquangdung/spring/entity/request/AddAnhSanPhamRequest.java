package edu.it10.vuquangdung.spring.entity.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AddAnhSanPhamRequest {
    private Integer sanPhamId; 
    private List<MultipartFile> files;
    
    public Integer getSanPhamId() {
        return sanPhamId;
    }
    public void setSanPhamId(Integer bienTheId) {
        this.sanPhamId = bienTheId;
    }
    public List<MultipartFile> getFiles() {
        return files;
    }
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
