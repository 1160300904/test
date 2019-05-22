package circularOrbit;

import static org.junit.Assert.*;
import org.junit.Test;

import centralObject.*;
import physicalObject.*;
import track.*;
import java.util.*;

public class CircularOrbitTest {
    /*
     * Testing strategy: (1)setCentralObj() : 输入的物体对象是否为空 (2) boolean
     * addInsideTrack(double radius) ①输入的轨道半径是否<0 ②轨道半径是否与系统中已有轨道的半径相同
     * ③输入轨道半径是否比已有轨道的半径都小或都大 (3)void deleteTrack(int tracknum) ①输入轨道号码是否超过了已有的轨道数目
     * (4)boolean addPhyToTrack(E physicalObject, double theta, int tracknum)
     * ①输入的物体是否为null ②输入的角度为0，360,90,180,270,>360,<0 ③输入轨道编号是否超过了已有轨道数目 (5) boolean
     * removePhy(E physicalObject, int tracknum) ①输入的物体是否为null ②输入轨道编号是否超过了已有轨道数目
     * (6)boolean addPhyRelation(E physicalObject1, E physicalObject2) ①两个物体是否相同
     * ②两个轨道物体是否为null ③两个轨道物体是否已经存在了轨道系统上 (7)boolean transit(E object, double theta,
     * int sourtracknum,int tartracknum) ①输入的物体是否为null
     * ②输入的角度为0，360,90,180,270,>360,<0 ③Sourtracknum，和tartracknum是否超过了已有的轨道数目 (8)int
     * NumOnTrack(int tracknum) ①tracknum是否超过了已有的轨道数目 ②该轨道上是否有物体 (9)double
     * getPhysicalDistance( E e1, E e2) ①这两个物体是否有不存在于轨道系统中的 ②两个物体是否在一条水平或竖直线上
     * ③两个物体是否在一条轨道上 ④两个物体的角度是否相同 ⑤两个物体的位置是否重合 (10) int getLogicalDistance(E p1, E
     * p2) ①两个物体间是否不存在逻辑关系 ②两个物体的逻辑关系是否为所有物体对中最远的 ③两个物体是否相同
     */
    @Test
    public void testCentralObject() {
        CircularOrbit<User, PersonalApp> c = CircularOrbit.empty();
        User u1 = UserFactory.getInstance("1");

        assertEquals(null, c.getCentralObj());
        c.setCentralObj(u1);
        assertEquals(u1, c.getCentralObj());
        try {
            c.setCentralObj(null);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }

    }

