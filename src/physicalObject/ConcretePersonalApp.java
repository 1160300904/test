package physicalObject;

/**
 * This is an implementation of Interface Athlete.
 *
 */
class ConcretePersonalApp implements PersonalApp {

  private String name;
  private String company;
  private String version;
  private String functionality;
  private String businessarea;

  /*
   * Abstract function: 1.name represents the name of the personal app. 2.company represents the
   * company of the personal app. 3.version represents the version of the personal app.
   * 4.functionality represents the functionality of the personal app. 5.businessarea represents the
   * business area of the personal app.
   */
  /*
   * Rep Invariant: 1.all the string fields must not be empty string.
   */
  /*
   * Safe from rep exposure. 1.all fields are private. 2.all return type and parameter type of the
   * methods are all immutable.
   */
  void checkRep() {
    assert name.equals("") == false;
    assert this.company.equals("") == false;
    assert this.version.equals("") == false;
    assert this.functionality.equals("") == false;
    assert this.businessarea.equals("") == false;

    assert name.matches("([[a-z][A-Z][0-9]]+)");
    assert this.company.matches("([[a-z][A-Z][0-9]]+)");
    assert this.version.matches("([[a-z][A-Z][0-9]-_\\.]+)");
    assert this.functionality.matches("(\"[[a-z][A-Z][0-9]\\s]+\")");
    assert this.businessarea.matches("(\"[[a-z][A-Z][0-9]\\s]+\")");
  }

  /**
   * create an instance of personal app.
   * 
   * @param name represents the name of the personal app.
   * @param company represents the company of the personal app.
   * @param version represents the version of the personal app.
   * @param functionality represents the functionality of the personal app.
   * @param businessarea represents the business area of the personal app.
   */
  ConcretePersonalApp(String name, String company, String version, String functionality,
      String businessarea) {

    assert name.equals("") == false;
    assert company.equals("") == false;
    assert version.equals("") == false;
    assert functionality.equals("") == false;
    assert businessarea.equals("") == false;
    this.name = name;
    this.company = company;
    this.version = version;
    this.functionality = functionality;
    this.businessarea = businessarea;
    // checkRep();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getCompany() {
    return this.company;
  }

  @Override
  public String getVersion() {
    return this.version;
  }

  @Override
  public String getFunctionality() {
    return this.functionality;
  }

  @Override
  public String getBusinessArea() {
    return this.businessarea;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof PersonalApp)) {
      return false;
    }
    PersonalApp o1 = (PersonalApp) o;
    return o1.getName().equals(this.getName()) && (o1.getCompany()).equals(this.company)
        && o1.getVersion().equals(this.version)
        && (o1.getFunctionality()).equals(this.functionality)
        && (o1.getBusinessArea()).equals(this.businessarea);
  }

  @Override
  public String toString() {
    return this.getName();
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}
