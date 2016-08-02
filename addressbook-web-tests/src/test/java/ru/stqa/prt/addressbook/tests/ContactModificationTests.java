package ru.stqa.prt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactNamesData modifiedContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    ContactNamesData contact = new ContactNamesData()
                                          .withId(modifiedContact.getId())
                                          .withFirstname("Ivan2")
                                          .withMiddlename("Zigmoondovich2")
                                          .withLastname("Zakipailo2")
                                          .withNickname("TeaPot");
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertEquals(before.size() , after.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
