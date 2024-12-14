package edu.it10.dangquangwatch.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vnpay")
public class VNPayConfig {
  private String tmnCode;
  private String hashSecret;
  private String vnpUrl;
  private String returnUrl;
  private String notifyUrl;

  public String getTmnCode() {
    return tmnCode;
  }
  public void setTmnCode(String tmnCode) {
    this.tmnCode = tmnCode;
  }
  public String getHashSecret() {
    return hashSecret;
  }
  public void setHashSecret(String hashSecret) {
    this.hashSecret = hashSecret;
  }
  public String getVnpUrl() {
    return vnpUrl;
  }
  public void setVnpUrl(String vnpUrl) {
    this.vnpUrl = vnpUrl;
  }
  public String getReturnUrl() {
    return returnUrl;
  }
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }
  public String getNotifyUrl() {
    return notifyUrl;
  }
  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}
