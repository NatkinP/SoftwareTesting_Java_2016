package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by natkin on 22.07.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ansurePreonditios(){
    if (!app.contact().isThereAContact()) {
      app.contact().createOrEditContact(new ContactNamesData()
                                            .withFirstname("Ivan")
                                            .withMiddlename("Zigmoondovich")
                                            .withLastname("Zakipailo")
                                            .withNickname("TeaPot")
                                            .withGroup("test1"), true);
      app.goTo().homePage();
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    Set<ContactNamesData> before = app.contact().all();
    ContactNamesData modifyContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    ContactNamesData contact = new ContactNamesData()
                                          .withId(modifyContact.getId())
                                          .withFirstname("Ivan2")
                                          .withMiddlename("Zigmoondovich2")
                                          .withLastname("Zakipailo2")
                                          .withNickname("TeaPot");
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    Set<ContactNamesData> after = app.contact().all();
    Assert.assertEquals(before.size() , after.size());

    before.remove(modifyContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
