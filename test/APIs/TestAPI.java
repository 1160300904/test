package APIs;

import static org.junit.Assert.*;
import org.junit.Test;

import centralObject.*;
import circularOrbit.*;
import physicalObject.*;

public class TestAPI {
  /*
   * Testing strategy: 1.getObjectDistributionEntropy 1）输入的多轨道系统，所有物体是否排在一条轨道上 2）熵值结果==0,<1,>1
   * 2.getAtomDifference 1）二者轨道数的区别：第一个轨道少，第二个轨道少，两者一样多 2）两者轨道上物体数目的差别：第一个少，第二个少，两者一样多
   * 3）两者是否有上面一个物体也没有的轨道 3.getPersonalAppDifference 1）二者轨道数的区别：第一个轨道少，第二个轨道少，两者一样多
   * 2）两者轨道上物体数目的差别：第一个少，第二个少，两者一样多 3）两者是否有上面一个物体也没有的轨道 4）物体差异上：某条轨道上，第一条轨道上的物体全包含于第二条
   * 第一条轨道上的物体全包含于第二条 第一条轨道上的物体∩第二条的==空 第一条轨道上的物体∩第二条的!=空 4.getRaceGameDifference
   * 1）二者轨道数的区别：第一个轨道少，第二个轨道少，两者一样多 2）两者轨道上物体数目的差别：第一个少，第二个少，两者一样多 3）两者是否有上面一个物体也没有的轨道
   * 4）物体差异上：某条轨道上，第一条轨道上的物体全包含于第二条 第一条轨道上的物体全包含于第二条 第一条轨道上的物体∩第二条的==空 第一条轨道上的物体∩第二条的!=空
   * 5.getDifference 1）输入的轨道系统类型：原子结构/app生态系统/竞赛系统
   */
  @Test
  public void testgetObjectDistributionEntropy() {
    CircularOrbit<User, PersonalApp> c = CircularOrbit.empty();
    for (int i = 1; i <= 4; i++) {
      c.addInsideTrack(i);
    }
    CircularOrbitAPIs<User, PersonalApp> api = new CircularOrbitAPIs<>();
    PersonalApp p1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
    PersonalApp p2 = PersonalAppFactory.getInstance("2", "1", "1", "1", "1");
    PersonalApp p3 = PersonalAppFactory.getInstance("3", "1", "1", "1", "1");
    PersonalApp p4 = PersonalAppFactory.getInstance("4", "1", "1", "1", "1");
    PersonalApp p5 = PersonalAppFactory.getInstance("5", "1", "1", "1", "1");
    PersonalApp p6 = PersonalAppFactory.getInstance("6", "1", "1", "1", "1");

    c.addPhyToTrack(p3, -135, 3);
    c.addPhyToTrack(p6, 90, 3);
    assertEquals(0, api.getObjectDistributionEntropy(c), 0.01);
    c.addPhyToTrack(p2, 0, 2);
    assertEquals(0.63651, api.getObjectDistributionEntropy(c), 0.01);
    c.addPhyToTrack(p1, 90, 1);
    c.addPhyToTrack(p4, -60, 4);
    c.addPhyToTrack(p5, 0, 4);
    c.addPhyRelation(p1, p2);
    assertEquals(1.32966, api.getObjectDistributionEntropy(c), 0.01);

    assertEquals(2, api.getPhysicalDistance(c, p1, p6), 0.01);
    assertEquals(1, api.getLogicalDistance(c, p1, p2));

  }

