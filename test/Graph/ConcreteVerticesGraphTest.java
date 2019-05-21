/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package Graph;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    /*
     * graph是否为空： 是，否
     */
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testConcreteVerticesGraphToString() {
    	Graph<String> g1=emptyInstance();
    	String answer="The graph is empty.";
    	assertEquals(answer,g1.toString());
    	
    	String answer1="Vertex: 1  Outward-edges: [10,2] [20,3] \n" + 
    			"Vertex: 2  Outward-edges: [60,4] \n" + 
    			"Vertex: 3  Outward-edges: [7,2] \n" + 
    			"Vertex: 4  Outward-edges: \n" ;
    	g1.add("1");g1.add("2");g1.add("3");g1.add("4");
    	g1.set("1", "2", 10);g1.set("1", "3", 20);g1.set("3", "2", 7);
    	g1.set("2", "4", 60);
    	assertEquals(answer1,g1.toString());
    	
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    /*
     * L:								不同种类泛型
     * add()和remove()中输入是否为null：  是，否
     * target域是否为空：				 是，否
     * source是否为null：				 是，否
     */
    // TODO tests for operations of Vertex
    @Test
    public void testgetSource() {
    	Vertex<String> v1=new Vertex<>("1");
    	assertEquals("1",v1.getSource());
    	
    	Double d1=new Double(1.1);
    	Vertex<Double> v2=new Vertex<>(d1);
    	assertEquals(d1,v2.getSource());
    	
    }
    @Test
    public void testGetTargetsAndGetIterator() {
    	Vertex<String> v1=new Vertex<>("0");
    	TarAndWei<String> taw1=new TarAndWei<>(1,"1");
    	TarAndWei<String> taw2=new TarAndWei<>(2,"2");
    	TarAndWei<String> taw3=new TarAndWei<>(3,"3");
    	TarAndWei<String> taw4=new TarAndWei<>(4,"4");
    	v1.addTarAndWei(taw1);v1.addTarAndWei(taw2);v1.addTarAndWei(taw3);v1.addTarAndWei(taw4);
    	
    	Iterator<TarAndWei<String>> i1=v1.getIterator();
    	List<TarAndWei<String>> l1=v1.getTargets();int index=0;
    	while(i1.hasNext()) {
    		assertEquals(l1.get(index++),i1.next());
    	}
    	
    	Vertex<String> v2=new Vertex<>("0");
    	assertTrue(v2.getTargets().isEmpty());
    }
    @Test
    public void testAddAndRemove() {
    	
    	TarAndWei<String> taw1=new TarAndWei<>(1,"1");
    	Vertex<String> v1=new Vertex<>("0",taw1);
    	TarAndWei<String> taw2=new TarAndWei<>(2,"2");
    	TarAndWei<String> taw3=new TarAndWei<>(3,"3");
    	TarAndWei<String> taw4=new TarAndWei<>(4,"4");

    	v1.addTarAndWei(taw2);v1.addTarAndWei(taw3);v1.addTarAndWei(taw4);
    	List<TarAndWei<String>> l1=v1.getTargets();
    	assertTrue(taw1.getTarget().equals(l1.get(0).getTarget()));
    	assertEquals(taw1.getWeight(),l1.get(0).getWeight());
    	assertTrue(taw2.getTarget().equals(l1.get(1).getTarget()));
    	assertEquals(taw2.getWeight(),l1.get(1).getWeight());
    	assertTrue(taw3.getTarget().equals(l1.get(2).getTarget()));
    	assertEquals(taw3.getWeight(),l1.get(2).getWeight());
    	assertTrue(taw4.getTarget().equals(l1.get(3).getTarget()));
    	assertEquals(taw4.getWeight(),l1.get(3).getWeight());
    	
    	
    	
    	v1.removeTarAndWei(taw1);v1.removeTarAndWei(taw2);v1.removeTarAndWei(taw3);
    	assertTrue(taw4.getTarget().equals(l1.get(3).getTarget()));
    	assertEquals(taw4.getWeight(),l1.get(3).getWeight());
    	
    	assertFalse(v1.addTarAndWei(null));
    	assertFalse(v1.removeTarAndWei(null));
    }
    @Test
    public void testToString() {
    	Vertex<String> v1=new Vertex<>("0");
    	TarAndWei<String> taw1=new TarAndWei<>(1,"1");
    	TarAndWei<String> taw2=new TarAndWei<>(2,"2");
    	TarAndWei<String> taw3=new TarAndWei<>(3,"3");
    	TarAndWei<String> taw4=new TarAndWei<>(4,"4");
    	v1.addTarAndWei(taw1);v1.addTarAndWei(taw2);v1.addTarAndWei(taw3);v1.addTarAndWei(taw4);
    	
    	String answer="Vertex: 0  Outward-edges: [1,1] [2,2] [3,3] [4,4] \n" ;
    	assertEquals(answer,v1.toString());
    	
    }
}
