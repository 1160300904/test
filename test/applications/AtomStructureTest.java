package applications;
import static org.junit.Assert.*;
import org.junit.Test;

import appExceptions.FileInfoConflictException;
import appExceptions.FileSyntaxException;
import physicalObject.*;

import java.io.*;
import java.util.*;
public class AtomStructureTest {

	/*Testing strategy
	 * 	��void initWithFile(File file) 	1�������ļ��е��쳣���ͣ�
				�﷨������Ϣ��ì�ܣ����ظ�������
	 */
	@Test
	public void testAtomStructure() throws FileNotFoundException,
							FileSyntaxException, FileInfoConflictException {
		ApplicationFactory af=new AtomStructureFactory();
		AtomStructure as=(AtomStructure)af.getApplication();
		File file=new File("test\\testfile\\AtomStructure\\AtomicStructure.txt");
		as.initWithFile(file);
		assertEquals("Rb",as.getCenterName());
		assertEquals(5,as.getEleOnTrack().keySet().size());
		
		as=(AtomStructure)af.getApplication();
		File file1=new File("test\\testfile\\AtomStructure\\EleNameMoreLetter.txt");
		
		try{
			as.initWithFile(file1);
		}catch(Exception ex) {
			assert ex instanceof FileSyntaxException;
		}
		as=(AtomStructure)af.getApplication();
		File file2=new File("test\\testfile\\AtomStructure\\AtomicStructure1.txt");
		try{
			as.initWithFile(file2);
		}catch(Exception ex) {
			assert ex instanceof FileInfoConflictException;
		}
	}
}
