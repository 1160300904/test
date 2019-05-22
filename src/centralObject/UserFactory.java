package centralObject;

/**
 * This is a factory interface of User.
 *
 */
public class UserFactory {
  /**
   * get an instance of an user.
   */
  public static User getInstance(String name) {
    return new ConcreteUser(name);
  }
}
