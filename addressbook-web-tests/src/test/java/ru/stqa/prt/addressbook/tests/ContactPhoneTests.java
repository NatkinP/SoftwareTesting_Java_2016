package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactData;

/**
 * Created by natkin on 03.08.2016.
 */
public class ContactPhoneTests extends TestBase{

  @Test(enabled = false)
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();

  }
}
