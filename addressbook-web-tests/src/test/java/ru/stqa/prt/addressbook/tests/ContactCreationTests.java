package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactData;
import ru.stqa.prt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
                                  .withFirstname("Ivan")
                                  .withMiddlename("Zigmoondovich")
                                  .withLastname("Zakipailo")
                                  .withNickname("TeaPot")
                                  .withGroup("test1")
                                  .withTitle("Boss")
                                  .withCompany("TheBestBossCompany")
                                  .withAddress("123115, USSR, Moscow, Tverskaya st, 1")
                                  .withHome("8-495-111-222-333")
                                  .withMobile("8-915-123-45-67")
                                  .withWork("01")
                                  .withFax("02")
                                  .withEmail2("1-2-3-4@hhh.ig")
                                  .withEmail3("llll@upriamstvo")
                                  ;
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt()))));
  }

  @Test//(enabled = false)
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Ivan'")
            .withMiddlename("Zigmoondovich")
            .withLastname("Zakipailo")
            .withNickname("TeaPot")
            .withGroup("test1")
            .withTitle("Boss")
            .withCompany("TheBestBossCompany")
            .withAddress("123115, USSR, Moscow, Tverskaya st, 1")
            .withHome("8-495-111-222-333")
            .withMobile("8-915-123-45-67")
            .withWork("01")
            .withFax("02")
            .withEmail2("1-2-3-4@hhh.ig")
            .withEmail3("llll@upriamstvo")
            ;
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    assertThat(before.size(), equalTo(app.contact().count()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
