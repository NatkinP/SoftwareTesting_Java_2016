package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactData;
import ru.stqa.prt.addressbook.model.Contacts;

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
      app.contact().create(new ContactData()
                                            .withFirstname("Ivan")
                                           // .withMiddlename("Zigmoondovich")
                                            .withLastname("Zakipailo")
                                           // .withNickname("TeaPot")
                                           // .withGroup("test1")
                                            );
      app.goTo().homePage();
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    ContactData contact = new ContactData()
                                          .withId(modifiedContact.getId())
                                          .withFirstname("Ivan2")
                                         // .withMiddlename("Zigmoondovich2")
                                          .withLastname("Zakipailo2")
                                         // .withNickname("TeaPot")
            ;
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    assertThat(app.group().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
