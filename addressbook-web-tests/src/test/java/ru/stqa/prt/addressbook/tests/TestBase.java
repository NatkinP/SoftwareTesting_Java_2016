package ru.stqa.prt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.prt.addressbook.appmanager.ApplicationManager;

/**
 * Created by natkin on 20.07.2016.
 */
public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
