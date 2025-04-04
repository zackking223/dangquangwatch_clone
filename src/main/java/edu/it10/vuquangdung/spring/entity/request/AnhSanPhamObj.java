package edu.it10.vuquangdung.spring.entity.request;

import org.springframework.web.multipart.MultipartFile;

public class AnhSanPhamObj {
    private String mauSacId;
    private MultipartFile file;
    
    public String getMauSacId() {
        return mauSacId;
    }
    public void setMauSacId(String mauSacId) {
        this.mauSacId = mauSacId;
    }
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
