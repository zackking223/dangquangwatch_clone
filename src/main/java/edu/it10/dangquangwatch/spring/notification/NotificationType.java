package edu.it10.dangquangwatch.spring.notification;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {
  SUCCESS("success"),
  ERROR("error"),
  INFO("info"),
  WARNING("warning");

  private final String value;

  NotificationType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
