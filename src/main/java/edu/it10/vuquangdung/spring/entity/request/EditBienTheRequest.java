package edu.it10.vuquangdung.spring.entity.request;

import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;

public class EditBienTheRequest {
    private SanPhamBienThe bienThe;
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
}
