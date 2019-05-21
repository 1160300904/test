package errorHandling;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestAtomHandler {

	/*Testing strategy:
	 * 1.FilesyntaxHandling			1�������Ƿ�Ϊnull
	 * 								2�������а�����NumberOfElectron/ElementName/NumberOfTracks/����
	 * 2.handleNumberOfElectron		1�������еĴ������ͣ������ʽ����/���������ĸ�ʽ�������﷨
	 * 3.handleElementName			1���������Ƿ��д���
	 * 								2������Ĵ������ͣ���Ԫ������/���ʽ����'='����/Ԫ��������ĸ��ʽ����
	 * 4.handleNumOfTracks			1���������Ƿ��д���
	 * 								2������Ĵ������ͣ����ʽ����'='����/�������������/�����û����������������
	 * 										������ʽ
	 * 
	 */
	@Test
	public void testAtomHandler() {
		AtomStructureHandler h=new AtomStructureHandler();
		assertNull(h.FilesyntaxHandling("A"));
		String str="The syntax of element numbers on track is \"int\\int\"";
		assertEquals(str,h.FilesyntaxHandling("NumberOfElectron ::= 1/2;2/8;3/18.0;4/8;5/1"));
		str="ElementName ::=";
		h.FilesyntaxHandling(str);h.FilesyntaxHandling(null);
		str="ElementName ::= Rba";
		h.FilesyntaxHandling(str);
		str="NumberOfTracks ::= 11.1";
		h.FilesyntaxHandling(str);
		str="NumberOfTracks ::= -5";
		h.FilesyntaxHandling(str);
		str="NumberOfElectron ::= 1/2,2/8,3/18,4/8,5/1";
		h.FilesyntaxHandling(str);
		str="NumberOfElectron ::= 1/2;2/8;3/18.0;4/8;5/1";
		h.FilesyntaxHandling(str);
		str="NumberOfElectron ::= one in the first orbit";
		h.FilesyntaxHandling(str);
		str="NumberOfElectron ::= one in the first orbit";
		h.FilesyntaxHandling(str);
		str="ElementName :: Rb";
		h.FilesyntaxHandling(str);
		str="ElementName ::= b";
		h.FilesyntaxHandling(str);
		str="ElementName ::= RD";
		h.FilesyntaxHandling(str);
		str="ElementName ::= Rb";
		h.FilesyntaxHandling(str);
		str="ElementName ::= R";
		h.FilesyntaxHandling(str);
		str="NumberOfTracks :: 5";
		h.FilesyntaxHandling(str);
		str="NumberOfTracks ::= 5";
		h.FilesyntaxHandling(str);
		
	}
}
