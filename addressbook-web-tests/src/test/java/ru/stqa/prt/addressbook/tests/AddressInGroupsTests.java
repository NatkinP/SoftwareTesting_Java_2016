package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.prt.addressbook.model.*;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by natkin on 06.08.2016.
 */
public class AddressInGroupsTests extends TestBase {

  @BeforeSuite//(enabled = false)
  public void ansurePreonditios() {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test100500"));

      app.goTo().homePage();
      app.contact().create(new ContactData()
                      .withFirstname("Ivan100500")
                      .withLastname("Zakipailo100500")
      );
      app.goTo().homePage();
  }

  @Test //(enabled = false)
  public void testAddContactToGroup() {
    app.goTo().homePage();
    Integer contact_id = app.findMaxId("addressbook","id");
    Integer group_id = app.findMaxId("group_list","group_id");
    AddressInGroupsData addedAddressInGroups = new AddressInGroupsData().withId(contact_id).withGroup_id(group_id);
    AddressInGroups before = app.db().addressInGroups();
    app.contact().addContactToGroup(contact_id, "test100500");
    AddressInGroups after = app.db().addressInGroups();
    assertThat(after, equalTo(before.withAdded(addedAddressInGroups)));
  }

  @Test //(enabled = false)
  public void testDelContactFromGroup() {
    app.goTo().homePage();
    Integer contact_id = app.findMaxId("addressbook","id");
    Integer group_id = app.findMaxId("group_list","group_id");
    AddressInGroupsData addedAddressInGroups = new AddressInGroupsData().withId(contact_id).withGroup_id(group_id);
    AddressInGroups before = app.db().addressInGroups();
    app.contact().delContactFromGroup(contact_id, "test100500");
    AddressInGroups after = app.db().addressInGroups();
    assertThat(after, equalTo(before.without(addedAddressInGroups)));
  }


  @AfterSuite//(enabled = false)
  public void ansurePostconditios() {
    app.goTo().groupPage();
    app.group().delete(new GroupData().withId(app.findMaxId("group_list","group_id")).withName("test100500"));

    app.goTo().homePage();
    app.contact().delete(new ContactData().withId(app.findMaxId("addressbook","id"))
            .withFirstname("Ivan100500")
            .withLastname("Zakipailo100500")
    );
    app.goTo().homePage();
  }
}
