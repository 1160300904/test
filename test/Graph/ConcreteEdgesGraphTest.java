/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package Graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    // Testing strategy for ConcreteEdgesGraph.toString()
    // TODO
    /*
     * graph是否为空：是，否
     */

    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testConcreteEdgesGraphToString() {
        Graph<String> g1 = emptyInstance();
        String answer = "The graph is empty.";
        assertEquals(answer, g1.toString());

        String answer1 = "The vertecies are: [1] [2] [3] [4] \n"
                + "The edges are: [1,10,2] [1,20,3] [3,7,2] [2,60,4] \n";

        g1.add("1");
        g1.add("2");
        g1.add("3");
        g1.add("4");
        g1.set("1", "2", 10);
        g1.set("1", "3", 20);
        g1.set("3", "2", 7);
        g1.set("2", "4", 60);
        assertEquals(answer1, g1.toString());

    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // TODO
    /*
     * source和target是否相同：是，否
     */

    // TODO tests for operations of Edge
    @Test
    public void testGetsourceAndGettarget() {
        Edge<String> e = new Edge<>("source", 3, "target");
        assertEquals("source", e.getSource());
        assertEquals("target", e.getTarget());
        assertEquals(3, e.getWeight());

        Edge<String> e1 = new Edge<>("source", 3, "source");
        assertEquals("source", e1.getSource());
        assertEquals("source", e1.getTarget());
        assertEquals(3, e1.getWeight());
    }

    @Test
    public void testToString() {
        Edge<String> e1 = new Edge<>("source", 3, "target");
        String answer = "This edge is from source to target with weight of 3";
        assertEquals(answer, e1.toString());
    }
}
