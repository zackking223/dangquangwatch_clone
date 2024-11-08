package edu.it10.dangquangwatch.spring.payment;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,    // Sử dụng tên kiểu lớp để nhận diện lớp con
    include = JsonTypeInfo.As.PROPERTY, // Thuộc tính sẽ được bao gồm như một phần của JSON
    property = "type"              // Tên thuộc tính nhận diện kiểu lớp con
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = GlobalCardInfo.class, name = "global"),
    @JsonSubTypes.Type(value = LocalCardInfo.class, name = "local")
})
public abstract class CardInfo {
  protected String cardNumber;
  protected String cardOwner;

  public String getCardNumber() {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
  public String getCardOwner() {
    return cardOwner;
  }
  public void setCardOwner(String cardOwner) {
    this.cardOwner = cardOwner;
  }
}