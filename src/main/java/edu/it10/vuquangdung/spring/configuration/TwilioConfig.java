package edu.it10.vuquangdung.spring.configuration;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class TwilioConfig {

  @Value("${twilio.account_sid}")
  private String accountSid;

  @Value("${twilio.auth_token}")
  private String authToken;

  @Value("${twilio.phone_number}")
  private String phoneNumber;

  @PostConstruct
  public void initTwilio() {
    Twilio.init(accountSid, authToken);
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
