package edu.it10.dangquangwatch.spring.notification;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

public class NotificationBody {
  private String title;
  private String message;
  private NotificationType type;
  @Nullable
  private String url;
  // yyyy-MM-dd'T'HH:mm:ss
  // 2024-10-28T17:30:00 đại diện cho ngày 28 tháng 10 năm 2024 lúc 5 giờ 30 phút chiều.
  @Nullable
  private LocalDateTime timestamp;
  
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public NotificationType getType() {
    return type;
  }
  public void setType(NotificationType type) {
    this.type = type;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}
