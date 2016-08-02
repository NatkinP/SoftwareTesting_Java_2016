package ru.stqa.prt.addressbook.model;

public class ContactNamesData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;
  private String group;

  public ContactNamesData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactNamesData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactNamesData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactNamesData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactNamesData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactNamesData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
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


  @Override
  public String toString() {
    return "ContactNamesData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactNamesData that = (ContactNamesData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
