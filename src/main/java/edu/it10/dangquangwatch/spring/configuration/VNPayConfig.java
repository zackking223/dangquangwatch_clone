package edu.it10.dangquangwatch.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "vnpay")
@Data
public class VNPayConfig {
  private String tmnCode;
  private String hashSecret;
  private String vnpUrl;
  private String returnUrl;
  private String notifyUrl;
}
