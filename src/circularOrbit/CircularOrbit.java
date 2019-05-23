package circularOrbit;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * This is a class represents a multiple tracks system. In this system, there is One or zero
 * centralObject which is surrounded by one or more tracks. And there are physicalObjects on the
 * tracks. Each track has a track number which is equal or greater than one. This is a MUTABLE type.
 * 
 * @param L L represents the type of centralObject, it MUST be IMMUTABLE. And it MUST implements the
 *        equals() method.
 * @param E E represents the physicalObjects on the track, it MUST be IMMUTABLE. And it MUST
 *        implements the equals() method.
 *
 */
public interface CircularOrbit<L, E> {

  /**
   * create an empty CircularOrbit which has a CentralObject of type L, and physicalObjects of type
   * E.
   * 
   * @return an empty instance of CircularOrbit.
   */
  public static <L, E> CircularOrbit<L, E> empty() {
    return new ConcreteCircularOrbit<L, E>();
  }

  /**
   * set the Central Object of this Orbit. Before calling this method, the central object is null.
   * 
   * @param object the CentralObject of the orbit.
   */
  public void setCentralObj(L object);

  /**
   * get the Central Object of this Orbit.
   * 
   * @return the CentralObject of the orbit.
   */
  public L getCentralObj();

  /**
   * get the total amount of tracks in this system.
   * 
   * @return the total amount of tracks in this system.
   */
  public int trackAmount();

  /**
   * add a track to the outside layer of the CircularOrbit.
   * 
   * @param radius the radius of the track you want to add. The radius of this track must larger
   *        than any other tracks in this system.
   * @return true if it succeeded in adding a track, false otherwise.
   * @throws IllegalArgumentException if radius<=0, throws IllegalArgumentException.
   */
  public boolean addOutsideTrack(double radius);

  /**
   * add a track of a specified radius to the tracks of the circular system. If the track of the
   * input radius is already in the system, then return false.
   * 
   * @param radius the radius of the new track you want to add.
   * @return true if it succeeded in adding the track, false otherwise.
   * @throws IllegalArgumentException if radius<=0, throws IllegalArgumentException.
   */
  public boolean addInsideTrack(double radius);

  /**
   * delete the input track in the orbit system.If the tracknum is out of range of [1,max track
   * number] then the system is left unmodified.
   * 
   * @param tracknum the number of the track you want to delete.It must between the max number and
   *        the minimum number of the current track in orbit.
   */
  public void deleteTrack(int tracknum);

  /**
   * Get the radius of the tracks in the system.
   * 
   * @return a list contains the radius of the tracks in the system, in the increading order.
   */
  public List<Double> getRadius();

  /*
   * add a centralObject into the orbit system.
   * 
   * @param centralObject the centralObject you want to add.
   * 
   * @return true if it succeeded in adding a centralObject, false otherwise.
   */
  // public boolean addCentralObject(L centralObject);

  /**
   * add a physicalObject into the orbit system.
   * 
   * @param physicalObject the centralObject you want to add.It cannot be null.
   * @param tracknum the number of track you want to add the physicalObject to.It must between the
   *        max number and the minimum number of the current track in orbit.
   * @param theta theta of the coordinate of the object you want to add. It MUST be in degree.
   * @return true if it succeeded in adding a physicalObject, false otherwise. If the track
   *         specified by tracknum is not in the system, then it returns false, and the system is
   *         left unmodified. If the object is already in the system, then it returns false, and the
   *         system is left unmodified.
   * @throws IllegalArgumentException if physicalObject is null, then throws
   *         IllegalArgumentException.
   */
  public boolean addPhyToTrack(E physicalObject, double theta, int tracknum);

  /**
   * remove a physicalObject from track which has a number of tracknum.
   * 
   * @param physicalObject object you want to remove.It cannot be null.
   * @param tracknum from which you want to remove the object.It must between the max number and the
   *        minimum number of the current track in orbit.
   * @return true if it succeeded in removing the object. If the track specified by tracknum is not
   *         in the system, then it returns false, and the system is left unmodified. If the object
   *         does not in the system, then it returns false, and the system is left unmodified.
   * @throws IllegalArgumentException if physicalObject is null, then throws
   *         IllegalArgumentException.
   */
  public boolean removePhy(E physicalObject, int tracknum);

