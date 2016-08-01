package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by natkin on 22.07.2016.
 */
public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createOrEditContact(new ContactNamesData("Ivan", "Zigmoondovich", "Zakipailo", "TeaPot", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactNamesData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size());
    ContactNamesData contact = new ContactNamesData(before.get(before.size() - 1).getId(), "Ivan2", "Zigmoondovich2", "Zakipailo2", "TeaPot", null);
    app.getContactHelper().fillContactForm(contact, "Boss", "TheBestBossCompany", "123115, USSR, Moscow, Tverskaya st, 1", false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnToHomePage();
    List<ContactNamesData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size() , after.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactNamesData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
