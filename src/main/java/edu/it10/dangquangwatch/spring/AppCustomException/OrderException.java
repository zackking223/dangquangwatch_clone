package edu.it10.dangquangwatch.spring.AppCustomException;

public class OrderException extends RuntimeException {
  public OrderException(String message) {
    super(message);
  }
}
