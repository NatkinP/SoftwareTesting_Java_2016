package ru.stqa.prt.addressbook.model;

/**
 * Created by natkin on 03.08.2016.
 */
public class ContactDatailData {
  private String fio;
  private String address;
  private String home;
  private String mobile;
  private String work;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;

  public ContactDatailData withFio(String fio) {
    this.fio = fio;
    return this;
  }

  public ContactDatailData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactDatailData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactDatailData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactDatailData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactDatailData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactDatailData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactDatailData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactDatailData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactDatailData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public String getFio() {
    return fio;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }
}
