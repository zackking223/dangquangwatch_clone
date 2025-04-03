package edu.it10.vuquangdung.spring.AppCustomException;

public class OrderException extends RuntimeException {
  public OrderException(String message) {
    super(message);
  }
}
