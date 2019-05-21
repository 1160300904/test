package errorHandling;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestAppHandler {
	/*Testing strategy:
	 * 	1.FilesyntaxHandling			1��������ַ�������������Ϣ��App/User/Period/InstallLog/UninstallLog
	 * 											UsageLog/Relation
	 * 	2.handleRelation				1������Ĵ������ͣ���Ϣû�б�<,>������/����һ����û��app/����һ��app������
	 * 														Ϊ�Ƿ���
	 * 	3.handleUseLog					1������Ĵ������ͣ���Ϣû�б�<,>������/������Ϣȱʧ/һ��app������
	 * 														Ϊ�Ƿ���/ʱ��ĸ�ʽ����/���е�ʱ������һ������
	 * 	4.handleUnInstallLog			1������Ĵ������ͣ���Ϣû�б�<,>������/������Ϣȱʧ/һ��app������
	 * 														Ϊ�Ƿ���/ʱ��ĸ�ʽ����/���е�ʱ������һ������
	 * 	5.handleInstallLog				1������Ĵ������ͣ���Ϣû�б�<,>������/������Ϣȱʧ/һ��app������
	 * 														Ϊ�Ƿ���/ʱ��ĸ�ʽ����/���е�ʱ������һ������
	 * 	6.handleApp						1������Ĵ������ͣ���Ϣû�б�<,>������/������Ϣȱʧ/һ��app������
	 * 														Ϊ�Ƿ���/app��˾��Ϣ�Ƿ�/app�汾��Ϣ�Ƿ�/app������Ϣ�Ƿ�
	 * 														app��ҵ������Ϣ�Ƿ�
	 * 	7.handleUser					1������Ĵ������ͣ���Ϣû��'-'����/�������û���/�û������пո�/�û������������Ƿ��ַ�
	 * 	8.handlePeriod					1������Ĵ������ͣ���Ϣû��'-'����/�����޷ָ���Ϣ
	 * 										/period�в���Hour|Day|Week|Month�е�һ��
	 * 														
	 */		
	@Test
	public void testappHandlerNull() {
		PersonalAppHandler h=new PersonalAppHandler();
		assertNull(h.FilesyntaxHandling(null));
		
	}
	
	@Test
	public void testRelation() {
		PersonalAppHandler h=new PersonalAppHandler();
		h.FilesyntaxHandling("Relaions ::= <Wechat1,QQ+>");
		h.FilesyntaxHandling("Relation ::= <Wechat1,QQ+>");
		h.FilesyntaxHandling("Relation ::= <Wechat1+,QQ>");
		h.FilesyntaxHandling("Relation ::= Wechat1,QQ");
		h.FilesyntaxHandling("Relation ::= <Wechat1>");
		h.FilesyntaxHandling("Relation ::= <Wechat1,QQ>");
	}
	
	@Test
	public void testhandleUseLog() {
		PersonalAppHandler h=new PersonalAppHandler();
		
		h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat,2>");
		h.FilesyntaxHandling("UsageLog ::= 2019-01-01,15:00:00,Wechat,2");
		h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat>");
		h.FilesyntaxHandling("UsageLog ::= <2019=01=01,15:00:00,Wechat,2>");
		h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15.00.00,Wechat,2>");
		h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,We+chat,2>");
		h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat,-2>");
	}
	
	@Test
	public void testhandleUnInstallLog() {
		PersonalAppHandler h=new PersonalAppHandler();
		
		h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28,Didi>");
		h.FilesyntaxHandling("UninstallLog ::= 2019-01-02,13:00:28,Didi>");
		h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28>");
		h.FilesyntaxHandling("UninstallLog ::= <2019=01-02,13:00:28,Didi>");
		h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13.00:28,Didi>");
		h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28,Didi+>");
	}
	
	@Test
	public void testhandleInstallLog() {
		PersonalAppHandler h=new PersonalAppHandler();
		
		h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap>");
		h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap");
		h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30>");
		h.FilesyntaxHandling("InstallLog ::= <2019=01-02,13:19:30,BaiduMap>");
		h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13.19:30,BaiduMap>");
		h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap+>");
	}
	
	
	@Test
	public void testhandleApp() {
		PersonalAppHandler h=new PersonalAppHandler();
		
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\"");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\">");
		h.FilesyntaxHandling("App ::= <Didi+,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi~+,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.3~!2,\"The most popular car sharing App in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,The most popular car sharing App in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China,\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App!+ in China\",\"Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",Travel\">");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel>");
		h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Tra%$vel\">");
	}
	
	@Test
	public void testhandleUsername() {
		PersonalAppHandler h=new PersonalAppHandler();
		
		h.FilesyntaxHandling("User ::= TimWong");
		h.FilesyntaxHandling("User :: TimWong");
		h.FilesyntaxHandling("User ::= Tim Wong");
		h.FilesyntaxHandling("User ::= ");
		h.FilesyntaxHandling("User ::= Tim!$%Wong");
	}
	
	@Test
	public void testhandlePeriod() {
		PersonalAppHandler h=new PersonalAppHandler();

		h.FilesyntaxHandling("Period ::= Day");
		h.FilesyntaxHandling("Period ::= Hour");
		h.FilesyntaxHandling("Period ::= Month");
		h.FilesyntaxHandling("Period ::= Week");
		h.FilesyntaxHandling("Period ::= Minute");
		h.FilesyntaxHandling("Period ::= ");
		h.FilesyntaxHandling("Period ::");
		h.FilesyntaxHandling("Period ::= Da##y");
	}
}
