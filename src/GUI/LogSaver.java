package GUI;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogSaver {

  private Logger log = Logger.getLogger(LogSaver.class.getName());
  private List<JavaLogElement> loglist = new ArrayList<>();

  private static LogSaver logsaver = new LogSaver();

  /*
   * Abstract function: 1.log is the logger applications shares. 2.loglist is a list of
   * JavaLogElement which records the log events 3.log saver is the instance in the singleton
   * pattern
   * 
   */
  /*
   * Rep Invariant: 1.loglist������Ԫ�ز���Ϊnull
   */
  void checkRep() {
    for (JavaLogElement e : loglist) {
      assert e != null;
    }
  }

  private LogSaver() {
    try {
      Handler h = new FileHandler("myLog.txt");
      log.addHandler(h);
    } catch (SecurityException | IOException e) {
      System.out.println("error in creating MyLogger.");
      e.printStackTrace();
    }
  }

  /**
   * Get the instance in the singleton pattern.
   * 
   * @return the instance in the singleton pattern
   */
  public static LogSaver getInstance() {
    return logsaver;
  }

  /**
   * Get the logger in the instance of LogSaver, which can be used to log the opereations.or
   * exceptions.
   * 
   * @return the logger in the instance of LogSaver
   */
  public Logger getLogger() {
    return this.log;
  }

  /**
   * Add a LogRecord to the LogSaver.
   * 
   * @param lr the LogRecord you want to add.
   */
  public void add(LogRecord lr) {
    // LogRecord lr=new LogRecord(Level.INFO, mes);
    long mil = lr.getMillis();
    Instant i = Instant.ofEpochMilli(mil);
    LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
    // log.log(lr);
    String[] mesdetail = lr.getMessage().split(",");

    JavaLogElement logele = new JavaLogElement(ldt, mesdetail[0], mesdetail[1], mesdetail[2],
        lr.getSourceClassName(), lr.getSourceMethodName(), mesdetail[3]);
    this.loglist.add(logele);
  }

  /**
   * Query for the logger based on the category and detail you provide.
   * 
   * @param category the category you provides.
   * @param detail the detail you provides.
   * @return
   */
  public List<JavaLogElement> query(String category, String detail) {
    if (category.equals("Time")) {
      String[] times = detail.split(" ");
      LocalDateTime begin = parseTimeString(times[0]);
      LocalDateTime end = parseTimeString(times[1]);
      return queryTime(begin, end);
    }
    if (category.equals("OOrE")) {
      return queryOperation(detail);
    }
    if (category.equals("OOrEDetail")) {
      return queryDetailOperation(detail);
    }
    if (category.equals("Message")) {
      return queryMessage(detail);
    }
    if (category.equals("SourceClass")) {
      return querySourceClass(detail);
    }
    if (category.equals("SourceMethod")) {
      return querySourceMethod(detail);
    }
    if (category.equals("Answer")) {
      return queryAnswer(detail);
    }
    return null;
  }

  private List<JavaLogElement> queryTime(LocalDateTime begin, LocalDateTime end) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getTime().isAfter(begin) && ele.getTime().isBefore(end)) {
        retlist.add(ele);
      }
    }
    Collections.sort(retlist);
    return retlist;
  }

  private List<JavaLogElement> queryOperation(String operation) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getExceptionOrOperation().equals(operation)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private List<JavaLogElement> queryDetailOperation(String detail) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getExceptionOrOperationDetail().equals(detail)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private List<JavaLogElement> queryMessage(String message) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getMessage().equals(message)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private List<JavaLogElement> querySourceClass(String sourceclass) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getSourceClass().equals(sourceclass)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private List<JavaLogElement> querySourceMethod(String sourcemethod) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getSourceMethod().equals(sourcemethod)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private List<JavaLogElement> queryAnswer(String answer) {
    List<JavaLogElement> retlist = new ArrayList<>();
    for (JavaLogElement ele : this.loglist) {
      if (ele.getAnswer().equals(answer)) {
        retlist.add(ele);
      }
    }
    return retlist;
  }

  private static LocalDateTime parseTimeString(String str) {
    String[] strs = str.split("T");
    String date = strs[0];
    String[] dates = date.split("-");
    String[] times = strs[1].split(":");
    String[] second = times[2].split("\\.");
    return LocalDateTime.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]),
        Integer.valueOf(dates[2]), Integer.valueOf(times[0]), Integer.valueOf(times[1]),
        Integer.valueOf(second[0]));
  }
}
