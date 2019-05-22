package centralObject;

/**
 * This is an interface represents the user in a Personal App Ecosystem. It is IMMUTABLE.
 */
public interface User extends CentralObject {

  /**
   * get the name of the user.
   * 
   * @return the name of the user
   */
  public String getName();

}
