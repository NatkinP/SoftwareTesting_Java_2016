package ru.stqa.prt.addressbook;

public class ContactCompanyParam {
  private final String title;
  private final String company;
  private final String address;

  public ContactCompanyParam(String title, String company, String address) {
    this.title = title;
    this.company = company;
    this.address = address;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }
}
