package edu.it10.dangquangwatch.spring.payment;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

@Service
public class PaymentService {
  @Async
  public boolean processPayment(CardInfo card) throws PaymentException {
    try {
      Thread.sleep(2000); // Mô phỏng thời gian chờ 2 giây
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    if (card.getCardNumber() != "888") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    }
    return true;
  }

  @Async
  public boolean processPayment(GlobalCardInfo card) throws PaymentException {
    try {
      Thread.sleep(2000); // Mô phỏng thời gian chờ 2 giây
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    if (card.getCardNumber() != "888") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    }
    return true;
  }

  @Async
  public boolean processPayment(LocalCardInfo card) throws PaymentException {
    try {
      Thread.sleep(2000); // Mô phỏng thời gian chờ 2 giây
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    if (card.getCardNumber() != "888") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    }
    return true;
  }
}
