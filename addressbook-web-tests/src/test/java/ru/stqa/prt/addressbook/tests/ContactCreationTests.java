package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactNamesData> before = app.getContactHelper().getContactList();
    ContactNamesData contact = new ContactNamesData("Ivan", "Zigmoondovich", "Zakipailo", "TeaPot", "test1");
    app.getContactHelper().createOrEditContact(contact,true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactNamesData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);
    Comparator<? super ContactNamesData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
