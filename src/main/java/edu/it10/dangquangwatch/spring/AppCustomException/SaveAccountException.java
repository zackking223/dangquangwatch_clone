package edu.it10.dangquangwatch.spring.AppCustomException;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;

public class SaveAccountException extends ServiceException {
  private TaiKhoan taiKhoan;
  /**
   * sodienthoai, email
   */
  private String type;

  public SaveAccountException(String message, ErrorEnum sessionErrorAttribute) {
    super(message);
    setSessionErrorAttribute(sessionErrorAttribute);
  }

  public TaiKhoan getTaiKhoan() {
    return taiKhoan;
  }

  public void setTaiKhoan(TaiKhoan taiKhoan) {
    this.taiKhoan = taiKhoan;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
