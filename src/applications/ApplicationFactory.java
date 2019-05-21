package applications;

import circularOrbit.CircularOrbit;
/**
 * This is an interface of all the application factory.
 * @author Luke Skywalker
 *
 */
public interface ApplicationFactory {

	/**
	 * Factory method of all applications.
	 * @return
	 */
	public CircularOrbit getApplication();
}
