package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

/**
 * Created by natkin on 22.07.2016.
 */
public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().editFirstContact();
    app.getContactHelper().fillContactForm(new ContactNamesData("Ivan2", "Petrovich2", "Zakipailo2", "TeaPot", null), "Boss", "TheBestBossCompany", "123115, USSR, Moscow, Tverskaya st, 1", false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnToHomePage();
  }
}
