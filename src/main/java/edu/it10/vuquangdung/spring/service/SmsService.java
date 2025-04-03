package edu.it10.vuquangdung.spring.service;

// import com.twilio.rest.api.v2010.account.Message;
// import com.twilio.type.PhoneNumber;
// import edu.it10.vuquangdung.spring.configuration.TwilioConfig;
// import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
  // @Autowired
  // private TwilioConfig twilioConfig;
  // Khai báo biến log thủ công
  private static final Logger log = LoggerFactory.getLogger(SmsService.class);
  public void sendConfirmNumberSms(String to, String otp) {
    log.info("Sent " + to + " with otp: " + otp);
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
