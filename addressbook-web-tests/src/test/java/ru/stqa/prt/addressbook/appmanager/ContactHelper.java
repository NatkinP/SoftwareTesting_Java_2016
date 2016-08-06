package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.prt.addressbook.model.ContactData;
import ru.stqa.prt.addressbook.model.Contacts;

import java.io.File;
import java.util.List;

/**
 * Created by natkin on 20.07.2016.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData namesData, boolean creation) {
    fillContactData(namesData);

    if (creation) {
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(namesData.getGroup());
      Assert.assertTrue(isElementPresent(By.name("new_group")));
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void fillContactData(ContactData contact) {
    //File photo = new File("src/test/resources/stru.png");
    type(By.name("firstname"), contact.getFirstname());
 //   type(By.name("middlename"), contact.getMiddlename());
    type(By.name("lastname"), contact.getLastname());
 //   type(By.name("nickname"), contact.getNickname());
 //   type(By.name("title"), contact.getTitle());
 //   type(By.name("company"), contact.getCompany());
    type(By.name("address"), contact.getAddress());
    type(By.name("home"), contact.getHome());
    type(By.name("mobile"), contact.getMobile());
    type(By.name("work"), contact.getWork());
  //  type(By.name("fax"), contact.getFax());
  //  type(By.name("email"), contact.getEmail());
    type(By.name("email2"), contact.getEmail2());
    type(By.name("email3"), contact.getEmail3());
  //  attach(By.name("photo"), contact.getPhoto());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void edit(int index) {
    wd.findElement(By.cssSelector("td.center > a[href='edit.php?id=" + index + "']")).click();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("Input[id='" + id + "']")).click();
  }

  public void pressContactDelete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void catchMessage() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contactNames) {
    initContactCreation();
    fillContactForm(contactNames, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    edit(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    pressContactDelete();
    catchMessage();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails);
      contactCache.add(contact);
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstname(firstname)
            .withLastname(lastname)
            .withHome(home)
            .withMobile(mobile)
            .withWork(work)
            .withAddress(address)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public String infoFromDetailForm(ContactData contact) {
    initContactDetailById(contact.getId());
    String contactDetails = wd.findElement(By.xpath("//div/div[4]")).getText();
    wd.navigate().back();
    return contactDetails;
  }

  private void initContactDetailById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }

  public void addContactToGroup(Integer contact_id, String groupName) {
    selectContactById(contact_id);
    chooseGroupForAdd(groupName);
    wd.findElement(By.name("add")).click();
  }

  private void chooseGroupForAdd(String groupName) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
  }

  private void chooseGroupForFiltr(String groupName) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
  }

  public void delContactFromGroup(Integer contact_id, String groupName) {
    chooseGroupForFiltr(groupName);
    selectContactById(contact_id);
    wd.findElement(By.name("remove")).click();
  }
}
