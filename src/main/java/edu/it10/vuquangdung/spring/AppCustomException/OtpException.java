package edu.it10.vuquangdung.spring.AppCustomException;

public class OtpException extends ServiceException {
  private boolean sendJson = false;

  public OtpException(String message) {
    this.setMessage(message);
  }

  public OtpException(String message, String path) {
    this.setMessage(message);
    this.setPath(path);
  }

  public OtpException(String message, String path, boolean sendJson) {
    this.setMessage(message);
    this.setPath(path);
    this.setSendJson(sendJson);
  }

  public boolean isSendingJson() {
    return sendJson;
  }

  public void setSendJson(boolean sendJson) {
    this.sendJson = sendJson;
  }
}
