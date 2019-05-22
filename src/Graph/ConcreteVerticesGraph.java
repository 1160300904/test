/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved. Redistribution of original
 * or derived work requires permission of course staff.
 */
package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

  private final List<Vertex<L>> vertices = new ArrayList<>();

  /*
   * Abstraction function: each element in the 'vertices' list represents a source vertex and its
   * outward edges
   */
  /*
   * Representation invariant: each element in vertices shouldn't be null. the 'source' in each
   * elements in vertices should be different.
   */
  /*
   * Safety from rep exposure: 1. all fields are private. 2. all parameters in all functions except
   * vertices() are of immutable type. 3. as for vertices(), it returns a new HashSet to client. 4.
   * as for source() and targets(), they returns a new HashMap to client. 5. other functions returns
   * immutable types, such as int or boolean.
   */

  // TODO constructor
  public ConcreteVerticesGraph() {

  }

  // TODO checkRep
  /**
   * check if the rep invariant is true
   */
  private void checkRep() {
    for (int i = 0; i < vertices.size(); i++) {
      Vertex<L> vi = vertices.get(i);
      assert vi != null;
      for (int j = 0; j < vertices.size(); j++) {
        Vertex<L> vj = vertices.get(j);
        if (vi != vj) {
          assert vi.equals(vj) == false;
        }
      }
    }
  }

  @Override
  public boolean add(L vertex) {
    for (Vertex<L> v : vertices) {
      if (v.getSource().equals(vertex)) return false;
    }
    vertices.add(new Vertex<L>(vertex));
    // checkRep();
    return true;
  }

  @Override
  public int set(L source, L target, int weight) {
    Vertex<L> existedv = null;
    TarAndWei<L> existedt = null;
    int oldweight;
    for (Vertex<L> v : vertices) {
      if (v.getSource().equals(source)) existedv = v;
    }
    if (existedv != null) {
      for (TarAndWei<L> taw : existedv.getTargets()) {
        if (taw.getTarget().equals(target)) existedt = taw;
      }
    }
    if (weight != 0) {
      if (existedv != null) {
        if (existedt != null) {
          oldweight = existedt.getWeight();
          existedt.setWeight(weight);
          // checkRep();
          return oldweight;
        } else {
          existedv.addTarAndWei(new TarAndWei<L>(weight, target));

          this.add(target);
          // vertices.add(new Vertex<L>(target));
          // checkRep();
          return 0;
        }
      } else {
        vertices.add(new Vertex<L>(source, new TarAndWei<L>(weight, target)));
        this.add(target);
        // this.add(target);
        // vertices.add(new Vertex<L>(target));
        // checkRep();
        return 0;
      }

      /*
       * if(existedv!=null&&existedt!=null) { oldweight=existedt.getWeight();
       * existedt.setWeight(weight);return oldweight; } if(existedv!=null&&existedt==null) {
       * existedv.getTargets().add(new TarAndWei<L>(weight,target)); vertices.add(new
       * Vertex<L>(target)); return 0; }else { vertices.add(new Vertex<L>(source,new
       * TarAndWei<L>(weight,target))); vertices.add(new Vertex<L>(target)); return 0; }
       */
    } else {
      if (existedv != null && existedt != null) {
        oldweight = existedt.getWeight();
        existedv.removeTarAndWei(existedt);
        // checkRep();
        return oldweight;
      } else {
        // checkRep();
        return 0;
      }
    }
  }

  @Override
  public boolean remove(L vertex) {
    Vertex<L> ver = null;
    Iterator<TarAndWei<L>> tawiter = null;
    for (Vertex<L> v : vertices) {
      if (v.getSource().equals(vertex)) ver = v;
    }
    if (ver == null) return false;
    vertices.remove(ver);
    for (Vertex<L> v : vertices) {
      tawiter = v.getIterator();
      while (tawiter.hasNext()) {
        TarAndWei<L> taw = tawiter.next();
        if (taw.getTarget().equals(vertex)) {
          tawiter.remove();
        }
      }
    }
    // checkRep();
    return true;
  }

  @Override
  public Set<L> vertices() {
    Set<L> set = new HashSet<>();
    for (Vertex<L> v : vertices) {
      set.add(v.getSource());
    }
    return set;
  }

  @Override
  public Map<L, Integer> sources(L target) {
    Map<L, Integer> answer = new HashMap<>();
    for (Vertex<L> v : vertices) {
      for (TarAndWei<L> taw : v.getTargets()) {
        if (taw.getTarget().equals(target)) {
          answer.put(v.getSource(), taw.getWeight());
        }
      }
    }
    return answer;
  }

  @Override
  public Map<L, Integer> targets(L source) {
    Map<L, Integer> answer = new HashMap<>();
    for (Vertex<L> v : vertices) {
      if (v.getSource().equals(source)) {
        for (TarAndWei<L> taw : v.getTargets()) {
          answer.put(taw.getTarget(), taw.getWeight());
        }
      }
      // break;
    }
    return answer;
  }

  // TODO toString()
  @Override
  public String toString() {
    if (this.vertices.isEmpty()) {
      return "The graph is empty.";
    }
    StringBuffer answer = new StringBuffer();

    for (Vertex<L> vertex : vertices) {
      answer.append(vertex.toString());
    }

    return answer.toString();
  }
}


/**
 * TODO specification Mutable. This class is internal to the rep of ConcreteVerticesGraph. L must be
 * an immutable type a Vertex instance represents a vertex of a graph and all of the outward-edges
 * of the vertex.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to you.
 */
class Vertex<L> {

