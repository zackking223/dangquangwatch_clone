package edu.it10.dangquangwatch.spring.AppCustomException;

public class ServiceException extends RuntimeException {
  private String path;
  private ErrorEnum sessionErrorAttribute;
  private String message;
  private String redirect;

  public ServiceException() {
  }

  public ServiceException(String message) {
    this.message = message;
  }

  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }

  public String getRedirect() {
    if (redirect != null) {
      return redirect;
    }
    return "redirect:" + getPath();
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ErrorEnum getSessionErrorAttribute() {
    return sessionErrorAttribute;
  }

  public void setSessionErrorAttribute(ErrorEnum sessionErrorAttribute) {
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
