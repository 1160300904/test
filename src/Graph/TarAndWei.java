package Graph;

/**
 * This is a class that combines the weight and the target of an edge together. It nearly represent
 * a whole edge except the starting point. It can be used in class Vertex. It is mutable.
 *
 * @param <L> Type of the target.L must be immutable.
 */
public class TarAndWei<L> {
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
   * check the rep invariant is true.
   */
  private void checkRep() {
    assert weight > 0;
  }

  /**
   * get the weight of the edge.
   * 
   * @return the weight of the edge
   */
  int getWeight() {
    return weight;
  }

  /**
   * get the target of the edge.
   * 
   * @return the target of the edge
   */
  L getTarget() {
    return target;
  }

  /**
   * set the weight of the edge and returns the old weight.
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
   * set the target of the edge and returns the old target.
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
