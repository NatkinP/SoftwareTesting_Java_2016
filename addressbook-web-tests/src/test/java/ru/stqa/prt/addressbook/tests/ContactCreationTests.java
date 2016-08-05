package ru.stqa.prt.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactData;
import ru.stqa.prt.addressbook.model.Contacts;
import ru.stqa.prt.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((o) -> new Object[]{o}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContacts")//(enabled = false)
  public void testContactCreation(ContactData contact) {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
//    ContactData contact = new ContactData()
//            .withFirstname("Ivan")
//            .withMiddlename("Zigmoondovich")
//            .withLastname("Zakipailo")
//            .withNickname("TeaPot")
//            .withPhoto(photo)
//            .withTitle("Boss")
//            .withCompany("TheBestBossCompany")
//            .withAddress("123115, USSR, Moscow, Tverskaya st, 1")
//            .withHome("8-495-111-222-333")
//            .withMobile("8-915-123-45-67")
//            .withWork("01")
//            .withFax("02")
//            .withEmail2("1-2-3-4@hhh.ig")
//            .withEmail3("llll@upriamstvo");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Ivan'")
            .withMiddlename("Zigmoondovich")
            .withLastname("Zakipailo")
            .withNickname("TeaPot")
            .withGroup("test1")
            .withTitle("Boss")
            .withCompany("TheBestBossCompany")
            .withAddress("123115, USSR, Moscow, Tverskaya st, 1")
            .withHome("8-495-111-222-333")
            .withMobile("8-915-123-45-67")
            .withWork("01")
            .withFax("02")
            .withEmail2("1-2-3-4@hhh.ig")
            .withEmail3("llll@upriamstvo");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    assertThat(before.size(), equalTo(app.contact().count()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
