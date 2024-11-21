package edu.it10.dangquangwatch.spring.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  public void processPayment(CardInfo card) throws PaymentException {
    if (card.getCardNumber().equals("5415 2123 1231 2311")) {
      throw new PaymentException("Số dư không khả dụng!");
    }
    if (card.getCardNumber() != "5415 2123 1231 2312") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    } 
  }

  public void processPayment(GlobalCardInfo card) throws PaymentException {
    if (card.getCardNumber().equals("5415 2123 1231 2311")) {
      throw new PaymentException("Số dư không khả dụng!");
    }
    if (card.getCardNumber() != "5415 2123 1231 2312") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    } 
  }

  public void processPayment(LocalCardInfo card) throws PaymentException {
    if (card.getCardNumber().equals("5415 2123 1231 2311")) {
      throw new PaymentException("Số dư không khả dụng!");
    }
    if (card.getCardNumber() != "5415 2123 1231 2312") {
      throw new PaymentException("Thông tin thẻ không hợp lệ!");
    } 
  }
}
