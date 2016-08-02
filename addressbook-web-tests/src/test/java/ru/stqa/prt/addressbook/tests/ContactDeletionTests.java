package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactNamesData;

import java.util.HashSet;
import java.util.List;

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
    List<ContactNamesData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<ContactNamesData> after = app.contact().list();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(index);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));
  }

}
