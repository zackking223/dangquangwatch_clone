package edu.it10.dangquangwatch.spring.entity.enumeration;

public enum OrderPaymentStatus {
  FAILED("Thất bại"),
  CANCELLED("Đã hủy"),
  ON_RECIEVED("Khi nhận hàng"),
  PENDING("Chưa thanh toán"),
  PAID("Đã thanh toán");

  private final String value;

  OrderPaymentStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
