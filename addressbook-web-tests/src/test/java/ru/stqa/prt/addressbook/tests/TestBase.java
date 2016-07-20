package ru.stqa.prt.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.prt.addressbook.appmanager.ApplicationManager;

/**
 * Created by natkin on 20.07.2016.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
