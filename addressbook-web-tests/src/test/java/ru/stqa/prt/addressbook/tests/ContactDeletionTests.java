package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

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
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().pressContactDelete();
    app.getContactHelper().catchMessage();
    app.getNavigationHelper().gotoHomePage();
  }
}
