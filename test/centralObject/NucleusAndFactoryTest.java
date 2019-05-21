package centralObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class NucleusAndFactoryTest {
	/*Testing strategy:
	 * 	1.getInstance():	input string: empty,not empty.
	 */
	@Test
	public void testNucleusAndFactory() {
		
		Nucleus u1=NucleusFactory.getInstance("Na");
		Nucleus u2=NucleusFactory.getInstance("K");
		Nucleus u3=NucleusFactory.getInstance("K");
		
		assertEquals("Na",u1.getName());
		assertEquals("K",u2.getName());
		assertTrue(u2.equals(u3));
		assertFalse(u2.equals("a"));
		assertFalse(u1.equals(u2));
		assertEquals("Na",u1.toString());
	}
}
