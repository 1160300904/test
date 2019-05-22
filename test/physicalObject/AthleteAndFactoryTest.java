package physicalObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class AthleteAndFactoryTest {

    /*
     * Testing strategy: 1. equals: 1）输入的另一个对象是否为一个运动员的对象。
     * 2）另一个对象：姓名/年龄/国籍/号码/最好成绩分别是否与本对象相同
     * 
     */
    @Test
    public void testAthleteAndFactory() {
        Athlete a1 = AthleteFactory.getInstance("1", 1, "ABC", 11, 10.0);
        Athlete a3 = AthleteFactory.getInstance("1", 1, "ABC", 11, 10.0);
        Athlete a2 = AthleteFactory.getInstance("2", 1, "ABC", 11, 10.0);

        Athlete a5 = AthleteFactory.getInstance("1", 2, "ABC", 11, 10.0);
        Athlete a6 = AthleteFactory.getInstance("1", 1, "ADC", 11, 10.0);
        Athlete a7 = AthleteFactory.getInstance("1", 1, "ABC", 12, 10.0);
        Athlete a8 = AthleteFactory.getInstance("1", 1, "ABC", 11, 11.0);
        assertTrue(a1.equals(a3));
        assertFalse(a1.equals(a2));
        assertFalse(a1.equals("A"));

        assertFalse(a1.equals(a5));
        assertFalse(a1.equals(a6));
        assertFalse(a1.equals(a7));
        assertFalse(a1.equals(a8));

        assertEquals("1", a1.toString());
    }
}
