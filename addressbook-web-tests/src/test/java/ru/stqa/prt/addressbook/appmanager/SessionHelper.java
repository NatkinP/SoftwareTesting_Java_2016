package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by natkin on 20.07.2016.
 */
public class SessionHelper extends HelperBase{

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void login(String usermane, String password) {
    type(By.name("user"), usermane);
    type(By.name("pass"), password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
