package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactNamesData("Ivan", "Petrovich", "Zakipailo", "TeaPot"), "Boss", "TheBestBossCompany", "123115, USSR, Moscow, Tverskaya st, 1");
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}
