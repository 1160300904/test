package circularOrbit;

/**
 * This is an interface of an iterator of this circular orbit system, which can be used to iterate
 * the system in a order that you get the objects in the inner tracks first, then the outer side
 * tracks. Then, on each track, you will get objects has a smaller degree on the coordinate system.
 * 
 * @author Luke Skywalker
 *
 * @param <L> The type of center objects of the system.
 * @param <E> The type of objects on tracks of the system.
 */
public interface OrbitIterator<L, E> {

  /**
   * whether the iterator has more elements.
   * 
   * @return true if there are more elements, false otherwise.
   */
  public boolean hasNext();

  /**
   * Get the element which specified by hasNext. It must follow the operation hasNext.
   * 
   * @return the next element which specified by hasNext.
   */
  public E next();
}
