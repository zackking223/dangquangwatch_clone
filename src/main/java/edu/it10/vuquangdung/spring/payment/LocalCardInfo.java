package edu.it10.vuquangdung.spring.payment;

public class LocalCardInfo extends CardInfo {
  String issueDate;
  String localBank;

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public String getLocalBank() {
    return localBank;
  }

  public void setLocalBank(LocalBank localBank) {
    this.localBank = localBank.getFullName();
  }

  public void setLocalBank(String localBank) {
    this.localBank = localBank;
  }
}
