package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.ContactPhones;

/**
 * Created by natkin on 03.08.2016.
 */
public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactNamesData contact = app.contact().all().iterator().next();

  }
}
