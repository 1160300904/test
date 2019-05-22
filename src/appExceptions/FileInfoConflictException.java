package appExceptions;

/**
 * This is a class of one of the app Exceptions. If there is something in the
 * initialize file which conflicts with each other, then this exception will be
 * thrown.
 * 
 * @author Luke Skywalker
 *
 */
public class FileInfoConflictException extends Exception {

    /**
     * Creator of an instance of FileInfoConflictException
     */
    public FileInfoConflictException() {
        super();
    }

    /**
     * Creator of an instance of FileInfoConflictException with a message string.
     * 
     * @param s the message you want to put in.
     */
    public FileInfoConflictException(String s) {
        super(s);
    }
}
