package edu.it10.vuquangdung.spring.entity.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SystemAction {
  Add("Thêm"),
  Edit("Sửa"),
  Delete("Xóa"),
  Deactivate("Vô hiệu hóa"),
  Activate("Kích hoạt"),
  PlaceOrder("Đặt đơn hàng"),
  CancelOrder("Hủy đơn hàng"),
  DeliverOrder("Giao đơn hàng"),
  ConfirmOrder("Xác nhận đơn");

  private final String value;

  SystemAction(String value) {
    this.value = value;
  }

  public static List<String> getAllValues() {
    return Arrays.stream(SystemAction.values())
        .map(SystemAction::getValue)
        .collect(Collectors.toList());
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
