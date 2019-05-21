package appExceptions;
/**
 * This is a class of one of the app Exceptions. If there are repeated objects in the initialize
 * file, then this exception will be thrown.
 * @author Luke Skywalker
 *
 */
public class RepeatedObjectsException extends Exception{
	/**
	 * Creator of an instance of RepeatedObjectsException
	 */
	public RepeatedObjectsException(){
		super();
	}
	/**
	 * Creator of an instance of RepeatedObjectsException with a message string.
	 * @param s		the message you want to put in.
	 */
	public RepeatedObjectsException(String s){
		super(s);
	}
}
