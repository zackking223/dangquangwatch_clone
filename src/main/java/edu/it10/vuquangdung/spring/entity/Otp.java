package edu.it10.vuquangdung.spring.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.it10.vuquangdung.spring.entity.enumeration.OtpAction;

@Entity
@Table(name = "otp")
public class Otp {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "action")
  private String action;

  @Column(name = "payload")
  private String payload;

  @Nullable
  @Column(name = "expiry_date")
  private String expiryDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public boolean isExpired() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Định dạng ngày và giờ
    try {
      // Chuyển chuỗi thành LocalDateTime
      LocalDateTime expiry = LocalDateTime.parse(this.expiryDate, dateTimeFormatter);
      // So sánh với thời gian hiện tại
      return LocalDateTime.now().isAfter(expiry);
    } catch (Exception e) {
      e.printStackTrace();
      return true; // Nếu có lỗi, mặc định coi là hết hạn
    }
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setAction(OtpAction action) {
    this.action = action.getValue();
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}
