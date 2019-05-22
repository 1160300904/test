package physicalObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class ElectronAndFactoryTest {
    /*
     * TestingStrategy 1.electron名字是否为空串 2.轨道数是否为>0
     */
    @Test
    public void testElectronAndFactory() {

        Electron e1 = ElectronFactory.getInstance(1);
        Electron e2 = ElectronFactory.getInstance(1);

        assertEquals("", e1.getName());
        assertEquals(1, e1.getTrackItOn());
        assertTrue(e1.equals(e1));
        assertFalse(e1.equals(e2));
    }
}
