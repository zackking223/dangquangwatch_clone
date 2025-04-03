package edu.it10.vuquangdung.spring.service;

import java.util.Map;
import java.util.HashMap;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import edu.it10.vuquangdung.spring.entity.DonHang;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
  private static final Logger log = LoggerFactory.getLogger(EmailService.class);
  @Autowired
  private JavaMailSender mailSender;
  @Autowired
  private SpringTemplateEngine templateEngine;

  final String confirmAccountTemplate = "email/confirm_account.html";
  final String confirmAccountSubject = "Xác thực tài khoản | Đăng Quang Watch";

  final String confirmPasswordTemplate = "email/confirm_password.html";
  final String confirmPasswordSubject = "Đổi mật khẩu | Đăng Quang Watch";

  final String orderSuccessTemplate = "email/order_success.html";
  final String orderSuccessSubject = "Đặt hàng thành công | Đăng Quang Watch";

  @Async
  public void sendOrderSuccessEmail(DonHang donHang)
    throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(
      mimeMessage,
      MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
      StandardCharsets.UTF_8.name()
    );
    
    messageHelper.setFrom("shopquanaovqd@noreply.com");
    messageHelper.setTo(donHang.getTaikhoan().getUsername());

    Map<String, Object> variables = new HashMap<>();
    variables.put("hoTen", donHang.getTaikhoan().getHoten());
    variables.put("donHang", donHang);

    Context context = new Context();
    context.setVariables(variables);

    messageHelper.setSubject(orderSuccessSubject);

    try {
      String htmlTemplate = templateEngine.process(orderSuccessTemplate, context);
      messageHelper.setText(htmlTemplate, true);

      mailSender.send(mimeMessage);
      log.info(String.format("INFO - Email successfully sent to %s with template %s", donHang.getTaikhoan().getUsername(), orderSuccessTemplate));
    } catch (MessagingException ex) {
      log.warn("WARNING - Cannot send email to {}", donHang.getTaikhoan().getUsername());
    }
  }

  @Async
  public void sendActivateAccountEmail(
    String username,
    String authUrl,
    String toEmail,
    String expiryDate
  )
    throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(
      mimeMessage,
      MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
      StandardCharsets.UTF_8.name()
    );
    
    messageHelper.setFrom("shopquanaovqd@noreply.com");
    messageHelper.setTo(toEmail);

    Map<String, Object> variables = new HashMap<>();
    variables.put("username", username);
    variables.put("authUrl", authUrl);
    variables.put("expiryDate", expiryDate);

    Context context = new Context();
    context.setVariables(variables);

    messageHelper.setSubject(confirmAccountSubject);

    try {
      String htmlTemplate = templateEngine.process(confirmAccountTemplate, context);
      messageHelper.setText(htmlTemplate, true);

      mailSender.send(mimeMessage);
      log.info(String.format("INFO - Email successfully sent to %s with template %s", toEmail, confirmAccountTemplate));
    } catch (MessagingException ex) {
      log.warn("WARNING - Cannot send email to {}", toEmail);
    }
  }

  @Async
  public void sendChangePasswordEmail(
    String username,
    String authUrl,
    String toEmail,
    String expiryDate
  )
    throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(
      mimeMessage,
      MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
      StandardCharsets.UTF_8.name()
    );
    
    messageHelper.setFrom("shopquanaovqd@noreply.com");
    messageHelper.setTo(toEmail);

    Map<String, Object> variables = new HashMap<>();
    variables.put("username", username);
    variables.put("authUrl", authUrl);
    variables.put("expiryDate", expiryDate);

    Context context = new Context();
    context.setVariables(variables);

    messageHelper.setSubject(confirmPasswordSubject);

    try {
      String htmlTemplate = templateEngine.process(confirmPasswordTemplate, context);
      messageHelper.setText(htmlTemplate, true);

      mailSender.send(mimeMessage);
      log.info(String.format("INFO - Email successfully sent to %s with template %s", toEmail, confirmPasswordTemplate));
    } catch (MessagingException ex) {
      log.warn("WARNING - Cannot send email to {}", toEmail);
    }
  }
}
