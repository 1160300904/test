package track;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is a implementation of factory TrackFactory.
 *
 * @param the type of the PhysicalObject on the track. It MUST be IMMUTABLE. And it MUST
 *        implements the equals() method.
 */
public class CircleTrackFactory implements TrackFactory {

  private static Map<Double,Track> flyweights=new HashMap<>();
  
  @Override
  public Track getInstance(double radius) {
    if (radius <= 0) {
      return null;
    }
    Track t=flyweights.get(radius);
    if(t!=null) return t;
    t = new CircleTrack(radius);
    flyweights.put(radius, t);
    return t;
  }

  /*
   * @Override public Track<E> getInstance(Set<E> set, double radius) { if(radius<=0) return null;
   * return new CircleTrack<E>(set,radius); }
   */

}
