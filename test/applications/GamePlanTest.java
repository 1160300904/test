package applications;
import static org.junit.Assert.*;
import org.junit.Test;

import physicalObject.*;
import java.util.*;
public class GamePlanTest {

	/*Testing strategy
	 * 	1.RandomPlan
	 * 		①List<List<Athlete>> plan(List<Athlete> athletes,int tracknum)
				1)输入运动员列表是否有重复运动员
				2)输入运动员列表中是否正好为轨道数目的整数倍
				3)轨道数目是否合法，在【4，10】之间
		2.sortedPlan
			①List<List<Athlete>> plan(List<Athlete> athletes, int tracknum)
				1)输入运动员列表是否有重复运动员
				2)输入运动员列表中是否正好为轨道数目的整数倍
				3)轨道数目是否合法，在【4，10】之间
				4)运动员是否已经以最好成绩排好序
	 */
	@Test
	public void testRandomPlan() {
		GamePlan gp=new RandomPlan();
		int athnum=10;
		List<Athlete> alist=new ArrayList<>();List<List<Athlete>> retlist=new ArrayList<>();
		for(int i=1;i<=athnum;i++) {
			alist.add(AthleteFactory.getInstance(String.valueOf(i), 1, "1", 1, 1));
		}
		try {
			gp.plan(alist, 1);
		}catch(Exception ex) {
			assertTrue(ex instanceof IllegalArgumentException);
		}
		try {
			gp.plan(alist, 19);
		}catch(Exception ex) {
			assertTrue(ex instanceof IllegalArgumentException);
		}
		retlist=gp.plan(alist, 7);
		assertEquals(7,retlist.get(0).size());assertEquals(3,retlist.get(1).size());
		assertTrue(retlist.get(0).get(0).equals(alist.get(0)));
		assertTrue(retlist.get(0).get(1).equals(alist.get(1)));
		assertTrue(retlist.get(0).get(6).equals(alist.get(6)));
		assertTrue(retlist.get(1).get(0).equals(alist.get(7)));
		assertTrue(retlist.get(1).get(1).equals(alist.get(8)));
		assertTrue(retlist.get(1).get(2).equals(alist.get(9)));
		try {
			retlist.get(1).get(3);
		}catch(Exception ex) {
			assertTrue(ex instanceof IndexOutOfBoundsException);
		}
		athnum=14;
		List<Athlete> alist1=new ArrayList<>();List<List<Athlete>> retlist1=new ArrayList<>();
		for(int i=1;i<=athnum;i++) {
			alist1.add(AthleteFactory.getInstance(String.valueOf(i), 1, "1", 1, 1));
		}
		retlist1=gp.plan(alist1, 7);
		assertEquals(7,retlist1.get(1).size());
		assertEquals(7,retlist1.get(0).size());
		
	}
	
	@Test
	public void testSortedPlan() {
		GamePlan gp=new SortedPlan();
		int athnum=10;
		List<Athlete> alist=new ArrayList<>();List<List<Athlete>> retlist=new ArrayList<>();
		for(int i=1;i<=athnum;i++) {
			alist.add(AthleteFactory.getInstance(String.valueOf(i), 1, "1", 1, i*0.1));
		}
		retlist=gp.plan(alist, 8);
		List<Athlete> retlist0=retlist.get(0);List<Athlete> retlist1=retlist.get(1);
		assertEquals(1.0,retlist0.get(0).getBestScore(),0.001);
		assertEquals(0.80,retlist0.get(1).getBestScore(),0.001);
		assertEquals(0.60,retlist0.get(2).getBestScore(),0.001);
		assertEquals(0.40,retlist0.get(3).getBestScore(),0.001);
		assertEquals(0.30,retlist0.get(4).getBestScore(),0.001);
		assertEquals(0.50,retlist0.get(5).getBestScore(),0.001);
		assertEquals(0.70,retlist0.get(6).getBestScore(),0.001);
		assertEquals(0.90,retlist0.get(7).getBestScore(),0.001);
		assertEquals(0.10,retlist1.get(3).getBestScore(),0.001);
		assertEquals(0.20,retlist1.get(4).getBestScore(),0.001);
		
		athnum=2;
		List<Athlete> alist1=new ArrayList<>();List<List<Athlete>> retlist11=new ArrayList<>();
		for(int i=1;i<=athnum;i++) {
			alist1.add(AthleteFactory.getInstance(String.valueOf(i), 1, "1", 1, i*0.1));
		}
		
		retlist11=gp.plan(alist1, 3);
		retlist0=retlist11.get(0);
		
		assertNull(retlist0.get(0));
		assertEquals(0.10,retlist0.get(1).getBestScore(),0.001);
		assertEquals(0.20,retlist0.get(2).getBestScore(),0.001);
	}
}
