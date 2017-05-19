package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceTo() {
    Point p1 = new Point(0,5);
    Point p2 = new Point(5,5);
    Assert.assertEquals(p1.distanceTo(p2), 5.0);
  }
}
