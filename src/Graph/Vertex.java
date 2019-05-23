package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * TODO specification Mutable. This class is internal to the rep of ConcreteVerticesGraph. L must be
 * an immutable type a Vertex instance represents a vertex of a graph and all of the outward-edges
 * of the vertex.
 * PS2 instructions: the specification and implementation of this class is up to you.
 */
public class Vertex<L> {

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
   * check the rep invariant is true.
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
   * add an instance of TarAndWei into the ending point list of this vertex.
   * 
   * @param taw the taw instance you want to add
   * @return true if this collection changed as a result of the call
   */
  boolean addTarAndWei(TarAndWei<L> taw) {
    if (taw == null) {
      return false;
    }
    return this.targets.add(new TarAndWei<L>(taw.getWeight(), taw.getTarget()));
  }

  /**
   * remove an instance of TarAndWei of the ending point list of this vertex.
   * 
   * @param taw the taw instance you want to remove
   * @return true if an element was removed as a result of this call
   */
  boolean removeTarAndWei(TarAndWei<L> taw) {
    if (taw == null) {
      return false;
    }
    return this.targets.remove(taw);
  }

  /**
   * gets an iterator of the ending point list.
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



