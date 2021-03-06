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
public class ConcreteEdgesGraph<L> implements Graph<L> {

  private final Set<L> vertices = new HashSet<>();
  private final List<Edge<L>> edges = new ArrayList<>();

  /*
   * Abstraction function: vertices represents the vertices of the edge. edges represents the edge
   * of the edge.
   */
  /*
   * Representation invariant: if vertices is empty, then edges must be empty. there shouldn't be
   * multiple edges between two vertices. the two vertices of an edge in edges must exists in the
   * rep vertices.
   */
  /*
   * Safety from rep exposure: all fields are private and final. L is immutable type, so it's fine
   * to input a parameter without defensive copy. vertices() returns an unmodifiable view of the set
   * since L is immutable, the HashMap that sources() and targets() returns is not rep exposure.
   */

  // TODO constructor
  public ConcreteEdgesGraph() {

  }

  private void checkRep() {
    if (vertices.isEmpty()) {
      assert edges.isEmpty();
    }
    for (int i = 0; i < edges.size(); i++) {
      Edge<L> ei = edges.get(i);
      for (int j = 0; j < edges.size(); j++) {

        Edge<L> ej = edges.get(j);
        if (ei != ej) {
          assert (ei.getSource().equals(ej.getSource()) == false)
              || (ei.getTarget().equals(ej.getTarget()) == false);
        }
      }
      assert vertices.contains(ei.getSource()) || vertices.contains(ei.getTarget());
    }

  }

  @Override
  public boolean add(L vertex) {
    if (vertices.contains(vertex)) {
      return false;
    }
    vertices.add(vertex);
    checkRep();
    return true;

  }

  @Override
  public int set(L source, L target, int weight) {
    // �����ڣ��Ȱ��������ҳ������ٽ��з�֧
    Edge<L> existed = null;
    for (Edge<L> edge : edges) {
      if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
        existed = edge;
        break;
      }
    }

    if (weight == 0) {
      if (existed == null) {
        return 0;
      } else {
        int existw = existed.getWeight();
        edges.remove(existed);
        // checkRep();
        return existw;
      }
    } else {
      if (existed == null) {
        if (vertices.contains(source) == false) {
          vertices.add(source);
        }
        if (vertices.contains(target) == false) {
          vertices.add(target);
        }
        edges.add(new Edge<L>(source, weight, target));
        // checkRep();
        return 0;
      } else {
        int oldw = existed.getWeight();
        edges.add(new Edge<L>(existed.getSource(), weight, existed.getTarget()));// defensive copy
        edges.remove(existed);
        // checkRep();
        return oldw;
      }
    }

  }

  @Override
  public boolean remove(L vertex) {
    if (vertices.contains(vertex)) {
      vertices.remove(vertex);
      Edge<L> edge;
      Iterator<Edge<L>> iter = edges.iterator();
      while (iter.hasNext()) {
        edge = iter.next();
        if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
          iter.remove();
        }
      }
      // checkRep();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Set<L> vertices() {
    return Collections.unmodifiableSet(vertices);
  }

  @Override
  public Map<L, Integer> sources(L target) {
    Map<L, Integer> answer = new HashMap<>();
    for (Edge<L> edge : edges) {
      if (edge.getTarget().equals(target)) {
        answer.put(edge.getSource(), edge.getWeight());
      }
    }
    return answer;
  }

  @Override
  public Map<L, Integer> targets(L source) {
    Map<L, Integer> answer = new HashMap<>();
    for (Edge<L> edge : edges) {
      if (edge.getSource().equals(source)) {
        answer.put(edge.getTarget(), edge.getWeight());
      }
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
    answer.append("The vertecies are: ");
    for (L vertex : vertices) {
      answer.append("[" + vertex.toString() + "] ");
    }
    answer.append('\n');
    answer.append("The edges are: ");
    for (Edge<L> edge : edges) {
      answer.append("[" + edge.getSource().toString() + "," + edge.getWeight() + ","
          + edge.getTarget().toString() + "] ");
    }
    answer.append('\n');
    return answer.toString();
  }
}



