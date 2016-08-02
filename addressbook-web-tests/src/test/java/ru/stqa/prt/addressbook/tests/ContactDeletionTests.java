package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Set<ContactNamesData> before = app.contact().all();
    ContactNamesData deletedContact = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Set<ContactNamesData> after = app.contact().all();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(deletedContact);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));
  }

}
