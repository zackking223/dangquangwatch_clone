package edu.it10.dangquangwatch.spring.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import edu.it10.dangquangwatch.spring.configuration.TwilioConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {
  @Autowired
  private TwilioConfig twilioConfig;

  public void sendConfirmNumberSms(String to, String otp) {
    log.info("Sent: " + to + " with otp: " + otp);
    // try {
    //   Message message = Message.creator(
    //       new PhoneNumber(to), // Số điện thoại nhận
    //       new PhoneNumber(twilioConfig.getPhoneNumber()), // Số Twilio của bạn
    //       "Mã xác thực:" + otp // Nội dung tin nhắn
    //   ).create();

    //   log.info("SMS sent! SID: " + message.getSid());
    // } catch (Exception e) {
    //   log.error("Failed to send SMS: " + e.getMessage());
    // }
  }
}
