package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.ContactPhones;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createOrEditContact(new ContactNamesData("Ivan", "Petrovich", "Zakipailo", "TeaPot", "test1"),true);
    app.getNavigationHelper().returnToHomePage();
  }

}
