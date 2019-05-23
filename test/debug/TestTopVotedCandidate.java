package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTopVotedCandidate {

  @Test
  public void testvote() {
    int[] persons = {0, 1, 1, 0, 0, 1, 0};
    int[] times = {0, 5, 10, 15, 20, 25, 30};
    TopVotedCandidate t = new TopVotedCandidate(persons, times);
    assertEquals(0, t.query(3), 0.01);
    assertEquals(1, t.query(12), 0.01);
    assertEquals(1, t.query(25), 0.01);
    assertEquals(0, t.query(15), 0.01);
    assertEquals(0, t.query(24), 0.01);
    assertEquals(1, t.query(8), 0.01);

    System.out.println(t.query(3));
    System.out.println(t.query(12));
    System.out.println(t.query(25));
    System.out.println(t.query(15));
    System.out.println(t.query(24));
    System.out.println(t.query(8));

  }
}
