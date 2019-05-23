package Graph;
/**
 * TODO specification Immutable. This class is internal to the rep of ConcreteEdgesGraph. an Edge
 * instance represent an edge of a graph. L must be an immutable type
 * PS2 instructions: the specification and implementation of this class is up to you.
 */

public class Edge<L> {

  // TODO fields
  private final L source;
  private final L target;
  private final int weight;
  /*
   * Abstraction function: source represents the source of the edge target represents the target of
   * the edge weight represents the weight of the edge
   */
  /*
   * Representation invariant: the weight must be a positive number source and target must not be
   * null pointer
   */
  /*
   * Safety from rep exposure: all fields are immutable all fields are private and final
   */

  /**
   * constructor of an edge. it represents an directed weighted edge in the graph. L must be
   * immutable.
   * 
   * @param source starting point of the edge
   * @param weight an int type weight of the weighted edge
   * @param target ending point of the edge
   */
  Edge(L source, int weight, L target) {
    this.source = source;
    this.target = target;
    this.weight = weight;
    checkRep();
  }

  /**
   * check the rep invariant is true.
   */
  private void checkRep() {
    assert weight > 0;
    assert source != null;
    assert target != null;
  }

  // TODO methods

  /**
   * get the starting point of the edge.
   * 
   * @return an instance of the starting point.
   */
  L getSource() {
    return this.source;
  }

  /**
   * get the ending point of the edge.
   * 
   * @return an instance of the ending point.
   */
  L getTarget() {
    return this.target;
  }

  /**
   * get the weight of the edge.
   * 
   * @return weight of the edge.
   */
  int getWeight() {
    return this.weight;
  }

  // TODO toString()
  @Override
  public String toString() {
    return ("This edge is from " + source.toString() + " to " + target.toString()
        + " with weight of " + weight);
  }
}
