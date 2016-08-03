package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by natkin on 03.08.2016.
 */
public class ContactInfoTest extends TestBase {

  @Test(enabled = false)
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @Test//(enabled = false)
  public void testContactDetail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String mergedContactDetails = mergeContactDetail(contactInfoFromEditForm);
    String detailFormInfo = app.contact().infoFromDetailForm(contact);

    System.out.println(mergedContactDetails);
    System.out.println("-------------------------");
    System.out.println(detailFormInfo);

    assertThat(detailFormInfo.startsWith(mergedContactDetails), equalTo(true));


  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));

  }

  private String mergeContactDetail(ContactData contact) {
    String megaStr = "";

    if (contact.getFirstname().length() > 0) {
      megaStr = megaStr + contact.getFirstname();
    }

    if (contact.getLastname().length() > 0) {
      if (contact.getFirstname().length() > 0) {
        megaStr = megaStr + " " + contact.getLastname();
      } else {
        megaStr = megaStr + contact.getLastname();
      }
    }

    megaStr = megaStr + "\n";

    if (contact.getAddress().length() > 0) {
      megaStr = megaStr + contact.getAddress() + "\n";
      megaStr = megaStr + "\n";
    }

    if (contact.getHome().length() > 0) {
      megaStr = megaStr + "H: " + contact.getHome() + "\n";
    }

    if (contact.getMobile().length() > 0) {
      megaStr = megaStr + "M: " + contact.getMobile() + "\n";
    }

    if (contact.getWork().length() > 0) {
      megaStr = megaStr + "W: " + contact.getWork() + "\n";
    }

    if (contact.getHome().length() != 0 || contact.getWork().length() != 0 || contact.getMobile().length() != 0) {
      megaStr = megaStr + "\n";
    }


    if (contact.getEmail().length() > 0) {
      megaStr = megaStr + contact.getEmail();
      megaStr = formatEmail(contact.getEmail(), megaStr);
    }

    if (contact.getEmail2().length() > 0) {
      megaStr = megaStr + contact.getEmail2();
      megaStr = formatEmail(contact.getEmail2(), megaStr);
    }

    if (contact.getEmail3().length() > 0) {
      megaStr = megaStr + contact.getEmail3();
      megaStr = formatEmail(contact.getEmail3(), megaStr);
    }

    return megaStr;
  }

  private String formatEmail(String email, String megaStr) {
    if (rowTale(email, "@").length() > 0) {
      megaStr = megaStr + " (www." + rowTale(email, "@") + ")";
    } else if (email.length() > 1) {
      megaStr = megaStr + " (www." + email.substring(1) + ")";
    }
    megaStr = megaStr + "\n";
    return megaStr;
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String rowTale(String str, String subStr) {
    if (str.indexOf(subStr) > -1) {
      return str.substring(str.indexOf(subStr) + subStr.length());
    } else {
      return "";
    }
  }

}
