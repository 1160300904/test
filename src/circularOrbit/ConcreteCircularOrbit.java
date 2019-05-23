package circularOrbit;

import Relations.CTrelations;
import Relations.TRelations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import track.CircleTrackFactory;
import track.Track;
import track.TrackFactory;

public class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {

  private L centralobj = null;
  // tracks in the orbit
  private List<Track> tracks = new ArrayList<Track>();
  // radiuses of the tracks
  private List<Double> radiuses = new ArrayList<>();
  // sets of objects corresponds with tracks
  private List<HashSet<E>> obj = new ArrayList<>();

  // a map maps objects to its theta degree
  private HashMap<E, Double> thetas = new HashMap<>();

  // relations between objects
  private CTrelations<L, E> ctr = new CTrelations<>(this.centralobj);
  private TRelations<E> tr = new TRelations<>();

  /*
   * Abstract function: 1.centralobj is the central object of the circular orbit system. 2.tracks is
   * the tracks of the circular orbit system. 3.radiuses is radiuses of the tracks. 4.obj are the
   * sets of objects corresponds with tracks. 5.thetas is a map maps objects to its theta degree.
   * 6.ctr is relations between objects and central object. 7.tr is relations between every two
   * objects.
   */
  /*
   * Rep Invariant: 1.the size of tracks and radiuses and obj must be the same. 2.elements in thetas
   * must be the same with elements in obj.
   */
  void checkRep() {
    for (Track t : tracks) {
      assert t != null;
    }
    for (Double d : radiuses) {
      assert d != null;
      double rad = d;
      assert rad > 0;
    }
    Set<E> thetaKeySet = thetas.keySet();
    boolean contains = false;
    /*
     * for(E object:thetaKeySet) { //assert object!=null; Double d=thetas.get(object); //assert
     * d!=null; Double dou=d; assert dou<360&&d>=0; for(HashSet<E> sets:obj) {
     * if(sets.contains(object)) { contains=true; } } assert contains;contains=false; }
     */
    assert this.tracks.size() == this.radiuses.size();
    assert this.tracks.size() == this.obj.size();
    /*
     * for(HashSet<E> hs:obj) { for(E object1:hs) { assert thetaKeySet.contains(object1); } }
     * Map<E,Integer> count=new HashMap<>(); for(E obj1:thetaKeySet) { for(E obj2:thetaKeySet) {
     * if(obj1.equals(obj2)) { int c = count.getOrDefault(obj1, 0)+1; count.put(obj1, c); } } }
     * for(Integer in:count.values()) { assert in.intValue()==1; }
     */
  }

