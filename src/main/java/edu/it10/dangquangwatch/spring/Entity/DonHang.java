package edu.it10.dangquangwatch.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "donhang")
public class DonHang {
  @Id
  @Column(name = "madonhang")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer maDonHang;

  @Column(name = "tongtien")
  private Integer tongTien;

  @Column(name = "diachi")
  private String diaChi;

  @Column(name = "ghichu")
  private String ghiChu;

  /**
   * "Chờ xác nhận" / "Đã xác nhận" / "Đang vận chuyển" / "Đã nhận hàng"
   */
  @Column(name = "tinhtrang")
  private String tinhTrang;

  /**
   * "Đã thanh toán" / "Khi nhận hàng"
   */
  @Column(name = "thanhtoan")
  private String thanhToan;

  @Column(name = "NGAYTHEM")
  private String NGAYTHEM;

  @OneToMany(mappedBy = "donhang", targetEntity = ChiTietDonHang.class)
  private List<ChiTietDonHang> items;

  @ManyToOne(targetEntity = TaiKhoan.class)
  @JoinColumn(name = "username")
  private TaiKhoan taikhoan;

  public String getTongTienFormatted() {
    return NumberFormat.getInstance().format(this.tongTien);
  }

  public DonHang() {
  }

  public DonHang(Integer maDonHang, Integer tongTien, String diaChi, String ghiChu, String tinhTrang, String thanhToan,
      String NGAYTHEM, List<ChiTietDonHang> items, TaiKhoan taikhoan) {
    this.maDonHang = maDonHang;
    this.tongTien = tongTien;
    this.diaChi = diaChi;
    this.ghiChu = ghiChu;
    this.tinhTrang = tinhTrang;
    this.thanhToan = thanhToan;
    this.NGAYTHEM = NGAYTHEM;
    this.items = items;
    this.taikhoan = taikhoan;
  }

  public Integer getMaDonHang() {
    return this.maDonHang;
  }

  public void setMaDonHang(Integer maDonHang) {
    this.maDonHang = maDonHang;
  }

  public Integer getTongTien() {
    return this.tongTien;
  }

  public void setTongTien(Integer tongTien) {
    this.tongTien = tongTien;
  }

  public String getDiaChi() {
    return this.diaChi;
  }

  public void setDiaChi(String diaChi) {
    this.diaChi = diaChi;
  }

  public String getGhiChu() {
    return this.ghiChu;
  }

  public void setGhiChu(String ghiChu) {
    this.ghiChu = ghiChu;
  }

  public String getTinhTrang() {
    return this.tinhTrang;
  }

  public void setTinhTrang(String tinhTrang) {
    this.tinhTrang = tinhTrang;
  }

  public String getThanhToan() {
    return this.thanhToan;
  }

  public void setThanhToan(String thanhToan) {
    this.thanhToan = thanhToan;
  }

  public String getNGAYTHEM() {
    return this.NGAYTHEM;
  }

  public void setNGAYTHEM(String NGAYTHEM) {
    this.NGAYTHEM = NGAYTHEM;
  }

  public List<ChiTietDonHang> getItems() {
    return this.items;
  }

  public void setItems(List<ChiTietDonHang> items) {
    this.items = items;
  }

  public TaiKhoan getTaikhoan() {
    return this.taikhoan;
  }

  public void setTaikhoan(TaiKhoan taikhoan) {
    this.taikhoan = taikhoan;
  }

  public DonHang maDonHang(Integer maDonHang) {
    setMaDonHang(maDonHang);
    return this;
  }

  public DonHang tongTien(Integer tongTien) {
    setTongTien(tongTien);
    return this;
  }

  public DonHang diaChi(String diaChi) {
    setDiaChi(diaChi);
    return this;
  }

  public DonHang ghiChu(String ghiChu) {
    setGhiChu(ghiChu);
    return this;
  }

  public DonHang tinhTrang(String tinhTrang) {
    setTinhTrang(tinhTrang);
    return this;
  }

  public DonHang thanhToan(String thanhToan) {
    setThanhToan(thanhToan);
    return this;
  }

  public DonHang NGAYTHEM(String NGAYTHEM) {
    setNGAYTHEM(NGAYTHEM);
    return this;
  }

  public DonHang items(List<ChiTietDonHang> items) {
    setItems(items);
    return this;
  }

  public DonHang taikhoan(TaiKhoan taikhoan) {
    setTaikhoan(taikhoan);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof DonHang)) {
      return false;
    }
    DonHang donHang = (DonHang) o;
    return Objects.equals(maDonHang, donHang.maDonHang) && Objects.equals(tongTien, donHang.tongTien)
        && Objects.equals(diaChi, donHang.diaChi) && Objects.equals(ghiChu, donHang.ghiChu)
        && Objects.equals(tinhTrang, donHang.tinhTrang) && Objects.equals(thanhToan, donHang.thanhToan)
        && Objects.equals(NGAYTHEM, donHang.NGAYTHEM) && Objects.equals(items, donHang.items)
        && Objects.equals(taikhoan, donHang.taikhoan);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maDonHang, tongTien, diaChi, ghiChu, tinhTrang, thanhToan, NGAYTHEM, items, taikhoan);
  }

  @Override
  public String toString() {
    return "{" +
        " maDonHang='" + getMaDonHang() + "'" +
        ", tongTien='" + getTongTien() + "'" +
        ", diaChi='" + getDiaChi() + "'" +
        ", ghiChu='" + getGhiChu() + "'" +
        ", tinhTrang='" + getTinhTrang() + "'" +
        ", thanhToan='" + getThanhToan() + "'" +
        ", NGAYTHEM='" + getNGAYTHEM() + "'" +
        ", items='" + getItems() + "'" +
        ", taikhoan='" + getTaikhoan() + "'" +
        "}";
  }
}
