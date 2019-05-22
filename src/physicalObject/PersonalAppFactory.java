package physicalObject;

/**
 * This is a factory interface of PersonalApp.
 *
 */
public class PersonalAppFactory {
  /**
   * get an instance of an PersonalApp.
   */
  public static PersonalApp getInstance(String name, String company, String version,
      String functionality, String businessarea) {
    return new ConcretePersonalApp(name, company, version, functionality, businessarea);
  }
}
