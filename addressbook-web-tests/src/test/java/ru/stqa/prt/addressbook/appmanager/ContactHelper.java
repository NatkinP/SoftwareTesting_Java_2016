package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.prt.addressbook.model.ContactCompanyParam;
import ru.stqa.prt.addressbook.model.ContactNamesData;
import ru.stqa.prt.addressbook.model.ContactPhones;

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

  public void fillContactForm(ContactNamesData namesData, String title, String company, String address, boolean creation) {
    fillNamesData(namesData.getFirstname(), namesData.getMiddlename(), namesData.getLastname(), namesData.getNickname());
    fillCompanyParam(new ContactCompanyParam(title, company, address));
    fillPhones(new ContactPhones("8-495-111-222-333", "8-915-123-45-67", "01", "02"));

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(namesData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void fillPhones(ContactPhones contactPhones) {
    type(By.name("home"), contactPhones.getHome());
    type(By.name("mobile"), contactPhones.getMobile());
    type(By.name("work"), contactPhones.getWork());
    type(By.name("fax"), contactPhones.getFax());
  }

  private void fillCompanyParam(ContactCompanyParam contactCompanyParam) {
    type(By.name("title"), contactCompanyParam.getTitle());
    type(By.name("company"), contactCompanyParam.getCompany());
    type(By.name("address"), contactCompanyParam.getAddress());
  }

  private void fillNamesData(String firstname, String middlename, String lastname, String nickname) {
    type(By.name("firstname"), firstname);
    type(By.name("middlename"), middlename);
    type(By.name("lastname"), lastname);
    type(By.name("nickname"), nickname);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void editFirstContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void selectFirstContact() {
    WebElement selected = wd.findElement(By.name("selected[]"));
    if (!selected.isSelected()) {
      selected.click();
    }
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

  public void createOrEditContact(ContactNamesData contactNames, boolean isCreate) {
    initContactCreation();
    fillContactForm(contactNames, "Boss", "TheBestBossCompany", "123115, USSR, Moscow, Tverskaya st, 1", isCreate);
    submitContactCreation();
  }
}
