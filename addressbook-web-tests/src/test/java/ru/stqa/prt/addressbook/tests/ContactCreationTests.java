package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    Set<ContactNamesData> before = app.contact().all();
    ContactNamesData contact = new ContactNamesData()
                                  .withFirstname("Ivan")
                                  .withMiddlename("Zigmoondovich")
                                  .withLastname("Zakipailo")
                                  .withNickname("TeaPot")
                                  .withGroup("test1");
    app.contact().createOrEditContact(contact,true);
    app.goTo().returnToHomePage();
    Set<ContactNamesData> after = app.contact().all();
    Assert.assertEquals(before.size() + 1, after.size());

    contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
