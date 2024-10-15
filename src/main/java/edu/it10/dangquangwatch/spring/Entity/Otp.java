package edu.it10.dangquangwatch.spring.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

  // Phương thức kiểm tra hạn sử dụng
  public boolean isExpired() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
    try {
      Date expiry = dateFormat.parse(expiryDate); // Chuyển đổi chuỗi thành đối tượng Date
      return new Date().after(expiry); // So sánh với ngày hiện tại
    } catch (ParseException e) {
      e.printStackTrace();
      return true; // Nếu có lỗi trong quá trình phân tích, coi như đã hết hạn
    }
  }
}
