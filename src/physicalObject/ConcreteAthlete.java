package physicalObject;

/**
 * This is an implementation of Interface Athlete.
 *
 */
class ConcreteAthlete implements Athlete {

  private String name;
  private int number;
  private String nation;
  private int age;
  private double bestscore;

  /*
   * Abstract function: 1.name represents the name of the athlete. 2.number represents the number of
   * the athlete. 3.nation represents the nation of the athlete. 4.age represents the age of the
   * athlete. 5.bestscore represents the best score of the athlete.
   */
  /*
   * Rep Invariant: 1.name cannot be empty string. 2.number must be a positive number. 3.nation
   * cannot be empty string. 4.age must be a positive number. 5.bestscore must be a positive number.
   */
  /*
   * Safe from rep exposure. 1.all fields are private. 2.all return type and parameter type of the
   * methods are all immutable.
   */
  void checkRep() {
    assert name.equals("") == false;
    assert nation.equals("") == false;
    assert number > 0;
    assert age > 0;
    assert bestscore > 0;
    assert name.matches("([a-z[A-Z]]+)");
    assert nation.matches("([A-Z]{3})");
    // assert String.valueOf(bestscore).matches("([0-9]{1,2}\\.[0-9]{2})");
  }

  /**
   * create an instance of an athlete.
   * 
   * @param name represents the name of the athlete.
   * @param number represents the number of the athlete.
   * @param nation represents the nation of the athlete.
   * @param age represents the age of the athlete.
   * @param bestscore represents the best score of the athlete.
   */
  ConcreteAthlete(String name, int number, String nation, int age, double bestscore) {
    /*
     * assert name.equals("")==false; assert nation.equals("")==false; assert number>0; assert
     * age>0; assert bestscore>0; assert name.matches("([a-z[A-Z]]+)"); assert
     * nation.matches("([A-Z]{3})");
     */
    this.name = name;
    this.number = number;
    this.nation = nation;
    this.age = age;
    this.bestscore = bestscore;
    // checkRep();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getNumber() {
    return this.number;
  }

  @Override
  public String getNation() {
    return this.nation;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public double getBestScore() {
    return this.bestscore;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Athlete)) {
      return false;
    }
    Athlete o1 = (Athlete) o;
    return o1.getName().equals(this.getName()) && o1.getNumber() == this.number
        && o1.getNation().equals(this.nation) && o1.getAge() == this.age
        && o1.getBestScore() == this.bestscore;
  }

  @Override
  public String toString() {
    //Athlete ::= <Tommy,3,JAM,19,10.11> 
    StringBuffer b=new StringBuffer("Athlete ::= <");
    b.append(this.name+",");
    b.append(this.number+",");
    b.append(this.nation+",");
    b.append(this.age+",");
    b.append(this.bestscore+">");
    return b.toString();
  }

}
