package centralObject;

/**
 * This is an implementation of Interface User.
 */
class ConcreteUser implements User {

  private String name;

  /*
   * Abstract function: 1.name represents the name of the user.
   */
  /*
   * Rep Invariant: 1.name should not be empty string.
   */
  /*
   * Safe from rep exposure. 1.all fields are private. 2.all return type of the methods are all
   * immutable.
   */
  /**
   * Constructor of a concrete user.
   * 
   * @param name
   */
  ConcreteUser(String name) {
    assert name.equals("") == false;
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof User)) return false;
    User o1 = (User) o;
    return o1.getName().equals(this.getName());
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
