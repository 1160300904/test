package centralObject;
/**
 * This is a factory interface of Nucleus.
 *
 */
public class NucleusFactory {
	/**
	 * get an instance of an Nucleus.
	 */
	public static Nucleus getInstance(String name) {
		return new ConcreteNucleus(name);
	}
}
