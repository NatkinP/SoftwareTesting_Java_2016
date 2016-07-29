package ru.stqa.prt.addressbook.model;

public class ContactNamesData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String group;

  public ContactNamesData(String firstname, String middlename, String lastname, String nickname, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getGroup() {
    return group;
  }
}
