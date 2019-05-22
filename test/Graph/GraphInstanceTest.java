/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package Graph;

import static org.junit.Assert.*;

import java.util.Collections;
import org.junit.Test;
import java.util.*;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    // TODO
    /*
     * vertices(): 图中顶点个数：0，1，>1
     * 
     * Add(): 输入的vertex：为空，在原图中已存在，原图中不存在
     * 
     * set(): weight输入值：0，非0 source到target的边：已存在，不存在 顶点source在图中：已存在，不存在
     * 顶点target在图中：已存在，不存在
     * 
     * remove(): 输入值vertex:为空，在原图中已存在，原图中不存在
     * 是否有邻接vertex的边：无，有以vertex为起点的边，有以vertex为终点的边
     * 
     * sources(): 图中是否存在它：是，否 图中指向它的边的个数：0，1，>1
     * 
     * targets(): 图中是否存在它：是，否 图中邻接它的边的个数：0，1，>1
     */

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph
    @Test
    public void testVertices() {
        Graph<String> g1 = emptyInstance();
        // similar to testAdd
        String a = "a";
        String b = "b";
        Set<String> answerset = new HashSet<>();

        assertEquals(Collections.emptySet(), g1.vertices());

        assertTrue(g1.add(b));
        answerset.add(b);
        assertEquals(answerset, g1.vertices());

        assertTrue(g1.add(a));
        answerset.add(a);
        assertEquals(answerset, g1.vertices());
    }

    @Test
    public void testAdd() {
        Graph<String> g1 = emptyInstance();
        // how to test null String?
        String a = "a";
        String b = "b";
        Set<String> answerset = new HashSet<>();
        assertTrue(g1.add(a));
        answerset.add(a);
        assertEquals(answerset, g1.vertices());

        assertTrue(g1.add(b));
        answerset.add(b);
        assertEquals(answerset, g1.vertices());

        assertFalse(g1.add(a));
        assertEquals(answerset, g1.vertices());
    }

    @Test
    public void testSet() {
        Graph<String> g = emptyInstance();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        // g.add(a);g.add(b);g.add(c);g.add(d);
        Set<String> vertexset = new HashSet<>();
        assertEquals(0, g.set(a, b, 0));
        assertEquals(vertexset, g.vertices());

        assertEquals(0, g.set(c, d, 1));
        vertexset.add(c);
        vertexset.add(d);
        assertEquals(vertexset, g.vertices());

        assertEquals(1, g.set(c, d, 2));
        assertEquals(vertexset, g.vertices());

        assertEquals(2, g.set(c, d, 0));
        assertEquals(vertexset, g.vertices());

        assertEquals(0, g.set(c, d, 0));

    }

    @Test
    public void testRemove() {
        Graph<String> g = emptyInstance();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        Set<String> vertexset = new HashSet<>();
        g.set(c, d, 1);
        g.set(c, a, 1);
        vertexset.add(a);
        vertexset.add(c);
        vertexset.add(d);
        assertFalse(g.remove(b));
        assertEquals(vertexset, g.vertices());

        assertTrue(g.remove(a));
        vertexset.remove(a);
        assertEquals(vertexset, g.vertices());

        assertTrue(g.remove(c));
        vertexset.remove(c);
        assertEquals(vertexset, g.vertices());

        assertTrue(g.remove(d));
        vertexset.remove(d);
        assertEquals(vertexset, g.vertices());

    }

    @Test
    public void testSources() {
        Graph<String> g = emptyInstance();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        Map<String, Integer> answer = new TreeMap<>();
        g.add(a);
        g.add(b);
        g.add(c);
        g.set(d, e, 1);

        assertEquals(answer, g.sources(a));

        g.set(b, a, 1);
        answer.put(b, 1);
        assertEquals(answer, g.sources(a));

        g.set(c, a, 2);
        answer.put(c, 2);
        g.add(d);
        assertEquals(answer, g.sources(a));
    }

    @Test
    public void testTargets() {
        Graph<String> g = emptyInstance();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        Map<String, Integer> answer = new TreeMap<>();
        g.add(a);
        g.add(b);
        g.add(c);
        g.set(d, e, 1);

        assertEquals(answer, g.targets(a));

        g.set(a, b, 1);
        answer.put(b, 1);
        assertEquals(answer, g.targets(a));

        g.set(a, c, 2);
        answer.put(c, 2);
        g.add(d);
        assertEquals(answer, g.targets(a));
    }
}
