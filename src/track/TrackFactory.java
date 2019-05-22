package track;

import java.util.*;

/**
 * This is a factory interface for type Track.
 * 
 * @param E the type of the PhysicalObject on the track. It MUST be IMMUTABLE.
 *          And it MUST implements the equals() method.
 */
public interface TrackFactory {

    /**
     * Get an instance of a track
     * 
     * @param the radius of the track.
     * @return An instance of Track<E> which has the radius of {@code radius}. If
     *         radius<=0, then the method returns null.
     */
    public Track getInstance(double radius);

    /**
     * Another factory method of Track.
     * 
     * @param radius the radius of the track.
     * @param the    initial object set of the track. It shouldn't be null.
     * @return An instance of Track<E> which has the radius of {@code radius}, and
     *         all the objects in the input set. If radius<=0, then the method
     *         returns null.
     */
    // public Track<E> getInstance(Set<E> set,double radius);
}
