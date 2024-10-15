package edu.it10.dangquangwatch.spring.AppCustomException;

public class EmptyOrNullListException extends RuntimeException {
  public EmptyOrNullListException(String message) {
    super(message);
  }
}