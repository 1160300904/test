
package errorHandling;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Exception handler of AtomStructure.
 * 
 * @author Luke Skywalker
 *
 */
public class AtomStructureHandler {

    /**
     * handling the FilesyntaxException occurs in atom structure.
     * 
     * @param str the string which cause the error.
     */
    public String FilesyntaxHandling(String str) {
        if (str == null)
            return null;
        int NumOfEleIndex = str.indexOf("NumberOfElectron");
        if (NumOfEleIndex != -1) {
            return handleNumberOfElectron(str);
        }

        int eleNameIndex = str.indexOf("ElementName");
        if (eleNameIndex != -1) {
            return handleElementName(str);
        }

        int numOfTrackIndex = str.indexOf("NumberOfTracks");
        if (numOfTrackIndex != -1) {
            return handleNumOfTracks(str);
        }
        return null;
    }

    /**
     * Handle errors in NumberOfElectron sentence in file.
     * 
     * @param str the string which cause the error.
     */
    private String handleNumberOfElectron(String str) {
        str = str.trim();
        String strplus = str + ';';
        Pattern pattern = Pattern.compile("(([[0-9]\\.]+)/([[0-9]\\.]+);)+");
        Matcher matcher = pattern.matcher(strplus);
        matcher.find();
        String message;
        try {
            message = matcher.group(0);
        } catch (IllegalStateException e) {
            String ret = "The format of elements on track is wrong, please read memu again.";
            System.out.println("The format of elements on track is wrong, please read memu again.");
            return ret;
        }

        String[] elements = message.split(";");

        for (String s : elements) {
            // System.out.println(s);

            if (s.matches("([0-9]+)/([0-9]+)") == false) {
                String ret = "The syntax of element numbers on track is \"int\\int\"";
                System.out.println(ret);
                return ret;
            }

        }
        return null;

    }

    /**
     * Handle errors in name sentence in file.
     * 
     * @param str the string which cause the error.
     */
    private String handleElementName(String str) {
        String ret = null;
        int equalIndex = str.indexOf('=');
        if (equalIndex == -1) {
            ret = "There should be an \'=\' in this string";
            System.out.println(ret);
            return ret;
        }
        String numstr = str.substring(equalIndex + 1, str.length());
        Scanner s = new Scanner(numstr);
        String eleName = "ABC";
        try {
            eleName = s.next();
        } catch (NoSuchElementException e) {
            ret = "The name of the elements must be announced";
            System.out.println(ret);
            return ret;
        } finally {
            s.close();
        }
        s.close();
        if (eleName.length() == 1) {
            if (eleName.matches("[A-Z]+") == false) {
                ret = "If the element name has only one letter, then it should be Capital";
                System.out.println(ret);
                return ret;
            }
        }
        if (eleName.length() == 2) {
            if (eleName.matches("([A-Z]+)([a-z]+)") == false) {
                ret = "If the element name has two letters, "
                        + "then the first should be Capital, and the second should be lower-case";
                System.out.println(ret);
                return ret;
            }
        } else {
            ret = "There should be only two letters in a element name.";
            System.out.println(ret);
            return ret;
        }
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
            ret = "The format of the number of the track should be an integer.";
            System.out.println(ret);
            s.close();
            return ret;
        }
        if ((trackNum < 0)) {
            ret = "The number of the track should a positive number";
            System.out.println(ret);
            s.close();
            return ret;
        }
        s.close();
        return null;
    }

}
