package track;

import java.util.Set;

/**
 * This is a implementation of factory TrackFactory.
 *
 * @param the type of the PhysicalObject on the track. It MUST be IMMUTABLE. And it MUST
 *        implements the equals() method.
 */
public class CircleTrackFactory implements TrackFactory {

  @Override
  public Track getInstance(double radius) {
    if (radius <= 0) {
      return null;
    }
    return new CircleTrack(radius);
  }

  /*
   * @Override public Track<E> getInstance(Set<E> set, double radius) { if(radius<=0) return null;
   * return new CircleTrack<E>(set,radius); }
   */

}
