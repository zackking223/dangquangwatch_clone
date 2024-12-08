package edu.it10.dangquangwatch.spring.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
  WaitForPayment("Chờ thanh toán"),
  WaitForApproval("Chờ xác nhận"),
  APPROVED("Đã xác nhận"),
  MOVING("Đang vận chuyển"),
  COMPLETED("Đã nhận hàng"),
  CANCELLED("Đã hủy");

  private final String value;

  OrderStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
