package physicalObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class ElectronAndFactoryTest {
	/*TestingStrategy
	 * 	1.electron�����Ƿ�Ϊ�մ�
	 * 	2.������Ƿ�Ϊ>0
	 */
	@Test
	public void testElectronAndFactory() {
		
		Electron e1=ElectronFactory.getInstance(1);
		Electron e2=ElectronFactory.getInstance(1);
		
		assertEquals("",e1.getName());
		assertEquals(1,e1.getTrackItOn());
		assertTrue(e1.equals(e1));
		assertFalse(e1.equals(e2));
	}
}
