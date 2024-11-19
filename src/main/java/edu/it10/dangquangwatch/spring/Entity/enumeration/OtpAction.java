package edu.it10.dangquangwatch.spring.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OtpAction {
  ChangePassword("change_password"),
  VerifyAccount("verify_account"),
  VerifyPhone("verify_phone");

  private final String value;

  OtpAction(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
