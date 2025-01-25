package edu.it10.dangquangwatch.spring.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityName {
  DongHo("Đồng hồ"),
  DonHang("Đơn hàng"),
  KinhMat("Kính mắt"),
  ButKy("Bút ký"),
  PhuKien("Phụ kiện"),
  TrangSuc("Trang sức");

  private final String value;

  EntityName(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