  /**
   * add a relation between two PhysicalObjects in the orbit system.
   * 
   * @param physicalObject1 the first PhysicalObject in the relation.It cannot be null.
   * @param physicalObject2 the second PhysicalObject in the relation.It cannot be null.
   * @return true if it succeeded in adding this relation, false otherwise. If either of these two
   *         objects doesn't exists in the system, then it returns false.
   * @throws IllegalArgumentException if physicalObject is null, then throws
   *         IllegalArgumentException.
   */
  public boolean addPhyRelation(E physicalObject1, E physicalObject2);

  /* it may change */
  // public CircularOrbit<L,E> initWithFile(File file);

  /**
   * move an object from its current track to the specified one.
   * 
   * @param object the object you want to move.It cannot be null.
   * @param sourtracknum the number of the source track you want to move from.It must between the
   *        max number and the minimum number of the current track in orbit.
   * @param tartracknum the number of the target track you want to move to.It must between the max
   *        number and the minimum number of the current track in orbit.
   * @param theta the coordinate where the object you want to put at. It MUST be in degree.
   * @return true if it succeeded in moving this object, false otherwise.
   */
  public boolean transit(E object, double theta, int sourtracknum, int tartracknum);

  /**
   * get the number of objects on a particular track.
   * 
   * @param tracknum the number of the track you want to specify on.It must between the max number
   *        and the minimum number of the current track in orbit.
   * @return the number of objects on that track.If tracknum is out of bound, then it returns -1.
   */
  public int numOnTrack(int tracknum);

  /**
   * Get the logical distance between two objects, not considering the connection between central
   * objects with physical objects. which is the minimum number of edges that connects two objects
   * on track. if these two objects aren't connected, then return value is INF.
   * 
   * @param e1 one of the object you want to calculate the distance with.
   * @param e2 another object you want to calculate the distance with.
   * @return the logical distance between two objects. if these two objects aren't connected, then
   *         return value is INF.
   */
  public int getLogicalDistance(E e1, E e2);

  /**
   * Get the physical distance between two objects.
   * 
   * @param e1 one of the object you want to calculate the distance with.It must appears in the
   *        circular system.
   * @param e2 another object you want to calculate the distance with.It must appears in the
   *        circular system.
   * @return the physical distance between two objects.
   */
  public double getPhysicalDistance(E e1, E e2);

  /**
   * Get the objects on tracks of this circular orbit system.
   * 
   * @return the objects on tracks of this circular orbit system.
   */
  public List<HashSet<E>> getObjOnTracks();

  /**
   * Get the number of objects on a particular track.
   * 
   * @param tracknum the track number you specify.
   * @return the number of objects on a particular track.
   */
  public int getObjNumOnTrack(int tracknum);

  /**
   * Get a map which maps objects on tracks to its degree on the coordinate system.
   * 
   * @return the degrees of each objects.
   */
  public Map<E, Double> getThetas();

  /**
   * Test if there is an edge between two objects.
   * 
   * @param e1 one object you want to test with.
   * @param e2 another object you want to test with.
   * @return true if there is an edge between two objects, false otherwise.
   */
  public boolean isEdgeBetween(E e1, E e2);

  /**
   * Test if this circular orbit system contains an object.
   * 
   * @param e the object you want to test with.
   * @return true if this system contains the object. false otherwise.
   */
  public boolean containsObject(E e);

  /**
   * Get an iterator of this circular orbit system, which can be used to iterate the system in a
   * order that you get the objects in the inner tracks first, then the outer side tracks. Then, on
   * each track, you will get objects has a smaller degree on the coordinate system.
   * 
   * @return An iterator to iterate the orbit.
   */
  public OrbitIterator<L, E> iterator();
  // get object on track

  // get position on track

}
