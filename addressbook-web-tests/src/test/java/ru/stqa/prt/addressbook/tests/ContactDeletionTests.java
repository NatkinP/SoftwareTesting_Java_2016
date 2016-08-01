package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by natkin on 22.07.2016.
 */
public class ContactDeletionTests  extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createOrEditContact(new ContactNamesData("Ivan", "Petrovich", "Zakipailo", "TeaPot", null), true);
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactNamesData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().pressContactDelete();
    app.getContactHelper().catchMessage();
    app.getNavigationHelper().gotoHomePage();
    List<ContactNamesData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(before.size() - 1);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));
  }
}
