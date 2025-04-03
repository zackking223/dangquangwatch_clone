package edu.it10.vuquangdung.spring.payment;

public class PaymentException extends RuntimeException {
  public PaymentException(String message) {
    super(message);
  }
}
