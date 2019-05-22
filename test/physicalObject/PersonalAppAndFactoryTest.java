package physicalObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class PersonalAppAndFactoryTest {
    /*
     * Testing stragegy: 1.app名字是否相同 2.app的输入信息是否合法 3.app判重时，是否存在着引用不同但内容相同的情况
     */
    @Test
    public void testPersonalAppAndFactory() {
        PersonalApp p11 = PersonalAppFactory.getInstance("wechat", "tencent", "1.2.0", "communication",
                "for common people");
        PersonalApp p12 = PersonalAppFactory.getInstance("wechat", "tencent", "1.2.0", "communication",
                "for common people");
        PersonalApp p2 = PersonalAppFactory.getInstance("wechat2", "tencent", "1.2.0", "communication",
                "for business communication");
        PersonalApp p3 = PersonalAppFactory.getInstance("wechat2", "tencent1", "1.2.0", "communication",
                "for business communication");
        PersonalApp p4 = PersonalAppFactory.getInstance("wechat2", "tencent", "1.2.01", "communication",
                "for business communication");
        PersonalApp p5 = PersonalAppFactory.getInstance("wechat2", "tencent", "1.2.0", "communication1",
                "for business communication");
        PersonalApp p6 = PersonalAppFactory.getInstance("wechat2", "tencent", "1.2.0", "communication",
                "for business communication1");

        assertTrue(p11.equals(p12));
        assertFalse(p11.equals(p2));
        assertFalse(p3.equals(p2));
        assertFalse(p4.equals(p2));
        assertFalse(p5.equals(p2));
        assertFalse(p6.equals(p2));
        assertFalse(p3.equals("A"));

        assertEquals("wechat", p11.toString());
        assertEquals(-791770330, p11.hashCode());
    }
}
