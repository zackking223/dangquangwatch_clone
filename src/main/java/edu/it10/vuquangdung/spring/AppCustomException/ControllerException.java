package edu.it10.vuquangdung.spring.AppCustomException;

public class ControllerException extends RuntimeException {
  private String path;
  private String sessionErrorAttribute;
  private String message;
  private String redirect;

  public ControllerException() {
  }

  public ControllerException(String message, String path) {
    this.path = path;
    this.message = message;
  }

  public ControllerException(String message, ErrorEnum attributeName, String path) {
    this.path = path;
    this.message = message;
    this.sessionErrorAttribute = attributeName.name();
  }

  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }

  public String getRedirect() {
    if (getPath() == null) {
      return null;
    }
    
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

  public String getSessionErrorAttribute() {
    return sessionErrorAttribute;
  }

  public void setSessionErrorAttribute(ErrorEnum sessionErrorAttribute) {
    this.sessionErrorAttribute = sessionErrorAttribute.name();
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
