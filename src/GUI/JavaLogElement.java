package GUI;

import java.time.LocalDateTime;
import java.util.logging.*;

public class JavaLogElement implements Comparable<JavaLogElement> {
  private LocalDateTime time = null;
  private String oe = null;
  private String detoe = null;
  private String message = null;
  private String sourceclass = null;
  private String sourcemethod = null;
  private String answer = null;

  /*
   * Abstract functions: 1.time means when this log element happens 2.oe means is this log an
   * operation or an exception 3.detoe means the detail of this operation or exception 4.message
   * means the message of this log element 5.sourceclass means the source class of this log element
   * 6.sourcemethod means the source method of this log element 7.answer means the response of the
   * operation or exception
   */
  /*
   * Rep Invariant: none.
   */
  JavaLogElement(LocalDateTime lt, String oe, String detoe, String message, String sourceclass,
      String sourcemethod, String answer) {
    this.time = lt;
    this.oe = oe;
    this.detoe = detoe;
    this.message = message;
    this.sourceclass = sourceclass;
    this.sourcemethod = sourcemethod;
    this.answer = answer;
  }

  /**
   * get the time of this log element
   * 
   * @return the time of this log element
   */
  public LocalDateTime getTime() {
    return this.time;
  }

  /**
   * get the type of this log element
   * 
   * @return the type of this log element
   */
  public String getExceptionOrOperation() {
    return this.oe;
  }

  /**
   * get the detail of the type of this log element
   * 
   * @return the detail of the type of this log element
   */
  public String getExceptionOrOperationDetail() {
    return this.detoe;
  }

  /**
   * Get the message of this log element
   * 
   * @return the message of this log element
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * Get the source class of this log element
   * 
   * @return Get the source class of this log element
   */
  public String getSourceClass() {
    return this.sourceclass;
  }

  /**
   * Get the source method of this log element
   * 
   * @return Get the source method of this log element
   */
  public String getSourceMethod() {
    return this.sourcemethod;
  }

  /**
   * Get the answer of this log element
   * 
   * @return Get the answer of this log element
   */
  public String getAnswer() {
    return this.answer;
  }

  @Override
  public int compareTo(JavaLogElement a) {
    return this.time.compareTo(a.getTime());
  }

  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("[" + time.toString() + "]");
    buf.append("[" + oe + "]");
    buf.append("[" + this.detoe + "]");
    buf.append("[" + this.message + "]");
    buf.append("[" + this.sourceclass + "]");
    buf.append("[" + this.sourcemethod + "]");
    buf.append("[" + this.answer + "]");
    return buf.toString();
  }
}
