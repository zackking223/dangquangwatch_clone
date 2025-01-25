package edu.it10.dangquangwatch.spring.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KhoAction {
  Export("Xuất hàng"),
  Import("Nhập hàng");

  private final String value;

  KhoAction(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
