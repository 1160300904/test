package track;

import static org.junit.Assert.*;
import org.junit.Test;

public class TrackAndFactoryTest {

  /*
   * Testing strategy 1.工厂方法中的输入是否为负数 2.轨道半径为整数或浮点数
   */
  @Test
  public void testTrackAndFactory() {
    TrackFactory tf1 = new CircleTrackFactory();
    assertNull(tf1.getInstance(-1));
    Track t11 = tf1.getInstance(1.0);
    Track t12 = tf1.getInstance(1.0);
    Track t2 = tf1.getInstance(13.0);
    Track t3 = tf1.getInstance(14.0);

    assertEquals(1.0, t11.getRadius(), 0.0001);
    assertEquals(13.0, t2.getRadius(), 0.0001);
    assertEquals(14.0, t3.getRadius(), 0.0001);

    assertTrue(t11.equals(t11));
    assertFalse(t11.equals(t12));
    assertFalse(t2.equals(t12));
    assertFalse(t3.equals(t2));
  }
}
