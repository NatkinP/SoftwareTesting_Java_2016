package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.prt.addressbook.model.*;

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

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(namesData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void fillContactData(ContactData contact) {
    type(By.name("firstname"), contact.getFirstname());
    type(By.name("middlename"), contact.getMiddlename());
    type(By.name("lastname"), contact.getLastname());
    type(By.name("nickname"), contact.getNickname());
    type(By.name("title"), contact.getTitle());
    type(By.name("company"), contact.getCompany());
    type(By.name("address"), contact.getAddress());
    type(By.name("home"), contact.getHome());
    type(By.name("mobile"), contact.getMobile());
    type(By.name("work"), contact.getWork());
    type(By.name("fax"), contact.getFax());
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
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname);
      contactCache.add(contact);
    }
    return contactCache;
  }
}
