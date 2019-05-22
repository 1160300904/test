package circularOrbit;

import static org.junit.Assert.*;
import org.junit.Test;

import centralObject.*;
import physicalObject.*;
import track.*;
import java.util.*;

public class OrbitIteratorTest {

  @Test
  public void testIterator() {
    CircularOrbit<User, PersonalApp> o = CircularOrbit.empty();
    User u = UserFactory.getInstance("a");
    PersonalApp p1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
    PersonalApp p2 = PersonalAppFactory.getInstance("2", "1", "1", "1", "1");
    PersonalApp p3 = PersonalAppFactory.getInstance("3", "1", "1", "1", "1");
    PersonalApp p4 = PersonalAppFactory.getInstance("4", "1", "1", "1", "1");
    PersonalApp p5 = PersonalAppFactory.getInstance("5", "1", "1", "1", "1");
    PersonalApp p6 = PersonalAppFactory.getInstance("6", "1", "1", "1", "1");

    for (int i = 1; i <= 3; i++) {
      o.addInsideTrack(i);
    }

    o.addPhyToTrack(p1, 0, 1);
    o.addPhyToTrack(p2, 20, 1);
    o.addPhyToTrack(p3, 0, 2);
    o.addPhyToTrack(p4, 20, 2);
    o.addPhyToTrack(p5, 0, 3);
    o.addPhyToTrack(p6, 20, 3);

    OrbitIterator<User, PersonalApp> oi = o.iterator();
    assertTrue(oi.hasNext());
    PersonalApp p = oi.next();
    assertTrue(p.equals(p1) || p.equals(p2));
    p = oi.next();
    assertTrue(p.equals(p1) || p.equals(p2));
    p = oi.next();
    assertTrue(p.equals(p3) || p.equals(p4));
    p = oi.next();
    assertTrue(p.equals(p3) || p.equals(p4));
    p = oi.next();
    assertTrue(p.equals(p5) || p.equals(p6));
    p = oi.next();
    assertTrue(p.equals(p5) || p.equals(p6));

  }
}
