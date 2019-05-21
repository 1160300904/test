package centralObject;
/**
 * This is an interface represents the nucleus in a Atom structure.
 *	It is IMMUTABLE.
 */
public interface Nucleus extends CentralObject{
	
	/**
	 * get the name of the nucleus.
	 * @return	the name of the nucleus
	 */
	public String getName();
}
