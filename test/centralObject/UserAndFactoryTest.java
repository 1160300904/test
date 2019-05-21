package centralObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserAndFactoryTest {

	/*Testing strategy:
	 * 	1.getInstance():	input string: empty,not empty.
	 */
	@Test
	public void testUserAndFactory() {
		User u1=UserFactory.getInstance("1");
		User u2=UserFactory.getInstance("A");
		User u3=UserFactory.getInstance("A");
		
		assertEquals("1",u1.getName());
		assertEquals("A",u2.getName());
		assertEquals("A",u2.toString());
		
		assertFalse(u1.equals(u2));
		assertFalse(u1.equals("d"));
		assertTrue(u2.equals(u3));
		
		
		
	}
}
