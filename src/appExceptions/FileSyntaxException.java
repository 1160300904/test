package appExceptions;

/**
 * This is a class of one of the app Exceptions. If there are file syntax errors
 * in the initialize file, then this exception will be thrown.
 * 
 * @author Luke Skywalker
 *
 */
public class FileSyntaxException extends Exception {

    /**
     * Creator of an instance of FileSyntaxException
     */
    public FileSyntaxException() {
        super();
    }

    /**
     * Creator of an instance of FileSyntaxException with a message string.
     * 
     * @param s the message you want to put in.
     */
    public FileSyntaxException(String s) {
        super(s);
    }
}
