package edu.it10.dangquangwatch.spring.entity.response;

public class ApiResponse {
  private boolean status;
  private String message;

  public ApiResponse(boolean status, String message) {
    this.status = status;
    this.message = message;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}