  // TODO fields
  private L source;
  private ArrayList<TarAndWei<L>> targets;
  /*
   * Abstraction function: source represents a vertex of the graph. targets represents the list of
   * the edges which are from vertex source.
   */
  /*
   * Representation invariant: source and each one in targets shouldn't be null. none of each two
   * vertices are the same in targets and source.
   */
  /*
   * Safety from rep exposure: 1. all fields are private. 2. since TarAndWei is a mutable type,
   * then: 1) in constructor Vertex(L source,TarAndWei<L> targetAndWeight), use defensive copy to
   * make a copy of the targetAndWeight argument. 2) in function getTargets(), returns an
   * unmodifiable view of the list of target 3) in function boolean addTarAndWei(TarAndWei<L> taw),
   * use defensive copy to make a copy of the taw argument. 4) in function getIterator(), it return
   * a iterator wrapping a new list instance other than the instance that targets points at.
   */

  // TODO constructor
  /**
   * constructor of class Vertex. create a new Vertex with only the starting point of the edge.
   * 
   * @param source the starting point of the edge
   */
  Vertex(L source) {
    this.source = source;
    this.targets = new ArrayList<>();
    // checkRep();
  }

  /**
   * check the rep invariant is true
   */

  private void checkRep() {
    assert source != null;
    for (TarAndWei<L> taw : targets) {
      assert taw != null;
      assert taw.equals(source) == false;
    }
    for (int i = 0; i < targets.size(); i++) {
      TarAndWei<L> tawi = targets.get(i);
      for (int j = 0; j < targets.size(); j++) {
        TarAndWei<L> tawj = targets.get(j);
        if (tawi != tawj) {
          assert tawi.equals(tawj) == false;
        }
      }
    }
  }

  /**
   * constructor of class Vertex. create a new Vertex with the starting point, and a TarAndWei
   * instance of a graph.
   * 
   * @param source the starting point of the edge
   * @param targetAndWeight a TarAndWei instance, which represents the weight and the target of an
   *        edge.
   */
  Vertex(L source, TarAndWei<L> targetAndWeight) {
    this.source = source;
    this.targets = new ArrayList<>();
    this.targets.add(new TarAndWei<L>(targetAndWeight.getWeight(), targetAndWeight.getTarget()));
    // checkRep();
  }

  // TODO methods
  /**
   * get the starting point of the Vertex instance.
   * 
   * @return starting point of the Vertex instance
   */
  L getSource() {
    return this.source;
  }

  /**
   * get the TarAndWei instances of the Vertex instance.
   * 
   * @return the TarAndWei instances of the Vertex instance
   */
  List<TarAndWei<L>> getTargets() {
    return Collections.unmodifiableList(this.targets);
    // return this.targets;
  }

  /**
   * add an instance of TarAndWei into the ending point list of this vertex
   * 
   * @param taw the taw instance you want to add
   * @return true if this collection changed as a result of the call
   */
  boolean addTarAndWei(TarAndWei<L> taw) {
    if (taw == null) return false;
    return this.targets.add(new TarAndWei<L>(taw.getWeight(), taw.getTarget()));
  }

  /**
   * remove an instance of TarAndWei of the ending point list of this vertex
   * 
   * @param taw the taw instance you want to remove
   * @return true if an element was removed as a result of this call
   */
  boolean removeTarAndWei(TarAndWei<L> taw) {
    if (taw == null) return false;
    return this.targets.remove(taw);
  }

  /**
   * gets an iterator of the ending point list
   * 
   * @return the iterator of the ending point list
   */
  Iterator<TarAndWei<L>> getIterator() {
    ArrayList<TarAndWei<L>> targets1 = new ArrayList<>(targets);
    return targets1.iterator();
  }

  // TODO toString()
  @Override
  public String toString() {
    StringBuffer answer = new StringBuffer();
    answer.append("Vertex: " + this.source.toString() + "  Outward-edges: ");
    for (TarAndWei<L> taw : targets) {
      answer.append(taw.toString() + " ");
    }
    answer.append('\n');
    return answer.toString();
  }
}


/**
 * This is a class that combines the weight and the target of an edge together. It nearly represent
 * a whole edge except the starting point. It can be used in class Vertex. It is mutable.
 *
 * @param <L> Type of the target.L must be immutable.
 */
class TarAndWei<L> {
  /*
   * Abstract function: weight represents the weight of the edge target represents the target of the
   * edge
   */
  /*
   * Rep Invariant: weight must be a positive number
   */
  /*
   * Safe from rep exposure: both rep is immutable type. so there won't have rep exposure.
   */
  private int weight;
  private L target;

  /**
   * constructor of class TarAndWei.
   * 
   * 
   * @param weight weight of the edge you want to represent
   * @param target target of the edge you want to represent
   */
  TarAndWei(int weight, L target) {
    this.target = target;
    this.weight = weight;
    checkRep();
  }

  /**
   * check the rep invariant is true
   */
  private void checkRep() {
    assert weight > 0;
  }

  /**
   * get the weight of the edge
   * 
   * @return the weight of the edge
   */
  int getWeight() {
    return weight;
  }

  /**
   * get the target of the edge
   * 
   * @return the target of the edge
   */
  L getTarget() {
    return target;
  }

  /**
   * set the weight of the edge and returns the old weight
   * 
   * @param weight the new weight you want to put
   * @return the previous weight of the edge
   */
  int setWeight(int weight) {
    int old = this.weight;
    this.weight = weight;
    checkRep();
    return old;
  }

  /**
   * set the target of the edge and returns the old target
   * 
   * @param target the new target you want to put
   * @return the previous target of the edge
   */
  L setTarget(L target) {
    L old = this.target;
    this.target = target;
    checkRep();
    return old;
  }

  @Override
  public String toString() {
    return "[" + this.getWeight() + "," + this.getTarget().toString() + "]";
  }
}
