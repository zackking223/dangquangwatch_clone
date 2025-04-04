package edu.it10.vuquangdung.spring.entity.request;

import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

public class AddBienTheRequest {
    private SanPhamBienThe bienThe;
    private String mauSacId;
    private String kichThuocId;
    private Integer sanPhamId;

    public SanPhamBienThe getBienThe() {
        return bienThe;
    }
    public void setBienThe(SanPhamBienThe bienThe) {
        this.bienThe = bienThe;
    }
    public Integer getSanPhamId() {
        return sanPhamId;
    }
    public void setSanPhamId(Integer sanPhamId) {
        this.sanPhamId = sanPhamId;
    }
    public String getMauSacId() {
        return mauSacId;
    }
    public void setMauSacId(String mauSacId) {
        this.mauSacId = mauSacId;
    }
    public String getKichThuocId() {
        return kichThuocId;
    }
    public void setKichThuocId(String kichThuocId) {
        this.kichThuocId = kichThuocId;
    }
}