  @Test
  public void testAtomDifference() {
    CircularOrbitAPIs<Nucleus, Electron> api = new CircularOrbitAPIs<>();
    CircularOrbit<Nucleus, Electron> c1 = CircularOrbit.empty();
    CircularOrbit<Nucleus, Electron> c2 = CircularOrbit.empty();
    for (int i = 1; i <= 3; i++) {
      c1.addInsideTrack(i);
      c2.addInsideTrack(i);
    }
    assertEquals(0, api.getAtomDifference(c1, c2).getTrackNumDiff());
    c1.addInsideTrack(4);
    assertEquals(1, api.getAtomDifference(c1, c2).getTrackNumDiff());
    c2.addInsideTrack(4);
    c2.addInsideTrack(5);
    assertEquals(-1, api.getAtomDifference(c1, c2).getTrackNumDiff());

    Electron e1 = ElectronFactory.getInstance(1);
    Electron e2 = ElectronFactory.getInstance(2);
    Electron e3 = ElectronFactory.getInstance(3);
    Electron e4 = ElectronFactory.getInstance(4);
    Electron e5 = ElectronFactory.getInstance(5);
    Electron e6 = ElectronFactory.getInstance(6);
    Electron e7 = ElectronFactory.getInstance(7);
    Electron e8 = ElectronFactory.getInstance(8);
    c1.addPhyToTrack(e1, 0, 1);
    c1.addPhyToTrack(e2, 0, 2);
    assertEquals(1, api.getAtomDifference(c1, c2).getObjNumDiff(1));
    c2.addPhyToTrack(e3, 0, 1);
    c2.addPhyToTrack(e4, 0, 2);
    assertEquals(0, api.getAtomDifference(c1, c2).getObjNumDiff(1));
    c2.addPhyToTrack(e5, 0, 2);
    assertEquals(-1, api.getAtomDifference(c1, c2).getObjNumDiff(2));
    c1.addPhyToTrack(e6, 0, 2);
    c1.addPhyToTrack(e7, 0, 2);
    assertEquals(1, api.getAtomDifference(c1, c2).getObjNumDiff(2));
    Nucleus u1 = NucleusFactory.getInstance("A");
    Nucleus u2 = NucleusFactory.getInstance("B");
    c1.setCentralObj(u1);
    c2.setCentralObj(u2);
    api.getDifference(c1, c2);

  }

  @Test
  public void testAppDifference() {
    CircularOrbitAPIs<User, PersonalApp> api = new CircularOrbitAPIs<>();
    CircularOrbit<User, PersonalApp> c1 = CircularOrbit.empty();
    CircularOrbit<User, PersonalApp> c2 = CircularOrbit.empty();
    for (int i = 1; i <= 3; i++) {
      c1.addInsideTrack(i);
      c2.addInsideTrack(i);
    }
    assertEquals(0, api.getPersonalAppDifference(c1, c2).getTrackNumDiff());
    // app ����circular orbit�����Ӧ��ͬ
    c1.addInsideTrack(4);
    assertNull(api.getPersonalAppDifference(c1, c2));
    c1.deleteTrack(4);
    /*
     * c2.addInsideTrack(4);c2.addInsideTrack(5); assertEquals(-1,api.getPersonalAppDifference(c1,
     * c2).getTrackNumDiff());
     */

    PersonalApp e1 = PersonalAppFactory.getInstance("1", "1", "1", "1", "1");
    PersonalApp e2 = PersonalAppFactory.getInstance("2", "1", "1", "1", "1");
    PersonalApp e3 = PersonalAppFactory.getInstance("3", "1", "1", "1", "1");
    PersonalApp e4 = PersonalAppFactory.getInstance("4", "1", "1", "1", "1");
    PersonalApp e5 = PersonalAppFactory.getInstance("5", "1", "1", "1", "1");
    PersonalApp e6 = PersonalAppFactory.getInstance("6", "1", "1", "1", "1");
    PersonalApp e7 = PersonalAppFactory.getInstance("7", "1", "1", "1", "1");
    PersonalApp e8 = PersonalAppFactory.getInstance("8", "1", "1", "1", "1");
    c1.addPhyToTrack(e1, 0, 1);
    c1.addPhyToTrack(e2, 0, 2);
    assertEquals(1, api.getPersonalAppDifference(c1, c2).getObjNumDiff(1));
    c2.addPhyToTrack(e3, 0, 1);
    c2.addPhyToTrack(e4, 0, 2);
    assertEquals(0, api.getPersonalAppDifference(c1, c2).getObjNumDiff(1));
    c2.addPhyToTrack(e5, 0, 2);
    assertEquals(-1, api.getPersonalAppDifference(c1, c2).getObjNumDiff(2));
    c1.addPhyToTrack(e6, 0, 2);
    c1.addPhyToTrack(e7, 0, 2);
    assertEquals(1, api.getPersonalAppDifference(c1, c2).getObjNumDiff(2));

    assertEquals("{1}-{3}", api.getPersonalAppDifference(c1, c2).getObjDetailDiff(1));
    assertEquals("{2,6,7}-{4,5}", api.getPersonalAppDifference(c1, c2).getObjDetailDiff(2));
    c1.addPhyToTrack(e8, 0, 3);
    assertEquals("{8}-{ }", api.getPersonalAppDifference(c1, c2).getObjDetailDiff(3));
    assertEquals("{ }-{8}", api.getPersonalAppDifference(c2, c1).getObjDetailDiff(3));
    c1.addPhyToTrack(e4, 0, 2);
    c1.addPhyToTrack(e5, 0, 2);
    c2.addPhyToTrack(e6, 0, 2);
    assertEquals("{ }-{2,7}", api.getPersonalAppDifference(c2, c1).getObjDetailDiff(2));
    User u1 = UserFactory.getInstance("1");
    User u2 = UserFactory.getInstance("2");
    c1.setCentralObj(u1);
    c2.setCentralObj(u2);
    api.getDifference(c1, c2);
  }

