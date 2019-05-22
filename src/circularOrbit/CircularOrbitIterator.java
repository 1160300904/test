package circularOrbit;

import java.util.*;

/**
 * An iterator of this circular orbit system, which can be used to iterate the system in a order
 * that you get the objects in the inner tracks first, then the outer side tracks. Then, on each
 * track, you will get objects has a smaller degree on the coordinate system.
 * 
 * @author Luke Skywalker
 *
 * @param <L> The type of center objects of the system.
 * @param <E> The type of objects on tracks of the system.
 */
public class CircularOrbitIterator<L, E> implements OrbitIterator<L, E> {

  CircularOrbit<L, E> orbit;
  private List<HashSet<E>> obj;
  private List<E> objlist = new ArrayList<>();
  private Iterator<E> iter;
  /*
   * Abstract function: 1.orbit is the orbit system you want to iterate. 2.obj is the objects on
   * tracks. 3.objlist is a list contains all the objects on the tracks. 4.iter is a iterator of
   * objlist.
   */
  /*
   * Rep Invariant: 1.elements in obj must be the same with elements in objlist.
   */

  void checkRep() {
    for (HashSet<E> objset : obj) {
      for (E obj : objset) {
        assert objlist.contains(obj);
      }
    }
    boolean contains = false;
    for (E object : objlist) {
      // assert object!=null;
      for (HashSet<E> sets : obj) {
        if (sets.contains(object)) {
          contains = true;
        }
      }
      assert contains;
      contains = false;
    }

    Map<E, Integer> count = new HashMap<>();
    for (E obj1 : objlist) {
      for (E obj2 : objlist) {
        if (obj1.equals(obj2)) {
          int c = count.getOrDefault(obj1, 0) + 1;
          count.put(obj1, c);
        }
      }
    }
    for (Integer in : count.values()) {
      assert in.intValue() == 1;
    }
  }

  /**
   * Creator of the Iterator.
   * 
   * @param orbit the orbit you want to iterate.
   */
  CircularOrbitIterator(CircularOrbit<L, E> orbit) {
    this.orbit = orbit;
    this.obj = orbit.getObjOnTracks();
    for (HashSet<E> hashs : obj) {
      for (E e : hashs) {
        this.objlist.add(e);
      }
    }
    this.iter = objlist.iterator();
    checkRep();
  }

  @Override
  public boolean hasNext() {
    return iter.hasNext();
  }

  @Override
  public E next() {
    return iter.next();
  }

}
