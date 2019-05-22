package Relations;

import java.util.*;

import com.sun.istack.internal.NotNull;

/**
 * This is a class represents the relations between CentralObject and PhysicalObject in a
 * CircularOrbit. This is MUTABLE.
 * 
 * @author Luke Skywalker
 *
 */
public class CTRelations<L, E> {
  private L central;
  private Set<E> phyobj;

  /*
   * Abstract function: 1.central represents the CentralObject in this CircularOrbit. 2.phyobj
   * represents a list of PhysicalObject that are related with the CentralObject. 3.all the objects
   * and only the objects in phyobj are related with the CentralObject.
   */
  /*
   * Rep Invarinat: 1.each objects in phyobj must not be null.
   */
  /*
   * Safe from Rep exposure: 1.all fields are private. 2.getCentralObject() method returns a
   * immutable type L. 3.getPhysicalObject() method returns an unmodifiable view of set phyobj.
   * 4.Other functions' parameter are all of immutable type L, and the return value are primitive
   * type boolean.
   */
  void checkRep() {
    for (E e : phyobj) {
      assert e != null;
    }
  }

  /**
   * Constructor of CTRelations.
   * 
   * @param central the CentralObject in these relations.
   */

  public CTRelations(@NotNull L central) {

    this.central = central;
    this.phyobj = new HashSet<E>();
    // checkRep();
  }

  /**
   * Another constructor of CTRelations.
   * 
   * @param central the CentralObject in these relations.
   * @param obj the initial object set of these relations.
   */
  public CTRelations(Set<E> obj, L central) {
    if (obj == null || central == null) {
      throw new IllegalArgumentException();
    }
    Set<E> newset = new HashSet<E>(obj);
    this.central = central;
    this.phyobj = newset;
  }

  /**
   * get the CentralObject in these relations.
   * 
   * @return the CentralObject in these relations.
   */
  public L getCentralObject() {
    return this.central;
  }

  /**
   * set the CentralObject in these relations.
   * 
   * @return the CentralObject in these relations.
   */
  public void setCentralObject(L object) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
    this.central = object;
  }

  /**
   * Get a set of all the PhysicalObjects in these relations.
   * 
   * @return a unmodifiableSet of all the PhysicalObjects in these relations.
   */
  public Set<E> getPhysicalObject() {
    return Collections.unmodifiableSet(this.phyobj);
  }

  /**
   * Add a relation between CentralObject and e. If the relation has existed in current relations,
   * then this class is not modified.
   * 
   * @param e PhysicalObject you want to add relation with.
   * @return true if you succeeded in adding this relation, false otherwise.
   */
  public boolean addRelation(@NotNull E e) {
    return this.phyobj.add(e);
  }

  /**
   * Delete a relation between CentralObject and e. If the relation doesn't exist in current
   * relations, then this class is not modified.
   * 
   * @param e PhysicalObject you want to delete relation with.
   * @return true if you succeeded in removing this relation, false otherwise.
   */
  public boolean deleRelation(@NotNull E e) {
    return this.phyobj.remove(e);
  }

  /**
   * See if there is a relation between CentralObject and e.
   * 
   * @param e PhysicalObject you want to inquire.
   * @return true is there is a relation between CentralObject and e, false otherwise.
   */
  public boolean relatedTo(@NotNull E e) {
    return this.phyobj.contains(e);
  }
}
