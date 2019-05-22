package APIs;

/**
 * This is an interface represents the difference of objects on two circular orbit systems.
 * 
 * @author Luke Skywalker
 *
 */
public interface Difference {

  /**
   * Static factory method of this ADT.
   * 
   * @return an instance of a Difference class.
   */
  public static Difference getInstance() {
    return new ConcreteListDifference();
  }

  /**
   * Set the difference on track numbers between two circular orbits.
   * 
   * @param tracknumdif the message you want to put in.
   */
  public void setTrackNumDiff(int tracknumdif);

  /**
   * Set the difference on object numbers on tracks between two circular orbits.
   * 
   * @param tracknum The track number of the track you want to set your message on.
   * @param objnumdiff the message of differences in numbers you want to put in.
   */
  public boolean setObjNumDiff(int tracknum, int objnumdiff);

  /**
   * Append the difference on object numbers on tracks between two circular orbits.
   * 
   * @param objnumdiff the message of differences in numbers you want to put in.
   */
  public void appendObjNumDiff(int objnumdiff);

  /**
   * Append the difference on object details on tracks between two circular orbits.
   * 
   * @param objdetaildiff the message of differences in details you want to put in.
   */
  public void appendObjDetailDiff(String objdetaildiff);

  /**
   * Set the difference on object details on tracks between two circular orbits.
   * 
   * @param tracknum The track number of the track you want to set your message on.
   */
  public boolean setObjDetailDiff(int tracknum, String objdetaildiff);

  /**
   * Get the difference on track numbers between two circular orbits.
   * 
   * @return the difference message you want.
   */
  public int getTrackNumDiff();

  /**
   * Get the difference on object numbers on tracks between two circular orbits.
   * 
   */
  public int getObjNumDiff(int tracknum);

  /**
   * Get the difference on object details on tracks between two circular orbits.
   * 
   */
  public String getObjDetailDiff(int tracknum);

  @Override
  public String toString();
}
