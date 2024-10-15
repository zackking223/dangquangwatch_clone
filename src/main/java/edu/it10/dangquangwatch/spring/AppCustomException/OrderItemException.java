package edu.it10.dangquangwatch.spring.AppCustomException;

public class OrderItemException extends RuntimeException {
  public OrderItemException(String message) {
    super(message);
  }
}
