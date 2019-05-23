/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved. Redistribution of original
 * or derived work requires permission of course staff.
 */

package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
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
   * check if the rep invariant is true.
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
      if (v.getSource().equals(vertex)) {
        return false;
      }
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
      if (v.getSource().equals(source)) {
        existedv = v;
      }
    }
    if (existedv != null) {
      for (TarAndWei<L> taw : existedv.getTargets()) {
        if (taw.getTarget().equals(target)) {
          existedt = taw;
        }
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
      if (v.getSource().equals(vertex)) {
        ver = v;
      }
    }
    if (ver == null) {
      return false;
    }
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


