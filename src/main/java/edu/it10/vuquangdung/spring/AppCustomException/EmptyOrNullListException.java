package edu.it10.vuquangdung.spring.AppCustomException;

public class EmptyOrNullListException extends RuntimeException {
  public EmptyOrNullListException(String message) {
    super(message);
  }
}