  @Test
  public void testgetRaceGameDifference() {
    CircularOrbitAPIs<String, Athlete> api = new CircularOrbitAPIs<>();
    CircularOrbit<String, Athlete> c1 = CircularOrbit.empty();
    CircularOrbit<String, Athlete> c2 = CircularOrbit.empty();
    for (int i = 1; i <= 3; i++) {
      c1.addInsideTrack(i);
      c2.addInsideTrack(i);
    }
    assertEquals(0, api.getRaceGameDifference(c1, c2).getTrackNumDiff());
    // app ����circular orbit�����Ӧ��ͬ
    c1.addInsideTrack(4);
    assertNull(api.getRaceGameDifference(c1, c2));
    c2.addInsideTrack(4);
    /*
     * c2.addInsideTrack(4);c2.addInsideTrack(5); assertEquals(-1,api.getPersonalAppDifference(c1,
     * c2).getTrackNumDiff());
     */

    Athlete e1 = AthleteFactory.getInstance("1", 1, "AAA", 1, 11.11);
    Athlete e2 = AthleteFactory.getInstance("2", 1, "AAA", 1, 11.11);
    Athlete e3 = AthleteFactory.getInstance("3", 1, "AAA", 1, 11.11);
    Athlete e4 = AthleteFactory.getInstance("4", 1, "AAA", 1, 11.11);
    Athlete e5 = AthleteFactory.getInstance("5", 1, "AAA", 1, 11.11);
    Athlete e6 = AthleteFactory.getInstance("6", 1, "AAA", 1, 11.11);
    Athlete e7 = AthleteFactory.getInstance("7", 1, "AAA", 1, 11.11);
    Athlete e8 = AthleteFactory.getInstance("8", 1, "AAA", 1, 11.11);
    c1.addPhyToTrack(e1, 0, 1);
    c1.addPhyToTrack(e2, 0, 2);
    assertEquals(1, api.getRaceGameDifference(c1, c2).getObjNumDiff(1));
    c2.addPhyToTrack(e3, 0, 1);
    c2.addPhyToTrack(e4, 0, 2);
    assertEquals(0, api.getRaceGameDifference(c1, c2).getObjNumDiff(1));
    // c2.addPhyToTrack(e5, 0, 2);
    // assertEquals(-1,api.getRaceGameDifference(c1, c2).getObjNumDiff(2));
    // c1.addPhyToTrack(e6, 0, 2);c1.addPhyToTrack(e7, 0, 2);
    // assertEquals(1,api.getRaceGameDifference(c1, c2).getObjNumDiff(2));

    assertEquals("1-3", api.getRaceGameDifference(c1, c2).getObjDetailDiff(1));
    // System.out.println(api.getRaceGameDifference(c1, c2).getObjDetailDiff(2));
    // assertEquals("6-4",api.getRaceGameDifference(c1, c2).getObjDetailDiff(2));
    c1.addPhyToTrack(e8, 0, 3);
    assertEquals("8-None", api.getRaceGameDifference(c1, c2).getObjDetailDiff(3));
    assertEquals("None-8", api.getRaceGameDifference(c2, c1).getObjDetailDiff(3));
    c1.addPhyToTrack(e5, 0, 4);
    c2.addPhyToTrack(e5, 0, 4);
    assertEquals("None", api.getRaceGameDifference(c2, c1).getObjDetailDiff(4));
    api.getDifference(c1, c2);
  }
}
