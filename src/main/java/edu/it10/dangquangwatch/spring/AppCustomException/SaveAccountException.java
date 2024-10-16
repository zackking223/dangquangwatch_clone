package edu.it10.dangquangwatch.spring.AppCustomException;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;

public class SaveAccountException extends RuntimeException {
  private String path;
  private TaiKhoan taiKhoan;

  /**
   * sodienthoai, email
   */
  private String type;
  private String entry;
  private ErrorEnum sessionErrorAttribute;

  public ErrorEnum getSessionErrorAttribute() {
    return sessionErrorAttribute;
  }

  public void setSessionErrorAttribute(ErrorEnum sessionErrorAttribute) {
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public SaveAccountException(String message, ErrorEnum sessionErrorAttribute) {
    super(message);
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public SaveAccountException(String message, String entry, ErrorEnum sessionErrorAttribute) {
    super(message);
    this.entry = entry;
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public SaveAccountException(String message, String entry, ErrorEnum sessionErrorAttribute, String type) {
    super(message);
    this.type = type;
    this.entry = entry;
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public SaveAccountException(String message, String entry, ErrorEnum sessionErrorAttribute, String path, String type) {
    super(message);
    this.path = path;
    this.type = type;
    this.entry = entry;
    this.sessionErrorAttribute = sessionErrorAttribute;
  }

  public String getRedirect() {
    return "redirect:" + getPath();
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String pageSource) {
    this.path = pageSource;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEntry() {
    return this.entry;
  }

  public void setEntry(String entry) {
    this.entry = entry;
  }
  
  public TaiKhoan getTaiKhoan() {
    return taiKhoan;
  }

  public void setTaiKhoan(TaiKhoan taiKhoan) {
    this.taiKhoan = taiKhoan;
  }
}
