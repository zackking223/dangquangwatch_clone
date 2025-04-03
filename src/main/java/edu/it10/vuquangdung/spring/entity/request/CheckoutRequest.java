package edu.it10.vuquangdung.spring.entity.request;

import edu.it10.vuquangdung.spring.entity.DonHang;
import edu.it10.vuquangdung.spring.payment.CardInfo;
import jakarta.validation.constraints.NotNull;

public class CheckoutRequest {
  @NotNull(message = "Đơn hàng không được để trống!")
  DonHang donHang;
  CardInfo cardInfo;

  public DonHang getDonHang() {
    return donHang;
  }
  public void setDonHang(DonHang donHang) {
    this.donHang = donHang;
  }
  public CardInfo getCardInfo() {
    return cardInfo;
  }
  public void setCardInfo(CardInfo cardInfo) {
    this.cardInfo = cardInfo;
  }
}
