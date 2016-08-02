package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    List<ContactNamesData> before = app.contact().list();
    ContactNamesData contact = new ContactNamesData()
                                  .withFirstname("Ivan")
                                  .withMiddlename("Zigmoondovich")
                                  .withLastname("Zakipailo")
                                  .withNickname("TeaPot")
                                  .withGroup("test1");
    app.contact().createOrEditContact(contact,true);
    app.goTo().returnToHomePage();
    List<ContactNamesData> after = app.contact().list();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);
    Comparator<? super ContactNamesData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
