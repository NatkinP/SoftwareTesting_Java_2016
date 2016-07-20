package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by natkin on 20.07.2016.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
   this.wd = wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