  @Override
  public void setCentralObj(L object) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
    this.centralobj = object;
    this.ctr.setCentralObject(object);
  }

  @Override
  public L getCentralObj() {
    return this.centralobj;
  }

  @Override
  public int trackAmount() {
    return tracks.size();
  }

  @Override
  public boolean addOutsideTrack(double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException();
    }

    if (radiuses.size() > 0) {
      Double outtrack = radiuses.get(radiuses.size() - 1);
      if (radius <= outtrack) {
        return false;
      }
    }

    TrackFactory tf = new CircleTrackFactory();
    Track t = tf.getInstance(radius);
    obj.add(new HashSet<E>());
    radiuses.add(radius);
    return tracks.add(t);

  }
  
  /**
   * add inside track.
   */
  public boolean addInsideTrack(double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException();
    }
    int i;
    for (i = 0; i < radiuses.size(); i++) {
      if (radiuses.get(i) == radius) {
        return false;
      }
      if (radiuses.get(i) > radius) {
        break;
      }
    }
    radiuses.add(i, radius);
    obj.add(i, new HashSet<E>());
    TrackFactory tf = new CircleTrackFactory();
    Track t1 = tf.getInstance(radius);
    tracks.add(i, t1);
    checkRep();
    return true;
  }

  @Override
  public void deleteTrack(int tracknum) {
    if (tracknum <= 0 || tracknum > radiuses.size()) {
      return;
    }
    int trackn = tracknum - 1;
    this.tracks.remove(trackn);
    this.obj.remove(trackn);
    this.radiuses.remove(trackn);
    HashSet<E> objontrack = obj.get(trackn);
    for (E object : objontrack) {
      this.thetas.remove(object);
    }
    // checkRep();
  }

  @Override
  public List<Double> getRadius() {
    return Collections.unmodifiableList(radiuses);
  }

  @Override
  public boolean addPhyToTrack(E physicalObject, double theta, int tracknum) {
    if (physicalObject == null) {
      throw new IllegalArgumentException();
    }
    if (tracknum <= 0 || tracknum > tracks.size()) {
      // System.out.println("invalid input");
      return false;
    }
    int trackn = tracknum - 1;
    if (this.obj.get(trackn).add(physicalObject) == false) {
      return false;
    }

    this.thetas.put(physicalObject, theta);
    // checkRep();
    return true;
  }

  @Override
  public boolean removePhy(E physicalObject, int tracknum) {
    if (physicalObject == null) {
      throw new IllegalArgumentException();
    }
    if (tracknum <= 0 || tracknum > tracks.size()) {
      // System.out.println("invalid input");
      // checkRep();
      return false;
    }
    int trackn = tracknum - 1;

    if (this.obj.get(trackn).remove(physicalObject) == false) {
      // checkRep();
      return false;
    }
    this.thetas.remove(physicalObject);
    // checkRep();
    return true;
  }

  @Override
  public boolean addPhyRelation(E physicalObject1, E physicalObject2) {
    if (physicalObject1 == null || physicalObject2 == null) {
      throw new IllegalArgumentException();
    }

    this.tr.addRelation(physicalObject1, physicalObject2);
    return true;

  }

  @Override
  public boolean transit(E object, double theta, int sourtracknum, int tartracknum) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
    if (sourtracknum <= 0 || sourtracknum > tracks.size()) {
      // System.out.println("invalid input");
      return false;
    }
    if (tartracknum <= 0 || tartracknum > tracks.size()) {
      // System.out.println("invalid input");
      return false;
    }
    if (this.removePhy(object, sourtracknum) == false) {
      return false;
    }
    boolean result = this.addPhyToTrack(object, theta, tartracknum);
    /*
     * for now it will not get false in this addPhyToTrack function. if(result==false) { return
     * false; }
     */
    return result;

  }

  @Override
  public int numOnTrack(int tracknum) {
    if (tracknum <= 0 || tracknum > tracks.size()) {
      // System.out.println("invalid input");
      return -1;
    }
    return this.obj.get(tracknum - 1).size();
  }

  @Override
  public double getPhysicalDistance(E e1, E e2) {
    Double theta1 = this.thetas.get(e1);
    Double theta2 = this.thetas.get(e2);
    if (theta1 == null || theta2 == null) {
      return Double.POSITIVE_INFINITY;
    }
    theta1 = Math.toRadians(theta1);
    theta2 = Math.toRadians(theta2);
    Double row1 = null;
    Double row2 = null;
    for (int i = 0; i < this.tracks.size(); i++) {
      if (obj.get(i).contains(e1)) {
        row1 = radiuses.get(i);
        // break; for performance, use break.
      }
    }
    for (int i = 0; i < this.tracks.size(); i++) {
      if (obj.get(i).contains(e2)) {
        row2 = radiuses.get(i);
        // break; for performance, use break.
      }
    }
    // System.out.println(row1+" "+theta1+" "+row2+" "+theta2+" ");
    Double x1 = row1 * Math.cos(theta1);
    Double y1 = row1 * Math.sin(theta1);
    Double x2 = row2 * Math.cos(theta2);
    Double y2 = row2 * Math.sin(theta2);
    // System.out.println("\\"+x1+" "+y1+" "+x2+" "+y2+" ");
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  @Override
  public int getLogicalDistance(E p1, E p2) {
    /*
     * if(!this.thetas.containsKey(p1)||!this.thetas.containsKey(p2)) return -1;
     */
    if (p1.equals(p2)) {
      return 0;
    }
    LinkedList<E> pque = new LinkedList<>();
    LinkedList<Integer> preque = new LinkedList<>();
    List<E> visited = new ArrayList<>();
    E quefront = null;
    Set<E> targets = new HashSet<E>();
    int distance = 0;
    boolean visitedboo = false;
    // change visiting strategy?
    pque.add(p1);
    preque.add(-1);
    visited.add(p1);
    // �˴��п�Ҫע��
    int index = 0;
    while (index < pque.size()) {
      quefront = pque.get(index++);
      targets = tr.targets(quefront).keySet();
      for (E p : targets) {
        visitedboo = false;
        for (E vp : visited) {
          if (vp.equals(p)) {
            visitedboo = true;
            break;
          }
        }
        if (visitedboo == false) {
          if (p.equals(p2)) {
            int preindex = index - 1;
            while (preindex != -1) {
              distance++;
              preindex = preque.get(preindex);
            }
            return distance;
          } else {
            pque.offer(p);
            preque.offer(index - 1);
            // Ҫע��ӵ�δ���ʽڵ���
            visited.add(p);
          }
        }
      }
    }
    return -1;
  }

  public List<HashSet<E>> getObjOnTracks() {
    return Collections.unmodifiableList(this.obj);
  }

  public int getObjNumOnTrack(int tracknum) {
    return this.obj.get(tracknum - 1).size();
  }

  @Override
  public Map<E, Double> getThetas() {
    return Collections.unmodifiableMap(this.thetas);
  }

  @Override
  public boolean isEdgeBetween(E e1, E e2) {
    return this.tr.relatedTo(e1, e2);
  }

  @Override
  public boolean containsObject(E e) {
    return this.thetas.containsKey(e);
  }

  @Override
  public OrbitIterator<L, E> iterator() {
    return new CircularOrbitIterator<L, E>(this);
  }
}
