package Relations;

import com.sun.istack.internal.NotNull;

import Graph.*;
import java.util.*;

/**
 * This is a class represents the relations between PhysicalObjects in a CircularOrbit. This is
 * MUTABLE.
 *
 */
public class TRelations<E> {
  private Graph<E> graph = Graph.empty();

  /*
   * Abstract Function: 1.graph represents a relation graph. 2.if two objects of type E are related
   * in CircularOrbit, then there will be an edge in the graph that connects these objects.
   */
  /*
   * Rep Invariant: 1.objects cann't have relations with themselves.
   */
  /*
   * Safe from rep exposure: 1.all fields are private; 2.all method returns void or primitive types.
   * 3.all parameters in methods are of immutable type E.
   */

  /**
   * Constructor of TRelation. Initialize an empty ralation graph.
   */
  public TRelations() {
    graph = Graph.empty();
  }

  /**
   * check if the rep invariant is correct
   */
  private void checkRep() {
    for (E e : graph.vertices()) {
      Set<E> targetsset = graph.targets(e).keySet();
      assert !targetsset.contains(e);
    }
  }

  /**
   * Add a relation between two objects. If the relation has existed in current relations, then this
   * class is not modified.
   * 
   * @param e1 PhysicalObject you want to add relation with.
   * @param e2 Another PhysicalObject you want to add relation with.
   */
  public void addRelation(@NotNull E e1, @NotNull E e2) {
    graph.set(e1, e2, 1);
    // checkRep();
  }

  /**
   * Delete a relation between two objects. If the relation doesn't exist in current relations, then
   * this class is not modified.
   * 
   * @param e1 PhysicalObject you want to add relation with.
   * @param e2 Another PhysicalObject you want to add relation with.
   */
  public void deleRelation(@NotNull E e1, @NotNull E e2) {
    graph.set(e1, e2, 0);
    // checkRep();
  }

  /**
   * See if there is a relation between two objects.
   * 
   * @param e1 One PhysicalObject you want to check with.
   * @param e2 Another PhysicalObject you want to add check with.
   * @return true is there is a relation between CentralObject and e, false otherwise.
   */
  public boolean relatedTo(@NotNull E e1, @NotNull E e2) {
    Set<E> retset = graph.targets(e1).keySet();
    return retset.contains(e2);
  }

  /**
   * Get the target vertices with directed relations from a source vertex and the weights of those
   * edges.
   * 
   * @param source a label
   * @return a map where the key set is the set of labels of vertices such that this graph includes
   *         an edge from source to that vertex, and the value for each key is the (nonzero) weight
   *         of the edge from source to the key
   */
  public Map<E, Integer> targets(E source) {
    return graph.targets(source);
  }
}
