package edu.it10.vuquangdung.spring.payment;

public enum LocalBank {
  MBBANK("Ngân hàng TMCP Quân đội"),  
  VIETCOMBANK("Ngân hàng TMCP Ngoại thương Việt Nam"),
  BIDV("Ngân hàng TMCP Đầu tư và Phát triển Việt Nam"),
  VIETINBANK("Ngân hàng TMCP Công Thương Việt Nam"),
  AGRIBANK("Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam"),
  TECHCOMBANK("Ngân hàng TMCP Kỹ Thương Việt Nam"),
  SACOMBANK("Ngân hàng TMCP Sài Gòn Thương Tín"),
  ACB("Ngân hàng TMCP Á Châu"),
  VPBANK("Ngân hàng TMCP Việt Nam Thịnh Vượng"),
  HDBANK("Ngân hàng TMCP Phát triển Nhà TP.HCM"),
  TPBANK("Ngân hàng TMCP Tiên Phong"),
  SHB("Ngân hàng TMCP Sài Gòn - Hà Nội");

  private final String fullName;

  // Constructor
  LocalBank(String fullName) {
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
