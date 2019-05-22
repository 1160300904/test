package errorHandling;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Exception handler of PersonalAppEcosystem.
 * 
 * @author Luke Skywalker
 *
 */
public class PersonalAppHandler {

  /**
   * handling the FilesyntaxException occurs in PersonalAppEcosystem.
   * 
   * @param str the string which cause the error.
   */
  public String FilesyntaxHandling(String str) {
    if (str == null) return null;
    int athleteIndex = str.indexOf("App");
    if (athleteIndex != -1) {
      return handleApp(str);
    }

    int userIndex = str.indexOf("User");
    if (userIndex != -1) {
      return handleUser(str);
    }

    int periodIndex = str.indexOf("Period");
    if (periodIndex != -1) {
      return handlePeriod(str);
    }

    int installIndex = str.indexOf("InstallLog");
    if (installIndex != -1) {
      return handleInstallLog(str);
    }

    int uninstallIndex = str.indexOf("UninstallLog");
    if (uninstallIndex != -1) {
      return handleUnInstallLog(str);
    }

    int UseLogIndex = str.indexOf("UsageLog");
    if (UseLogIndex != -1) {
      return handleUseLog(str);
    }

    int relationIndex = str.indexOf("Relation");
    if (relationIndex != -1) {
      return handleRelation(str);
    }
    return null;
  }

  /**
   * Handle errors in Relation sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleRelation(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the relation should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 2) {
      ret = "There should be two apps in one relation log description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }
    if (elements[1].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }
    return null;

  }

  /**
   * Handle errors in UseLog sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleUseLog(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the Useage Log should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 4) {
      ret = "There should be three infos in one useage log description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") == false) {
      ret = "The format of Date in Useage Log should be like YYYY-MM-DD";
      System.out.println(ret);
      return ret;
    }

    if (elements[1].matches("([0-9]{2}):([0-9]{2}):([0-9]{2})") == false) {
      ret = "The format of Time in Useage Log should be like hh:mm:ss";
      System.out.println(ret);
      return ret;
    }

    if (elements[2].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }

    if (elements[3].matches("([0-9]+)") == false) {
      ret = "The Duration in Useage Log should be a positive integer.";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

  /**
   * Handle errors in UnInstallLog sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleUnInstallLog(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the UnInstall Log should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 3) {
      ret = "There should be three infos in one uninstall log description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") == false) {
      ret = "The format of Date in UnInstall Log should be like YYYY-MM-DD";
      System.out.println(ret);
      return ret;
    }

    if (elements[1].matches("([0-9]{2}):([0-9]{2}):([0-9]{2})") == false) {
      ret = "The format of Time in UnInstall Log should be like hh:mm:ss";
      System.out.println(ret);
      return ret;
    }

    if (elements[2].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

  /**
   * Handle errors in InstallLog sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleInstallLog(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the inatalllog should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 3) {
      ret = "There should be three infos in one install log description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") == false) {
      ret = "The format of Date in Install Log should be like YYYY-MM-DD";
      System.out.println(ret);
      return ret;
    }

    if (elements[1].matches("([0-9]{2}):([0-9]{2}):([0-9]{2})") == false) {
      ret = "The format of Time in Install Log should be like hh:mm:ss";
      System.out.println(ret);
      return ret;
    }

    if (elements[2].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

  /**
   * Handle errors in App sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleApp(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the app should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 5) {
      ret = "There should be five infos in one app description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The name of app should be a label, only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }

    if (elements[1].matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "The company of app should be a label, " + "only letter and number is allowed";
      System.out.println(ret);
      return ret;
    }

    if (elements[2].matches("([[a-z][A-Z][0-9]-_\\.]+)") == false) {
      ret = "The version of app should be a label, "
          + "only \'-\', \'_\', \'.\', letter and number is allowed";
      System.out.println(ret);
      return ret;
    }

    if (elements[3].startsWith("\"") == false) {
      ret = "The functionality of app should be a sentence, " + "which starts with \"";
      System.out.println(ret);
      return ret;
    }
    if (elements[3].endsWith("\"") == false) {
      ret = "The functionality of app should be a sentence, " + "which ends with \"";
      System.out.println(ret);
      return ret;
    }
    if (elements[3].matches("\"[[a-z][A-Z][0-9]\\s]+\"") == false) {
      ret = "The functionality of app should be a sentence, "
          + "only space, letters, numbers may appears.";
      System.out.println(ret);
      return ret;
    }

    if (elements[4].startsWith("\"") == false) {
      ret = "The business area of app should be a sentence, " + "which starts with \"";
      System.out.println(ret);
      return ret;
    }
    if (elements[4].endsWith("\"") == false) {
      ret = "The business area of app should be a sentence, " + "which ends with \"";
      System.out.println(ret);
      return ret;
    }
    if (elements[4].matches("\"[[a-z][A-Z][0-9]\\s]+\"") == false) {
      ret = "The functionality of app should be a sentence, "
          + "only space, letters, numbers may appears.";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

  /**
   * Handle errors in user sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleUser(String str) {
    String ret = null;
    int equalIndex = str.indexOf('=');
    if (equalIndex == -1) {
      ret = "There should be an \'=\' in this string";
      System.out.println(ret);
      return ret;
    }
    String numstr = str.substring(equalIndex + 1, str.length());
    Scanner s = new Scanner(numstr);
    String username = "a";
    try {
      username = s.next();
    } catch (NoSuchElementException e) {
      ret = "There should be a username in the file.";
      System.out.println(ret);
      return ret;
    } finally {
      s.close();
    }
    int userIndex = str.indexOf(username);
    if (userIndex + username.length() < str.length()) {
      ret = "Space is not permitted in this file.";
      System.out.println(ret);
      return ret;
    }
    if (username.matches("([[a-z][A-Z][0-9]]+)") == false) {
      ret = "Only letters and numbers can exits in the username.";
      System.out.println(ret);
      return ret;
    }
    s.close();
    return null;
  }

  /**
   * Handle errors in Period sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handlePeriod(String str) {
    String ret = null;
    int equalIndex = str.indexOf('=');
    if (equalIndex == -1) {
      ret = "There should be an \'=\' in this string";
      System.out.println(ret);
      return ret;
    }
    String numstr = str.substring(equalIndex + 1, str.length());
    Scanner s = new Scanner(numstr);
    String period = "a";
    try {
      period = s.next();
    } catch (NoSuchElementException e) {
      ret = "There should be a period in the file.";
      System.out.println(ret);
      return ret;
    } finally {
      s.close();
    }
    if ((period.equals("Day") || period.equals("Hour") || period.equals("Week")
        || period.equals("Month")) == false) {
      ret = "Period can only be Hour|Day|Week|Month ";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

}
