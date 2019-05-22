package physicalObject;

/**
 * This is an interface represents the PersonalApp objects on the track of a circular orbit system.
 * It is IMMUTABLE.
 *
 */
public interface PersonalApp extends PhysicalObject {

  /**
   * get the name of the app.
   * 
   * @return the name of the app
   */
  public String getName();

  /**
   * get the company of the app.
   * 
   * @return the company of the app
   */
  public String getCompany();

  /**
   * get the version of the app.
   * 
   * @return the version of the app
   */
  public String getVersion();

  /**
   * get the functionality of the app.
   * 
   * @return the functionality of the app
   */
  public String getFunctionality();

  /**
   * get the business area of the app.
   * 
   * @return the business area of the app
   */
  public String getBusinessArea();
}