    @Test
    public void testTrack() {
        CircularOrbit<User, PersonalApp> c = CircularOrbit.empty();
        CircularOrbit<User, PersonalApp> c2 = CircularOrbit.empty();
        User u1 = UserFactory.getInstance("1");
        User u2 = UserFactory.getInstance("2");
        c.setCentralObj(u1);
        c2.setCentralObj(u2);
        TrackFactory tf = new CircleTrackFactory();
        Track t1 = tf.getInstance(1);
        Track t2 = tf.getInstance(2.2);
        Track t3 = tf.getInstance(3.3);
        Track t4 = tf.getInstance(4);
        assertEquals(0, c.TrackAmount());
        // test addOutsideTrack()
        try {
            c.addOutsideTrack(-1);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        assertTrue(c.addOutsideTrack(1));
        assertTrue(c.addOutsideTrack(2));
        assertTrue(c.addOutsideTrack(3));
        assertTrue(c.addOutsideTrack(4));
        assertFalse(c.addOutsideTrack(2.2));
        assertTrue(c.addOutsideTrack(5));
        assertEquals(5, c.TrackAmount());

        // test addInsideTrack() and getRadius()
        try {
            c.addInsideTrack(-2);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        try {
            c.addInsideTrack(0);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        assertTrue(c.addInsideTrack(2.2));
        assertTrue(c.addInsideTrack(6));
        assertFalse(c.addInsideTrack(6));
        assertTrue(c.addInsideTrack(0.1));
        assertEquals(8, c.TrackAmount());
        assertTrue(c2.addInsideTrack(1));
        assertEquals(1, c2.TrackAmount());
        List<Double> lc = c.getRadius();
        assertEquals(0.1, lc.get(0).doubleValue(), 0.01);
        assertEquals(1, lc.get(1).doubleValue(), 0.01);
        assertEquals(2, lc.get(2).doubleValue(), 0.01);
        assertEquals(2.2, lc.get(3).doubleValue(), 0.01);
        assertEquals(3, lc.get(4).doubleValue(), 0.01);
        assertEquals(4, lc.get(5).doubleValue(), 0.01);
        assertEquals(5, lc.get(6).doubleValue(), 0.01);
        assertEquals(6, lc.get(7).doubleValue(), 0.01);

        // test deleteTrack()
        c.deleteTrack(-2);
        assertEquals(8, c.TrackAmount());
        c.deleteTrack(9);
        assertEquals(8, c.TrackAmount());
        PersonalApp p1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
        c.addPhyToTrack(p1, 1, 4);
        c.deleteTrack(4);
        assertEquals(7, c.TrackAmount());
        c.deleteTrack(7);
        assertEquals(6, c.TrackAmount());
        c.deleteTrack(1);
        assertEquals(5, c.TrackAmount());
        List<Double> lcc = c.getRadius();
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 1, lcc.get(i).doubleValue(), 0.01);
        }
        c2.deleteTrack(1);
        assertEquals(0, c2.TrackAmount());
        assertTrue(c2.getRadius().isEmpty());

    }

    @Test
    public void TestObjectOnTrack() {
        // changed constructor of electron
        CircularOrbit<Nucleus, Electron> c = CircularOrbit.empty();
        Electron e1 = ElectronFactory.getInstance(1);
        Electron e2 = ElectronFactory.getInstance(1);
        Electron e3 = ElectronFactory.getInstance(1);

        try {
            c.addPhyToTrack(null, 0, 1);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        c.addInsideTrack(1);
        c.addInsideTrack(2);
        c.addInsideTrack(3);
        c.addInsideTrack(4);
        assertFalse(c.addPhyToTrack(e1, Math.PI, 9));
        assertFalse(c.addPhyToTrack(e1, Math.PI, 0));
        assertTrue(c.addPhyToTrack(e1, Math.PI, 1));
        assertFalse(c.addPhyToTrack(e1, Math.PI, 1));
        assertTrue(c.addPhyToTrack(e2, Math.PI / 2, 2));
        assertTrue(c.addPhyToTrack(e3, Math.PI / 2, 1));

        assertEquals(2, c.NumOnTrack(1));
        assertEquals(1, c.NumOnTrack(2));
        assertEquals(-1, c.NumOnTrack(-2));
        assertEquals(-1, c.NumOnTrack(22));

        try {
            c.removePhy(null, 1);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        assertFalse(c.removePhy(e1, 9));
        assertFalse(c.removePhy(e1, -9));
        assertTrue(c.removePhy(e1, 1));
        assertFalse(c.removePhy(e1, 1));
        assertEquals(1, c.NumOnTrack(1));
        assertEquals(1, c.NumOnTrack(2));

        assertTrue(c.removePhy(e2, 2));
        assertTrue(c.removePhy(e3, 1));
        assertFalse(c.removePhy(e3, 1));
        assertTrue(c.NumOnTrack(1) == 0);
        assertTrue(c.NumOnTrack(11) == -1);
    }

    @Test
    public void testRelationAndLogicalDistance() {
        CircularOrbit<User, PersonalApp> c = CircularOrbit.empty();
        PersonalApp p1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
        PersonalApp p2 = PersonalAppFactory.getInstance("2", "2", "2", "2", "2");
        PersonalApp p3 = PersonalAppFactory.getInstance("3", "3", "3", "3", "3");
        PersonalApp p4 = PersonalAppFactory.getInstance("4", "4", "4", "4", "4");
        PersonalApp p5 = PersonalAppFactory.getInstance("5", "4", "4", "4", "4");
        PersonalApp p6 = PersonalAppFactory.getInstance("6", "4", "4", "4", "4");
        PersonalApp p7 = PersonalAppFactory.getInstance("7", "4", "4", "4", "4");
        PersonalApp p8 = PersonalAppFactory.getInstance("8", "4", "4", "4", "4");

        c.addInsideTrack(1);
        c.addInsideTrack(2);
        c.addInsideTrack(3);
        c.addInsideTrack(4);
        c.addPhyToTrack(p1, 90.0, 1);
        c.addPhyToTrack(p2, 0, 2);
        c.addPhyToTrack(p3, -135, 3);
        c.addPhyToTrack(p4, -60, 4);
        c.addPhyToTrack(p6, 90, 3);
        c.addPhyToTrack(p5, 0, 4);
        c.addPhyRelation(p1, p2);
        c.addPhyRelation(p2, p3);
        c.addPhyRelation(p2, p4);
        c.addPhyRelation(p4, p5);
        c.addPhyRelation(p2, p1);
        c.addPhyRelation(p3, p2);
        c.addPhyRelation(p4, p2);
        c.addPhyRelation(p5, p4);
        assertTrue(c.addPhyRelation(p1, p7));
        assertTrue(c.addPhyRelation(p7, p1));
        assertTrue(c.addPhyRelation(p8, p7));
        try {
            c.addPhyRelation(null, null);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        try {
            c.addPhyRelation(p1, null);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        try {
            c.addPhyRelation(null, p1);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        assertEquals(1, c.getLogicalDistance(p1, p2));
        assertEquals(1, c.getLogicalDistance(p2, p1));
        assertEquals(2, c.getLogicalDistance(p1, p4));
        assertEquals(2, c.getLogicalDistance(p4, p1));
        assertEquals(3, c.getLogicalDistance(p3, p5));
        assertEquals(3, c.getLogicalDistance(p5, p3));
        assertEquals(-1, c.getLogicalDistance(p6, p3));
        assertEquals(3, c.getLogicalDistance(p7, p3));
        assertEquals(0, c.getLogicalDistance(p3, p3));

        assertEquals(3.74, c.getPhysicalDistance(p1, p3), 0.1);
        assertEquals(5.543, c.getPhysicalDistance(p6, p3), 0.1);
        assertEquals(3.46, c.getPhysicalDistance(p2, p4), 0.1);
        assertEquals(5.543, c.getPhysicalDistance(p3, p6), 0.1);
        assertEquals(Double.POSITIVE_INFINITY, c.getPhysicalDistance(p8, p6), 0.1);
        assertEquals(Double.POSITIVE_INFINITY, c.getPhysicalDistance(p8, p8), 0.1);
        assertEquals(Double.POSITIVE_INFINITY, c.getPhysicalDistance(p6, p8), 0.1);

        // test transit
        try {
            c.transit(null, 0, 1, 1);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
        assertFalse(c.transit(p1, 1, 100, 1));
        assertFalse(c.transit(p1, 1, -1, 1));
        assertFalse(c.transit(p1, 0, 1, -1));
        assertFalse(c.transit(p1, 0, 1, 10));
        assertFalse(c.transit(p7, 0, 1, 2));
        assertEquals(-1, c.NumOnTrack(10));
        c.transit(p6, 0, 3, 3);
        assertEquals(1, c.getPhysicalDistance(p5, p6), 0.1);

    }

    @Test
    public void testObserver() {
        CircularOrbit<User, PersonalApp> c = CircularOrbit.empty();
        List<HashSet<PersonalApp>> objs = c.getObjOnTracks();
        c.addInsideTrack(1);
        assertEquals(0, c.getObjNumOnTrack(1));
        Map<PersonalApp, Double> maps = c.getThetas();
        PersonalApp p1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
        PersonalApp p2 = PersonalAppFactory.getInstance("2", "2", "2", "2", "2");
        assertFalse(c.isEdgeBetween(p1, p2));
        assertFalse(c.containsObject(p1));
    }
}
