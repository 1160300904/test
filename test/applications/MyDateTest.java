package applications;

import static org.junit.Assert.*;
import org.junit.Test;

import physicalObject.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MyDateTest {

	/*Testing Strategy:
	 * 	1.����йصĵĺ�����	1)���룺0��>0;
	 * 						2)�����ʱ�䣺�Ƿ�����ֵ�󣬼����Ƿ���Ҫ��λ��
	 * 	2.��׶��йصĺ�����	1�����ضϵ�ʱ����ָ�������»��Ƿ���Ҫ���ضϣ�������ʱ��Ϊ1999:01:01  13:00:00
	 * 							��Ҫ��ضϵ����ӡ�
	 * 	3.observer������		1����ȡ��ʱ�����͵�ʱ��ֵ��0, ��0.
	 * 	4.getmin������		1���ĸ�ʱ����ǰ����һ�����ڶ�����������
	 * 						2����������ʱ����ͬ�ĸ�����һ��������������
	 * 	5.TruncBaseOnSplit��	1���ضϵĲ��ԣ� Hour|Day|Week|Month 
	 * 						2���Ƿ��Լ�����ضϵ�Ҫ���ˣ���������֮�󣬷��ص�MyDate�����ԭ��һ����
	 * 	6.addBaseOnSplit:	1�����ӵĲ��ԣ� Hour|Day|Week|Month 
	 * 						2��ԭ���������Ӻ��Ƿ���Ҫ��λ
	 * 						3������Ĳ����Ƿ��� Hour|Day|Week|Month �С�
	 * 	7.equals:			1��������ȣ������
	 * 						2����������������Ƿ�Ϊͬһ������
	 * 						3�����������Ƿ�Ϊͬһ�����͡�
	 */
	
	@Test
	public void testEqualsAndToString() {
		MyDate d1=new MyDate(1,2,3,4,5,6);
		MyDate d2=new MyDate(1,2,3,4,5,6);
		MyDate d3=new MyDate(1,2,4,4,5,6);
		String s1="sds";
		assertFalse(d1.equals(s1));
		assertTrue(d1.equals(d2));
		assertTrue(d1.equals(d1));
		assertFalse(d1.equals(d3));
		
		assertEquals("0001-02-03T04:05:06",d1.toString());
	}
	
	@Test
	public void testAddBaseOnSplit() {
		MyDate d1=new MyDate(1,2,3,4,5,6);
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,5,5,6)),
				MyDate.addBaseOnSplit("Hour", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,4,4,5,6)),
				MyDate.addBaseOnSplit("Day", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,10,4,5,6)),
				MyDate.addBaseOnSplit("Week", d1));
		assertEquals(new MyDate(LocalDateTime.of(2,1,3,5,5,6)),
				MyDate.addBaseOnSplit("Month", new MyDate(LocalDateTime.of(1,12,3,5,5,6))));
		assertNull(MyDate.addBaseOnSplit("Second", d1));
	}
	@Test
	public void testTruncBaseOnSplit() {
		MyDate d1=new MyDate(1,2,3,4,5,6);
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,0,0)),
						MyDate.TruncBaseOnSplit("Hour", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,0,0,0)),
						MyDate.TruncBaseOnSplit("Month", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,0,0,0)),
				MyDate.TruncBaseOnSplit("Day", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,0,0,0)),
				MyDate.TruncBaseOnSplit("Week", d1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,0,0,0)),
				MyDate.TruncBaseOnSplit("Week", new MyDate(LocalDateTime.of(1,2,3,0,0,0))));
		assertNull(MyDate.TruncBaseOnSplit("Second", d1));
	}
	@Test
	public void testAddAndTrunc() {
		MyDate d1=new MyDate(1,2,3,4,5,6);
		MyDate d2=new MyDate(2,2,3,4,5,6);
		MyDate d3=new MyDate(1,3,3,4,5,6);
		MyDate d4=new MyDate(1,2,4,4,5,6);
		MyDate d5=new MyDate(1,2,3,5,5,6);
		MyDate d6=new MyDate(1,2,3,4,6,6);
		MyDate d7=new MyDate(1,2,3,4,5,7);
		MyDate d8=new MyDate(LocalDateTime.of(1,2,3,4,5,6));
		
		assertEquals(new MyDate(LocalDateTime.of(2,2,3,4,5,6)),d1.addYear(1));
		assertEquals(new MyDate(LocalDateTime.of(1,3,3,4,5,6)),d1.addMonth(1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,4,4,5,6)),d1.addDay(1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,5,5,6)),d1.addHour(1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,6,6)),d1.addMinute(1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,5,7)),d1.addSecond(1));
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,6,6)),d1.addSecond(60));
		
		assertEquals(d1,d1.addDay(0));
		assertEquals(d1,d1.addHour(0));
		assertEquals(d1,d1.addMinute(0));
		assertEquals(d1,d1.addMonth(0));
		assertEquals(d1,d1.addSecond(0));
		assertEquals(d1,d1.addYear(0));
		
		
		
		
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,5,6)),d1.truncSecond());
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,5,0)),d1.truncMinute());
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,4,0,0)),d1.truncHour());
		assertEquals(new MyDate(LocalDateTime.of(1,2,3,0,0,0)),d1.truncDay());
		
	}
	@Test
	public void testGetter() {
		MyDate d1=new MyDate(1,1,1,1,1,1);
		assertEquals(1,d1.getDay());
		assertEquals(1,d1.getHour());
		assertEquals(1,d1.getMinute());
		assertEquals(1,d1.getMonth());
		assertEquals(1,d1.getSecond());
		assertEquals(1,d1.getYear());
	}
	@Test
	public void testGetMin() {
		//d1<d2
		MyDate d1=new MyDate(1,2,3,4,5,6);
		MyDate d2=new MyDate(2,2,3,4,5,6);
		MyDate d3=new MyDate(3,3,3,4,5,6);
		MyDate d4=new MyDate(1,2,4,4,5,6);
		//d5==d6
		MyDate d5=new MyDate(1,2,3,4,5,6);
		MyDate d6=new MyDate(1,2,3,4,5,6);

		assertEquals(1,MyDate.getMin(d1, d2, d3));
		assertEquals(2,MyDate.getMin(d2, d1, d3));
		assertEquals(3,MyDate.getMin(d3, d2, d1));
		assertEquals(3,MyDate.getMin(d2, d3, d1));
		assertEquals(1,MyDate.getMin(d1, d3, d3));
	}
	@Test
	public void testCompare() {
		MyDate d1=new MyDate(1,2,3,4,5,6);
		MyDate d2=new MyDate(2,2,3,4,5,6);
		MyDate d3=new MyDate(1,3,3,4,5,6);
		MyDate d4=new MyDate(1,2,4,4,5,6);
		MyDate d5=new MyDate(1,2,3,5,5,6);
		MyDate d6=new MyDate(1,2,3,4,6,6);
		MyDate d7=new MyDate(1,2,3,4,5,7);
		MyDate d8=new MyDate(LocalDateTime.of(1,2,3,4,5,6));
		
		assertTrue(d1.compareTo(d2)<0);
		assertTrue(d1.compareTo(d3)<0);
		assertTrue(d1.compareTo(d4)<0);
		assertTrue(d1.compareTo(d5)<0);
		assertTrue(d1.compareTo(d6)<0);
		assertTrue(d1.compareTo(d7)<0);
		assertTrue(d1.compareTo(d8)==0);
		assertTrue(d1.compareTo(d1)==0);
	}
	
}
