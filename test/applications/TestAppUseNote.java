package applications;

import static org.junit.Assert.*;
import org.junit.Test;

import physicalObject.*;
import java.util.*;

public class TestAppUseNote {

    /*
     * Testing strategy: 1.构造方法输入参数是否合法 2.得到的综合属性是否为0
     */
    @Test
    public void testAppUseNote() {
        AppUseNote a1 = new AppUseNote(1, 1, "A");
        assertEquals(1, a1.getFrequence());
        assertEquals(1, a1.getTime());
        assertEquals("A", a1.getName());
        assertEquals(11, a1.getSynthesize());

        AppUseNote a2 = new AppUseNote(1, 2, "A");
        assertEquals(-1, a1.compareTo(a2));
        String a1s = "A  frequence: 1time: 1";
        assertEquals(a1s, a1.toString());

    }
}
