package edu.it10.vuquangdung.spring.entity.response;

public class ResultItem {
  Integer maSanPham;
  String tenSanPham;
  String loaiSanPham;

  public Integer getMaSanPham() {
    return maSanPham;
  }
  public void setMaSanPham(Integer masanpham) {
    this.maSanPham = masanpham;
  }
  public String getTenSanPham() {
    return tenSanPham;
  }
  public void setTenSanPham(String tensanpham) {
    this.tenSanPham = tensanpham;
  }
  public String getLoaiSanPham() {
    return loaiSanPham;
  }
  public void setLoaiSanPham(String loaisanpham) {
    this.loaiSanPham = loaisanpham;
  }
}
