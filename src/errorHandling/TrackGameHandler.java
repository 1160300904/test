package errorHandling;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Exception handler of TrackGame.
 * 
 * @author Luke Skywalker
 *
 */
public class TrackGameHandler {

  /**
   * handling the FilesyntaxException occurs in TrackGame.
   * 
   * @param str the string which cause the error.
   */
  public String filesyntaxHandling(String str) {
    if (str == null) {
      return null;
    }
    int athleteIndex = str.indexOf("Athlete");
    if (athleteIndex != -1) {
      return handleAthlete(str);
    }

    int gameIndex = str.indexOf("Game");
    if (gameIndex != -1) {
      return handleGame(str);
    }

    int numOfTrackIndex = str.indexOf("NumOfTracks");
    if (numOfTrackIndex != -1) {
      return handleNumOfTracks(str);
    }
    return null;
  }

  /**
   * Handle errors in Athlete sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleAthlete(String str) {
    String ret = null;
    Pattern pattern = Pattern.compile("<.*>");
    Matcher matcher = pattern.matcher(str);
    matcher.find();
    String message;
    try {
      message = matcher.group(0);
    } catch (IllegalStateException e) {
      ret = "The message of the athlete should be surrounded with < and >";
      System.out.println(ret);
      return ret;
    }
    int oneIndex = str.indexOf('<');
    int twoIndex = str.indexOf('>');
    message = str.substring(oneIndex + 1, twoIndex);
    String[] elements = message.split(",");

    if (elements.length != 5) {
      ret = "There should be five infos in one athlete description";
      System.out.println(ret);
      return ret;
    }

    if (elements[0].matches("([a-z[A-Z]]+)") == false) {
      ret = "The name of athlete should be a word, only letter is allowed";
      System.out.println(ret);
      return ret;
    }

    if (elements[2].matches("[A-Z]{3}") == false) {
      ret = "The nation of athlete should be three capital letters";
      System.out.println(ret);
      return ret;
    }

    try {
      Integer.valueOf(elements[1]);
    } catch (NumberFormatException e) {
      ret = "The age of athlete should be an integer";
      System.out.println(ret);
      return ret;
    }
    try {
      Integer.valueOf(elements[3]);
    } catch (NumberFormatException e) {
      ret = "The number of athlete should be an integer";
      System.out.println(ret);
      return ret;
    }
    try {
      Double.valueOf(elements[4]);
    } catch (NumberFormatException e) {
      ret = "The best score of athlete should be a double";
      System.out.println(ret);
      return ret;
    }

    Pattern p = Pattern.compile("([0-9]{1,2}\\.[0-9]{2})");
    Matcher m = p.matcher(elements[4]);
    m.find();
    try {
      m.group(1);
    } catch (IllegalStateException e) {
      ret = "The best score of an athlete should be no more than two digit numbers"
          + "and two decimal places";
      System.out.println(ret);
      return ret;
    }
    return null;
  }

  /**
   * Handle errors in game sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleGame(String str) {
    String ret = null;
    int equalIndex = str.indexOf('=');
    if (equalIndex == -1) {
      ret = "There should be an \'=\' in this string";
      System.out.println(ret);
      return ret;
    }
    String numstr = str.substring(equalIndex + 1, str.length());
    Scanner s = new Scanner(numstr);
    int game = 100;
    try {
      game = s.nextInt();
    } catch (InputMismatchException e) {
      ret = "The format of the game should be an integer of 100, or 200, or 400";
      System.out.println(ret);
      s.close();
      return ret;
    }
    if ((game != 100) && (game != 200) && (game != 400)) {
      ret = "The game can only be 100, or 200, or 400";
      s.close();
      System.out.println(ret);
      return ret;
    }
    s.close();
    return null;
  }

  /**
   * Handle errors in NumOfTracks sentence in file.
   * 
   * @param str the string which cause the error.
   */
  private String handleNumOfTracks(String str) {
    String ret = null;
    int equalIndex = str.indexOf('=');
    if (equalIndex == -1) {
      ret = "There should be an \'=\' in this string";
      System.out.println(ret);
      return ret;
    }
    String numstr = str.substring(equalIndex + 1, str.length());
    Scanner s = new Scanner(numstr);
    int trackNum = 4;
    try {
      trackNum = s.nextInt();
    } catch (InputMismatchException e) {
      ret = "The format of the game should be an integer.";
      System.out.println(ret);
      s.close();
      return ret;
    }
    if ((trackNum < 4) || (trackNum > 10)) {
      ret = "The number of the track should between 4 and 10";
      System.out.println(ret);
      s.close();
      return ret;
    }
    s.close();
    return null;
  }

}
