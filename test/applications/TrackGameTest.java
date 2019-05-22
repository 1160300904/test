package applications;

import static org.junit.Assert.*;
import org.junit.Test;

import appExceptions.FileSyntaxException;
import appExceptions.RepeatedObjectsException;
import centralObject.*;
import circularOrbit.CircularOrbit;
import physicalObject.*;
import track.*;

import java.io.*;
import java.util.*;

public class TrackGameTest {
  /*
   * Testing stragety: 1.setGamePlan: 1）group1，group2：超过已有组数，未超过已有组数，小于0
   * 2）tracknum1，tracknum2：是合法轨道数，不是合法轨道数，小于0 3）group1>group2，group1<group2,group1==group2
   * 4）tracknum1>tracknum2,tracknum1<tracknum2,tracknum1==tracknum2 5）交换的两个运动员是否为同一个
   * 2.islegalOneGroup 1）c:中心物体是否是null 2）c中轨道数是否为非法数字（是否在[4,10]之间）
   * 3）c中每个跑道上的运动员数量是否是合法的：每条跑道上只能有一名运动员 3.getAthleteNum 1）tracknum是否合法，是否在[4,10]区间内
   * 2）输入中是否存在不包含任何物体的轨道 4.noSameAthInBothOrbits 1）两轨道上是否有相同的物体： 2）两轨道上是否存在其中没有物体的情况
   */
  @Test
  public void testInitWithFile()
      throws FileNotFoundException, FileSyntaxException, RepeatedObjectsException {

    // assert false;

    ApplicationFactory tf = new TrackGameFactory();
    TrackGame t1 = (TrackGame) tf.getApplication();

    File f1 = new File("test\\testfile\\TrackGame\\TrackGameCorrect.txt");
    t1.initFromFile(f1);
    GamePlan gp = new RandomPlan();
    t1.arrangeGame(gp, 5);
    t1.setGamePlan(1, 1, 2, 2);
    assertFalse(t1.setGamePlan(-1, 1, 2, 2));
    assertFalse(t1.setGamePlan(1, 1, -2, 2));
    assertFalse(t1.setGamePlan(10, 1, 2, 2));
    assertFalse(t1.setGamePlan(1, 1, 20, 2));
    assertFalse(t1.setGamePlan(1, -1, 2, 2));
    assertFalse(t1.setGamePlan(1, 1, 2, -2));
    assertFalse(t1.setGamePlan(1, 10, 2, 2));
    assertFalse(t1.setGamePlan(1, 1, 2, 20));

    List<List<Athlete>> gamep = t1.getGamePlan();
    CircularOrbit<String, Athlete> c1 = CircularOrbit.empty();
    assertFalse(t1.isLegalOneGroup(c1));
    c1.setCentralObj("1");
    assertFalse(t1.isLegalOneGroup(c1));
    c1 = CircularOrbit.empty();
    for (int i = 1; i <= 15; i++) {
      c1.addInsideTrack(i);
    }
    assertFalse(t1.isLegalOneGroup(c1));
    c1 = CircularOrbit.empty();
    for (int i = 1; i <= 5; i++) {
      c1.addInsideTrack(i);
    }
    Athlete a1 = AthleteFactory.getInstance("1", 1, "AAA", 1, 11.11);
    Athlete a2 = AthleteFactory.getInstance("2", 2, "AAA", 2, 11.11);
    c1.addPhyToTrack(a1, 1, 1);
    assertTrue(t1.isLegalOneGroup(c1));
    c1.addPhyToTrack(a2, 10, 1);
    assertFalse(t1.isLegalOneGroup(c1));

    // get athlete number
    c1 = CircularOrbit.empty();
    for (int i = 1; i <= 5; i++) {
      c1.addInsideTrack(i);
    }
    c1.addPhyToTrack(a1, 1, 1);
    c1.addPhyToTrack(a2, 10, 2);
    assertEquals(2, t1.getAthleteNum(c1, 5));
    assertEquals(-1, t1.getAthleteNum(c1, -1));
    assertEquals(-1, t1.getAthleteNum(c1, 15));
    // get athlete on track
    HashSet<Athlete> athset = new HashSet<>();
    athset.add(a1);
    assertEquals(a1, t1.getAthOnTrack(athset));
    // no same athlete on tracks
    c1 = CircularOrbit.empty();
    CircularOrbit<String, Athlete> c2 = CircularOrbit.empty();
    for (int i = 1; i <= 5; i++) {
      c1.addInsideTrack(i);
      c2.addInsideTrack(i);
    }
    c1.addPhyToTrack(a1, 1, 1);
    c2.addPhyToTrack(a2, 1, 2);
    assertTrue(t1.noSameAthInBothOrbits(c1, c2, 5));
    c2.addPhyToTrack(a1, 1, 3);
    assertFalse(t1.noSameAthInBothOrbits(c1, c2, 5));

    // islegal
    List<CircularOrbit<String, Athlete>> clist = new ArrayList<>();
    assertFalse(t1.isLegal(clist));
    c1 = CircularOrbit.empty();
    c2 = CircularOrbit.empty();
    for (int i = 1; i <= 5; i++) {
      c1.addInsideTrack(i);
      c2.addInsideTrack(i);
    }
    Athlete a3 = AthleteFactory.getInstance("3", 3, "AAA", 3, 11.12);
    Athlete a4 = AthleteFactory.getInstance("4", 3, "AAA", 3, 11.12);
    Athlete a5 = AthleteFactory.getInstance("5", 3, "AAA", 3, 11.12);
    Athlete a6 = AthleteFactory.getInstance("6", 3, "AAA", 3, 11.12);
    Athlete a7 = AthleteFactory.getInstance("7", 3, "AAA", 3, 11.12);
    Athlete a8 = AthleteFactory.getInstance("8", 3, "AAA", 3, 11.12);
    Athlete a9 = AthleteFactory.getInstance("9", 3, "AAA", 3, 11.12);
    Athlete a10 = AthleteFactory.getInstance("10", 3, "AAA", 3, 11.12);
    Athlete a11 = AthleteFactory.getInstance("11", 3, "AAA", 3, 11.12);
    clist.add(c1);
    clist.add(c2);
    c1.addPhyToTrack(a1, 1, 1);
    c1.addPhyToTrack(a2, 1, 2);
    c1.addPhyToTrack(a3, 1, 3);
    c1.addPhyToTrack(a4, 1, 4);
    c2.addPhyToTrack(a6, 1, 1);
    assertFalse(t1.isLegal(clist));
    c1.addPhyToTrack(a5, 1, 5);
    assertTrue(t1.isLegal(clist));

    c2.addPhyToTrack(a7, 1, 2);
    c2.addPhyToTrack(a8, 1, 3);
    c2.addPhyToTrack(a9, 1, 4);
    c2.addPhyToTrack(a10, 1, 5);
    assertTrue(t1.isLegal(clist));
    c2.removePhy(a6, 1);
    c2.addPhyToTrack(a1, 1, 1);
    assertFalse(t1.isLegal(clist));

    /*
     * �˴�����assert�ⲻ�� c2.removePhy(a1, 1);c2.addPhyToTrack(a6, 1, 1);c2.addPhyToTrack(a11, 10,
     * 1); assertFalse(t1.isLegal(clist));
     */
    assertFalse(t1.isLegalForTwoOrbits(c1, c2));

    // other test.
    t1 = (TrackGame) tf.getApplication();
    File f2 = new File("test\\testfile\\TrackGame\\TrackGame_Medium.txt");
    t1.initFromFile(f2);
    gp = new SortedPlan();
    t1.arrangeGame(gp, 7);

    t1 = (TrackGame) tf.getApplication();
    File f3 = new File("test\\testfile\\TrackGame\\TrackGameCorrecteven.txt");
    t1.initFromFile(f3);
    gp = new SortedPlan();
    t1.arrangeGame(gp, 8);

    t1 = (TrackGame) tf.getApplication();
    File f4 = new File("test\\testfile\\TrackGame\\TrackGameCorrectNoLeft.txt");
    t1.initFromFile(f4);
    gp = new SortedPlan();
    t1.arrangeGame(gp, 6);

    // exceptions test
    t1 = (TrackGame) tf.getApplication();
    File file1 = new File("test\\testfile\\TrackGame\\AthBestScoreNotDouble.txt");

    try {
      t1.initFromFile(file1);
    } catch (Exception ex) {
      assert ex instanceof FileSyntaxException;
    }

    t1 = (TrackGame) tf.getApplication();
    File file2 = new File("test\\testfile\\TrackGame\\TrackGameRepeatedName.txt");

    try {
      t1.initFromFile(file2);
    } catch (Exception ex) {
      assert ex instanceof RepeatedObjectsException;
    }

    /*
     * t1.ArrangeGame(new SortedPlan(), 5); List<List<Athlete>> gameplan=t1.getGamePlan();
     * List<Athlete> gp=gameplan.get(0); assertEquals(1.0,gp.get(0).getBestScore(),0.001);
     * assertEquals(0.8,gp.get(1).getBestScore(),0.001);
     * assertEquals(0.6,gp.get(2).getBestScore(),0.001);
     * assertEquals(0.4,gp.get(3).getBestScore(),0.001);
     * assertEquals(0.3,gp.get(4).getBestScore(),0.001);
     * assertEquals(0.5,gp.get(5).getBestScore(),0.001);
     * assertEquals(0.7,gp.get(6).getBestScore(),0.001);
     * assertEquals(0.9,gp.get(7).getBestScore(),0.001);
     * 
     * gp=gameplan.get(1); assertNull(gp.get(0));assertNull(gp.get(1));assertNull(gp.get(2));
     * assertNull(gp.get(5));assertNull(gp.get(6));assertNull(gp.get(7));
     * assertEquals(0.1,gp.get(3).getBestScore(),0.001);
     * assertEquals(0.2,gp.get(4).getBestScore(),0.001);
     * 
     * t1.ArrangeGame(new RandomPlan(), 6);
     */

  }

