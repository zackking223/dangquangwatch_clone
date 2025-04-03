package edu.it10.vuquangdung.spring.entity.response;

public class ObjectResponse<T> extends ApiResponse {
  T data;

  public ObjectResponse(boolean status, String message, T data) {
    super(status, message);
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
