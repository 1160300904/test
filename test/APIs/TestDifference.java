package APIs;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDifference {

    /*
     * Testing strategy: 1.setTrackNumDiff: 1）输入：>0,==0,<0 2.setObjNumDiff：
     * 1）tracknumber: 是否超过了目前得最大下标：是，否。 3.setObjDetailDiff: 1）tracknumber:
     * 是否超过了目前得最大下标：是，否。 2）objdetaildiff：是否为空串：是，否。 4.appendObjDetailDiff:
     * 1）objdetaildiff：是否为空串：是，否。 5.getObjNumDiff: 1）tracknumber: 是否超过了目前得最大下标：是，否。
     * 5.getObjDetailDiff： 1）tracknumber: 是否超过了目前得最大下标：是，否。
     */
    @Test
    public void testDifference() {
        Difference d = Difference.getInstance();
        d.setTrackNumDiff(0);
        d.appendObjNumDiff(2);
        d.setObjNumDiff(1, 1);
        String res = "The difference of track numbers is: 0 \n" + "The difference of object numbers on track 1 is : 1\n"
                + "There is no object detail differences between two orbits.\n";
        assertEquals(res, d.toString());
        d.appendObjDetailDiff("a-b");
        d.setObjDetailDiff(1, "a-c");
        String result = "The difference of track numbers is: 0 \n"
                + "The difference of object numbers on track 1 is : 1\n"
                + "The difference of object detail on track 1 is : a-c\n";
        assertEquals(result, d.toString());
        assertFalse(d.setObjNumDiff(100, 100));
        assertFalse(d.setObjDetailDiff(100, "a"));
        assertFalse(d.setObjDetailDiff(100, ""));
        assertEquals(0, d.getTrackNumDiff());
        assertEquals(1, d.getObjNumDiff(1));
        assertEquals(Integer.MAX_VALUE, d.getObjNumDiff(100));
        assertEquals("Index out of bound", d.getObjDetailDiff(100));
        assertEquals("a-c", d.getObjDetailDiff(1));

        // System.out.println(d.toString());
    }
}
