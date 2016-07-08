package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by natkin on 08.07.2016.
 */
public class PointTest {

  @Test
  public void testPoint1(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 1);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertNotEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
    Assert.assertNotEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
  }

  @Test
  public void testPoint2(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertNotEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
    Assert.assertNotEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
  }

  @Test
  public void testPoint3(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(-3.14, 1589263186.5);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertNotEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
    Assert.assertNotEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
  }

  @Test
  public void testPoint4(){
    Point p1 = new Point(89.0, -13);
    Point p2 = new Point(89.0, -13);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)));
    Assert.assertNotEquals(p1.distance(p2),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
    Assert.assertNotEquals(p2.distance(p1),Math.sqrt(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2)) + 1);
  }

}