  @Test
  public void testSetAndGetGamePlan()
      throws FileNotFoundException, FileSyntaxException, RepeatedObjectsException {
    /*
     * ApplicationFactory tf=new TrackGameFactory(); TrackGame t1=(TrackGame)tf.getApplication();
     * File f1=new File("src\\inputfile\\TrackGameForTest.txt"); t1.initFromFile(f1);
     * t1.ArrangeGame(new SortedPlan(), 8); assertFalse(t1.SetGamePlan(-1, 1, 1, 1));
     * assertFalse(t1.SetGamePlan(10, 1, 1, 1)); assertFalse(t1.SetGamePlan(1, -1, 1, 1));
     * assertFalse(t1.SetGamePlan(1, 10, 1, 1)); assertFalse(t1.SetGamePlan(1, 1, -1, 1));
     * assertFalse(t1.SetGamePlan(1, 1, 10, 1)); assertFalse(t1.SetGamePlan(1, 1, 1, -1));
     * assertFalse(t1.SetGamePlan(1, 1, 1, 10));
     * 
     * assertFalse(t1.SetGamePlan(1, 1, 2, 1)); assertFalse(t1.SetGamePlan(2, 1, 1, 1));
     * 
     * t1.SetGamePlan(1, 1, 1, 2); List<List<Athlete>> gameplan=t1.getGamePlan(); List<Athlete>
     * gp=gameplan.get(0); assertEquals(1.0,gp.get(1).getBestScore(),0.001);
     * assertEquals(0.8,gp.get(0).getBestScore(),0.001);
     * 
     * t1.SetGamePlan(1, 1, 2, 4); gameplan=t1.getGamePlan(); gp=gameplan.get(0);
     * assertEquals(0.1,gp.get(0).getBestScore(),0.001); gp=gameplan.get(1);
     * assertEquals(0.8,gp.get(3).getBestScore(),0.001);
     */
  }
}
