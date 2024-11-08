package edu.it10.dangquangwatch.spring.payment;

public class PaymentException extends RuntimeException {
  public PaymentException(String message) {
    super(message);
  }
}
