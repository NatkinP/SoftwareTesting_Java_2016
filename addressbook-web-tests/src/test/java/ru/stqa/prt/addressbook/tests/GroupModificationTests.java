package ru.stqa.prt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by natkin on 21.07.2016.
 */
public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ansurePreonditios(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification(){
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); // Возвращает первый попавшийся элемент множетсва
    GroupData group = new GroupData()
                          .withId(modifiedGroup.getId())
                          .withName("test1")
                          .withHeader("test2")
                          .withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }



}
