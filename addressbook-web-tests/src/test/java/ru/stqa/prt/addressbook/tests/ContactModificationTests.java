package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactNamesData> before = app.contact().list();
    int index = before.size() - 1;
    ContactNamesData contact = new ContactNamesData()
                                          .withId(before.get(before.size() - 1).getId())
                                          .withFirstname("Ivan2")
                                          .withMiddlename("Zigmoondovich2")
                                          .withLastname("Zakipailo2")
                                          .withNickname("TeaPot");
    app.contact().modify(before, contact);
    app.goTo().returnToHomePage();
    List<ContactNamesData> after = app.contact().list();
    Assert.assertEquals(before.size() , after.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactNamesData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
