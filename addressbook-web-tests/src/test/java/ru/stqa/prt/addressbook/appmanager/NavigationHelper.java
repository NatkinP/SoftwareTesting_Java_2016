package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by natkin on 20.07.2016.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

}
