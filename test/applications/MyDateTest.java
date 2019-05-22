package applications;

import static org.junit.Assert.*;
import org.junit.Test;

import physicalObject.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MyDateTest {

  /*
   * Testing Strategy: 1.与加有关的的函数： 1)输入：0，>0; 2)输入的时间：是否比最大值大，即，是否需要进位。 2.与阶段有关的函数：
   * 1）被截断的时间在指定条件下还是否需要被截断，例，若时间为1999:01:01 13:00:00 而要求截断到分钟。 3.observer方法： 1）获取的时刻类型的时间值：0, 非0.
   * 4.getmin方法： 1）哪个时间点最靠前：第一个，第二个，第三个 2）三个输入时间相同的个数：一个，两个，三个 5.TruncBaseOnSplit： 1）截断的策略：
   * Hour|Day|Week|Month 2）是否以及满足截断的要求了：方法调用之后，返回的MyDate对象和原来一样。 6.addBaseOnSplit: 1）增加的策略：
   * Hour|Day|Week|Month 2）原日期在增加后是否需要进位 3）输入的策略是否不在 Hour|Day|Week|Month 中。 7.equals: 1）两者相等，不相等
   * 2）输入的两个对象是否为同一个对象 3）两个对象是否为同一个类型。
   */

  @Test
  public void testEqualsAndToString() {
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d2 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d3 = new MyDate(1, 2, 4, 4, 5, 6);
    String s1 = "sds";
    assertFalse(d1.equals(s1));
    assertTrue(d1.equals(d2));
    assertTrue(d1.equals(d1));
    assertFalse(d1.equals(d3));

    assertEquals("0001-02-03T04:05:06", d1.toString());
  }

  @Test
  public void testAddBaseOnSplit() {
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 5, 5, 6)), MyDate.addBaseOnSplit("Hour", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 4, 4, 5, 6)), MyDate.addBaseOnSplit("Day", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 10, 4, 5, 6)),
        MyDate.addBaseOnSplit("Week", d1));
    assertEquals(new MyDate(LocalDateTime.of(2, 1, 3, 5, 5, 6)),
        MyDate.addBaseOnSplit("Month", new MyDate(LocalDateTime.of(1, 12, 3, 5, 5, 6))));
    assertNull(MyDate.addBaseOnSplit("Second", d1));
  }

  @Test
  public void testTruncBaseOnSplit() {
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 0, 0)),
        MyDate.truncBaseOnSplit("Hour", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0)),
        MyDate.truncBaseOnSplit("Month", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0)),
        MyDate.truncBaseOnSplit("Day", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0)),
        MyDate.truncBaseOnSplit("Week", d1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0)),
        MyDate.truncBaseOnSplit("Week", new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0))));
    assertNull(MyDate.truncBaseOnSplit("Second", d1));
  }

  @Test
  public void testAddAndTrunc() {
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d2 = new MyDate(2, 2, 3, 4, 5, 6);
    MyDate d3 = new MyDate(1, 3, 3, 4, 5, 6);
    MyDate d4 = new MyDate(1, 2, 4, 4, 5, 6);
    MyDate d5 = new MyDate(1, 2, 3, 5, 5, 6);
    MyDate d6 = new MyDate(1, 2, 3, 4, 6, 6);
    MyDate d7 = new MyDate(1, 2, 3, 4, 5, 7);
    MyDate d8 = new MyDate(LocalDateTime.of(1, 2, 3, 4, 5, 6));

    assertEquals(new MyDate(LocalDateTime.of(2, 2, 3, 4, 5, 6)), d1.addYear(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 3, 3, 4, 5, 6)), d1.addMonth(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 4, 4, 5, 6)), d1.addDay(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 5, 5, 6)), d1.addHour(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 6, 6)), d1.addMinute(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 5, 7)), d1.addSecond(1));
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 6, 6)), d1.addSecond(60));

    assertEquals(d1, d1.addDay(0));
    assertEquals(d1, d1.addHour(0));
    assertEquals(d1, d1.addMinute(0));
    assertEquals(d1, d1.addMonth(0));
    assertEquals(d1, d1.addSecond(0));
    assertEquals(d1, d1.addYear(0));

    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 5, 6)), d1.truncSecond());
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 5, 0)), d1.truncMinute());
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 4, 0, 0)), d1.truncHour());
    assertEquals(new MyDate(LocalDateTime.of(1, 2, 3, 0, 0, 0)), d1.truncDay());

  }

  @Test
  public void testGetter() {
    MyDate d1 = new MyDate(1, 1, 1, 1, 1, 1);
    assertEquals(1, d1.getDay());
    assertEquals(1, d1.getHour());
    assertEquals(1, d1.getMinute());
    assertEquals(1, d1.getMonth());
    assertEquals(1, d1.getSecond());
    assertEquals(1, d1.getYear());
  }

  @Test
  public void testGetMin() {
    // d1<d2
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d2 = new MyDate(2, 2, 3, 4, 5, 6);
    MyDate d3 = new MyDate(3, 3, 3, 4, 5, 6);
    MyDate d4 = new MyDate(1, 2, 4, 4, 5, 6);
    // d5==d6
    MyDate d5 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d6 = new MyDate(1, 2, 3, 4, 5, 6);

    assertEquals(1, MyDate.getMin(d1, d2, d3));
    assertEquals(2, MyDate.getMin(d2, d1, d3));
    assertEquals(3, MyDate.getMin(d3, d2, d1));
    assertEquals(3, MyDate.getMin(d2, d3, d1));
    assertEquals(1, MyDate.getMin(d1, d3, d3));
  }

  @Test
  public void testCompare() {
    MyDate d1 = new MyDate(1, 2, 3, 4, 5, 6);
    MyDate d2 = new MyDate(2, 2, 3, 4, 5, 6);
    MyDate d3 = new MyDate(1, 3, 3, 4, 5, 6);
    MyDate d4 = new MyDate(1, 2, 4, 4, 5, 6);
    MyDate d5 = new MyDate(1, 2, 3, 5, 5, 6);
    MyDate d6 = new MyDate(1, 2, 3, 4, 6, 6);
    MyDate d7 = new MyDate(1, 2, 3, 4, 5, 7);
    MyDate d8 = new MyDate(LocalDateTime.of(1, 2, 3, 4, 5, 6));

    assertTrue(d1.compareTo(d2) < 0);
    assertTrue(d1.compareTo(d3) < 0);
    assertTrue(d1.compareTo(d4) < 0);
    assertTrue(d1.compareTo(d5) < 0);
    assertTrue(d1.compareTo(d6) < 0);
    assertTrue(d1.compareTo(d7) < 0);
    assertTrue(d1.compareTo(d8) == 0);
    assertTrue(d1.compareTo(d1) == 0);
  }

}
