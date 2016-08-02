package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactNamesData contact = new ContactNamesData()
                                  .withFirstname("Ivan")
                                  .withMiddlename("Zigmoondovich")
                                  .withLastname("Zakipailo")
                                  .withNickname("TeaPot")
                                  .withGroup("test1");
    app.contact().createOrEditContact(contact,true);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt()))));
  }

}
