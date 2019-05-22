/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved. Redistribution of original
 * or derived work requires permission of course staff.
 */
package Graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.*;
import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are tested in
 * GraphInstanceTest.
 */
public class GraphStaticTest {

  // Testing strategy
  // empty()
  // no inputs, only output is empty graph
  // observe with vertices()
  /*
   * testDifferetnTypes: use different types to test Graph
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }

  @Test
  public void testEmptyVerticesEmpty() {
    assertEquals("expected empty() graph to have no vertices", Collections.emptySet(),
        Graph.empty().vertices());
  }

  // TODO test other vertex label types in Problem 3.2
  @Test
  public void testDifferetnTypes() {
    Graph<Integer> g1 = Graph.empty();
    Integer one = 1;
    Integer two = 2;
    Integer three = 3;
    g1.add(one);
    g1.add(two);
    g1.add(three);
    g1.set(one, two, 1);
    g1.set(three, two, 3);
    Map<Integer, Integer> set1 = new HashMap<>();
    set1.put(1, 1);
    set1.put(3, 3);
    assertEquals(set1, g1.sources(two));

    Graph<Double> g2 = Graph.empty();
    Double done = 1.1;
    Double dtwo = 2.2;
    Double dthree = 3.3;
    g2.set(done, dtwo, 1);
    assertEquals(0, g2.set(dthree, dtwo, 4));
    Map<Double, Integer> set2 = new HashMap<>();
    set2.put(dtwo, 1);
    assertEquals(set2, g2.targets(done));

  }

}
