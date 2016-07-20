package ru.stqa.prt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by natkin on 20.07.2016.
 */
public class SessionHelper {
  private FirefoxDriver wd;

  public SessionHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void login(String usermane, String password) {
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(usermane);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }
}
