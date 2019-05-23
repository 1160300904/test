package track;


/**
 * This is an implementation of interface Track. It's a IMMUTABLE class.
 */
public class CircleTrack implements Track {

  private double radius;

  /*
   * Abstract Function: 1.radius represents the radius of the track.
   */
  /*
   * Rep Invariant: 1.radius must greater than zero. (radius>0)
   */
  /*
   * Safe from Rep Exposure: 1.all fields are private. 2.all return type and parameter type of all
   * method are immutable.
   */
  /**
   * Constructor of CircleTrack.
   * 
   * @param radius the radius of this track.
   */
  public CircleTrack(double radius) {
    this.radius = radius;
    // checkRep();
  }

  /**
   * check if the Rep Invariant is correct.
   */
  private void checkRep() {
    assert this.radius > 0;
  }

  @Override
  public double getRadius() {
    return this.radius;
  }

}
