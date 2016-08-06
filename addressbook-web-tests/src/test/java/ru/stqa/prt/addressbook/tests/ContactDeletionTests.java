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
public class ContactDeletionTests  extends TestBase {

  @BeforeMethod
  public void ansurePreonditios(){
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData()
              .withFirstname("Ivan")
              .withMiddlename("Zigmoondovich")
              .withLastname("Zakipailo")
              .withNickname("TeaPot")
              .withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test// (enabled = false)
  public void testContactDeletion(){
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(before.size() - 1, equalTo(app.contact().count()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
