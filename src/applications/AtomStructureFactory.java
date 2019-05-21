package applications;

import circularOrbit.CircularOrbit;
/**
 * This is a factory class which is used to creat an instance of AtomStructure.
 * @author Luke Skywalker
 *
 */
public class AtomStructureFactory implements ApplicationFactory {

	@Override
	public CircularOrbit getApplication() {
		// TODO Auto-generated method stub
		return new AtomStructure();
	}

}
