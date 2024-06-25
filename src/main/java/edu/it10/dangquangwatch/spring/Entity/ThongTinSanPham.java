package edu.it10.dangquangwatch.spring.entity;
import java.util.Objects;

public class ThongTinSanPham {
  private int masanpham;
  private String tensanpham;
  private String anhsanpham;

  public ThongTinSanPham() {
  }

  public ThongTinSanPham(int masanpham, String tensanpham, String anhsanpham) {
    this.masanpham = masanpham;
    this.tensanpham = tensanpham;
    this.anhsanpham = anhsanpham;
  }

  public int getMasanpham() {
    return this.masanpham;
  }

  public void setMasanpham(int masanpham) {
    this.masanpham = masanpham;
  }

  public String getTensanpham() {
    return this.tensanpham;
  }

  public void setTensanpham(String tensanpham) {
    this.tensanpham = tensanpham;
  }

  public String getAnhsanpham() {
    return this.anhsanpham;
  }

  public void setAnhsanpham(String anhsanpham) {
    this.anhsanpham = anhsanpham;
  }

  public ThongTinSanPham masanpham(int masanpham) {
    setMasanpham(masanpham);
    return this;
  }

  public ThongTinSanPham tensanpham(String tensanpham) {
    setTensanpham(tensanpham);
    return this;
  }

  public ThongTinSanPham anhsanpham(String anhsanpham) {
    setAnhsanpham(anhsanpham);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ThongTinSanPham)) {
            return false;
        }
        ThongTinSanPham thongTinSanPham = (ThongTinSanPham) o;
        return masanpham == thongTinSanPham.masanpham && Objects.equals(tensanpham, thongTinSanPham.tensanpham) && Objects.equals(anhsanpham, thongTinSanPham.anhsanpham);
  }

  @Override
  public int hashCode() {
    return Objects.hash(masanpham, tensanpham, anhsanpham);
  }

  @Override
  public String toString() {
    return "{" +
      " masanpham='" + getMasanpham() + "'" +
      ", tensanpham='" + getTensanpham() + "'" +
      ", anhsanpham='" + getAnhsanpham() + "'" +
      "}";
  }  
}
