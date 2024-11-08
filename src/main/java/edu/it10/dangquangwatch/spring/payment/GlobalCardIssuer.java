package edu.it10.dangquangwatch.spring.payment;

public enum GlobalCardIssuer {
  VISA("Visa Inc."),
  MASTERCARD("MasterCard Inc."),
  AMERICAN_EXPRESS("American Express"),
  JCB("Japan Credit Bureau"),
  DISCOVER("Discover Financial Services"),
  UNIONPAY("UnionPay");

  private final String fullName;

  // Constructor
  GlobalCardIssuer(String fullName) {
      this.fullName = fullName;
  }

  // Getter for full name
  public String getFullName() {
      return fullName;
  }

  @Override
  public String toString() {
      return fullName;
  }
}
