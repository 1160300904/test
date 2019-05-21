package errorHandling;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestTrackGameHandler {

	/*Testing strategy:
	 * 	1.handleAthlete			1������Ĵ������ͣ���Ϣû��<,>������/��Ϣȱʧ/�˶�Ա������ʽ����/
	 * 								�˶�Ա������Ϣ����������д��ĸ/����ͺ��벻��������/��óɼ�������λС��
	 * 	2.handleGame			1������Ĵ������ͣ���Ϣ����'='����/�������Ʋ�������/��������100��200��400
	 * 	3.handleNumOfTracks		1������Ĵ������ͣ���Ϣ����'='����/�����Ŀ��������/�����Ŀ����[4,10]֮��
	 */
	@Test
	public void testTrackGame() {
		TrackGameHandler h=new TrackGameHandler();
		assertNull(h.FilesyntaxHandling(null));
		assertNull(h.FilesyntaxHandling("a"));
	}
	
	@Test
	public void testAthlete() {
		TrackGameHandler h=new TrackGameHandler();
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38>");
		h.FilesyntaxHandling("Athlete ::= <Bo#lt,1,JAM,38,9.94>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JABM,38,9.94>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1.0,JAM,38,9.94>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38.0,9.94>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.9>");
		h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,nine>");
		
	}
	
	@Test
	public void testGame() {
		TrackGameHandler h=new TrackGameHandler();
		h.FilesyntaxHandling("Game ::= 100");
		h.FilesyntaxHandling("Game ::= 200");
		h.FilesyntaxHandling("Game ::= 400");
		h.FilesyntaxHandling("Game ::100");
		h.FilesyntaxHandling("Game ::= one hundred");
		h.FilesyntaxHandling("Game ::= 1000");
	}
	
	@Test
	public void testTrack() {
		TrackGameHandler h=new TrackGameHandler();
		h.FilesyntaxHandling("NumOfTracks ::= 5 ");
		h.FilesyntaxHandling("NumOfTracks :: 5 ");
		h.FilesyntaxHandling("NumOfTracks ::= five");
		h.FilesyntaxHandling("NumOfTracks ::= 15");
		h.FilesyntaxHandling("NumOfTracks ::= 1");
	}
}
