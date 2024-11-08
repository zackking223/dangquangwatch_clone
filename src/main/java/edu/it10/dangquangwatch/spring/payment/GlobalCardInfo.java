package edu.it10.dangquangwatch.spring.payment;

public class GlobalCardInfo extends CardInfo { 
  String verifyCode;
  String cardIssuer;
  String expiryDate;

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getCardIssuer() {
    return cardIssuer;
  }

  public void setCardIssuer(GlobalCardIssuer cardIssuer) {
    this.cardIssuer = cardIssuer.getFullName();
  }

  public void setCardIssuer(String cardIssuer) {
    this.cardIssuer = cardIssuer;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }
}
