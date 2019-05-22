package physicalObject;

/**
 * This is an interface represents the athlete in the racing app. It's a physicalObject on a track.
 * It is IMMUTABLE.
 *
 */
public interface Athlete extends PhysicalObject {

  /**
   * get the name of an athlete.
   * 
   * @return the name of an athlete
   */
  public String getName();

  /**
   * get the number of an athlete.
   * 
   * @return the number of an athlete
   */
  public int getNumber();

  /**
   * get the nationality of an athlete.
   * 
   * @return the nationality of an athlete
   */
  public String getNation();

  /**
   * get the age of an athlete.
   * 
   * @return the age of an athlete
   */
  public int getAge();

  /**
   * get the best score of an athlete.
   * 
   * @return the best score of an athlete
   */
  public double getBestScore();

}
