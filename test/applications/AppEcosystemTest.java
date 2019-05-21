package applications;
import static org.junit.Assert.*;
import org.junit.Test;

import appExceptions.FileInfoConflictException;
import appExceptions.FileSyntaxException;
import appExceptions.RepeatedObjectsException;
import centralObject.User;
import circularOrbit.CircularOrbit;
import physicalObject.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class AppEcosystemTest {

	/*Testing Strategy:	
	 * 	1.isRelated:		1��ecosystem���Ƿ���������ַ���
	 * 						2�������ַ����Ƿ���ecosystem���й�ϵ
	 * (3)PersonalAppEcosystem��
			��boolean initFromFile(File file) 
				1)�����ļ����Ƿ����һ���쳣���﷨�����ļ���Ϣ��ì�ܣ������ظ�����
			�� boolean addRelation(String s1,String s2)
				1)S1,s2�Ƿ���ͬ
				2)S1��s2�Ƿ��Ѿ������Ź�ϵ
				3)S1��s2�Ƿ�����˲����������ļ��е�app
			�� boolean isRelated(String s1,String s2) 
				1)S1,s2�Ƿ���ͬ
				2)S1��s2�Ƿ��Ѿ������Ź�ϵ
				3)S1��s2�Ƿ�����˲����������ļ��е�app
			��int getMinMyDate(int i,int j,int k,int is,int js,int ks)
				1)�Ƿ������Ҫȡ��Ԫ�ص�����������Ԫ���б��size�����
				2)������Ϣ���Ƿ���ڸ���
				3)��������ȵĸ���
			��boolean synthensisLegal(CircularOrbit<User, PersonalApp> c,String appname1,String appname2,int orbitlistindex)
				1)�����������ϵͳ�Ƿ�Ϸ�
				2)���������app�����Ƿ񲻴����������ϵͳ��
				3)����app�����Ƿ���ͬ
				4)���ϵͳ���б��е������Ƿ񳬽�
	 */
	@Test
	public void appEcosystemTest() throws FileNotFoundException,
			FileSyntaxException, RepeatedObjectsException, FileInfoConflictException {
		ApplicationFactory af=new AppEcosystemFactory();
		PersonalAppEcosystem as=(PersonalAppEcosystem)af.getApplication();
		File file=new File("test\\testfile\\appEcosystem\\PersonalAppEcosystem.txt");
		as.initFromFile(file);
		

		assertEquals(1,as.getMinMyDate(1, 1, 15, 6, 2, 12));
		assertEquals(2,as.getMinMyDate(5, 1, 15, 6, 2, 12));
		assertEquals(3,as.getMinMyDate(5, 10, 0, 6, 2, 12));
		assertEquals(2,as.getMinMyDate(15, 0, 11, 6, 2, 12));
		assertEquals(3,as.getMinMyDate(15, 1, 0, 6, 2, 12));
		assertEquals(1,as.getMinMyDate(1, 11, 21, 6, 2, 12));
		assertEquals(2,as.getMinMyDate(15, 1, 20, 6, 2, 12));
		assertEquals(3,as.getMinMyDate(15, 11, 0, 6, 2, 12));
		System.out.println(as.getMinMyDate(0,8,15,6, 2, 12));
		as.getMinMyDate(0, 1, 6,6, 2, 12);
		as.ArrangeEcos();
		
		assertFalse(as.isRelated("ss", "gg"));
		assertFalse(as.isRelated("Wechat", "gg"));
		assertTrue(as.isRelated("Wechat", "QQ"));
		Map<String,HashSet<String>> map1=as.getRelationMap();
		List<List<AppUseNote>> list1=as.getEcos();
		List<MyDate> list2=as.getPeriodList();
		Map<String,PersonalApp> map2=as.getUseNoteToAppMap();
		String str1=as.getUserName();
		
		CircularOrbit<User,PersonalApp> c1=CircularOrbit.empty();
		for(int i=1;i<=4;i++) {
			c1.addInsideTrack(i);
		}
		PersonalApp p1=PersonalAppFactory.getInstance("Wechat","Tencent","13.2",
				"The most popular social networking App in China","Social network");
		PersonalApp p2=PersonalAppFactory.getInstance("QQ","Tencent","29.2",
				"The second popular social networking App in China","Social network");
		PersonalApp p3=PersonalAppFactory.getInstance("Weibo","Sina","v0.2.3.4",
				"The third popular social networking App in China","Social network");
		PersonalApp p4=PersonalAppFactory.getInstance("Didi","Didi","ver03.32"
				,"The most popular car sharing App in China","Travel");
		PersonalApp p5=PersonalAppFactory.getInstance("Eleme","Eleme","20V0.03",
				"The most popular online food ordering App in China","Food");
		PersonalApp p6=PersonalAppFactory.getInstance("BaiduMap","Baidu","2.9000000.20v03"
				,"The most popular map App in China","Travel");
		
		c1.addPhyToTrack(p2, 0, 1);
		c1.addPhyToTrack(p1, 10, 3);
		c1.addPhyToTrack(p5, 20, 3);
		c1.addPhyToTrack(p3, 10, 4);
		c1.addPhyToTrack(p4, 20, 4);
		c1.addPhyToTrack(p6, 30, 2);
		assertTrue(as.synthensisLegal(c1, "QQ", "Wechat", 2));
		assertTrue(as.synthensisLegal(c1, "Eleme", "Wechat", 2));
		assertTrue(as.synthensisLegal(c1, "Didi", "Wechat", 2));
		assertFalse(as.synthensisLegal(c1, "BaiduMap", "Wechat", 2));
		assertFalse(as.synthensisLegal(c1, "Wechat", "BaiduMap", 2));
		
		
		HashSet<PersonalApp> set=new HashSet<>();
		set.add(p1);set.add(p2);
		PersonalApp p61=PersonalAppFactory.getInstance("Wechat","Tencent","13.2",
				"The most popular social networking App in China","Social network");
		System.out.println(set.contains(p61));
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file1=new File("test\\testfile\\appEcosystem\\AppBusinessAreaWrong.txt");
		
		try{
			as.initFromFile(file1);
		}catch(Exception ex) {
			assert ex instanceof FileSyntaxException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file2=new File("test\\testfile\\appEcosystem\\installTwice.txt");
		try{
			as.initFromFile(file2);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file3=new File("test\\testfile\\appEcosystem\\DuplicatedApp.txt");
		try{
			as.initFromFile(file3);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof RepeatedObjectsException;
		}
		 
		as=(PersonalAppEcosystem)af.getApplication();
		File file4=new File("test\\testfile\\appEcosystem\\InstallUnDefinedApp.txt");
		try{
			as.initFromFile(file4);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file5=new File("test\\testfile\\appEcosystem\\UninstallUndefinedApp.txt");
		try{
			as.initFromFile(file5);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file6=new File("test\\testfile\\appEcosystem\\UninstallUnexistedApp.txt");
		try{
			as.initFromFile(file6);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file7=new File("test\\testfile\\appEcosystem\\UseUnDefinedApp.txt");
		try{
			as.initFromFile(file7);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file8=new File("test\\testfile\\appEcosystem\\UseUnexistedApp.txt");
		try{
			as.initFromFile(file8);
			as.ArrangeEcos();
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
		
		as=(PersonalAppEcosystem)af.getApplication();
		File file9=new File("test\\testfile\\appEcosystem\\PersonalAppEcosystem_Medium.txt");
		as.initFromFile(file9);
		as.ArrangeEcos();
		
		LogElement l=new LogElement("1",new MyDate(1,1,1,1,1,1));
		assertEquals("1: 0001-01-01T01:01:01",l.toString());
	}
}
