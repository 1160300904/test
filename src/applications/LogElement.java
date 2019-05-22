package applications;

/**
 * This is a class represents a piece of trace of install/uninstall/useage trace.
 * 
 * @author Luke Skywalker
 *
 */
public class LogElement implements Comparable<LogElement> {

  private String name;
  private MyDate date;

  /*
   * Abstract Function: 1.name is the name of the app in this log element. 2.date is the time when
   * this log event happens.
   */
  /*
   * Rep Invariant: none.
   */
  LogElement(String name, MyDate date) {
    this.name = name;
    this.date = date;
  }

  /**
   * get the name of the app of the log element.
   * 
   * @return the name of the app of the log element.
   */
  String getName() {
    return this.name;
  }

  /**
   * get the date of the app of the log element.
   * 
   * @return the date of the app of the log element.
   */
  MyDate getDate() {
    return this.date;
  }

  @Override
  public int compareTo(LogElement e) {
    return this.date.compareTo(e.getDate());
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append(this.name + ": ");
    s.append(this.date.toString());
    return s.toString();
  }
}
