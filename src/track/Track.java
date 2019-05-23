package track;


/**
 * This is a interface represents a track in the CircularOrbit system. This is an IMMUTABLE type.
 */
public interface Track {

  /**
   * If the track is a circle track, then this method returns the radius of the track.
   * 
   * @return the radius of the track.
   */
  public double getRadius();

}
