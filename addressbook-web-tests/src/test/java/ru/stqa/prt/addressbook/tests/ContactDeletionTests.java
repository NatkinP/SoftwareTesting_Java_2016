package ru.stqa.prt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.Contacts;
import ru.stqa.prt.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      app.contact().createOrEditContact(new ContactNamesData()
              .withFirstname("Ivan")
              .withMiddlename("Zigmoondovich")
              .withLastname("Zakipailo")
              .withNickname("TeaPot")
              .withGroup("test1"), true);
      app.goTo().homePage();
    }
  }

  @Test// (enabled = false)
  public void testContactDeletion(){
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactNamesData deletedContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(before.size() - 1, after.size());
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
