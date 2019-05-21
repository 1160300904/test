package appExceptions;

import static org.junit.Assert.*;
import org.junit.Test;

public class appExceptionTest {

	@Test
	public void testException() {
		FileInfoConflictException e1=new FileInfoConflictException();
		FileSyntaxException e2=new FileSyntaxException();
		RepeatedObjectsException e3=new RepeatedObjectsException();
		
		FileInfoConflictException e4=new FileInfoConflictException("a");
		FileSyntaxException e5=new FileSyntaxException("b");
		RepeatedObjectsException e6=new RepeatedObjectsException("c");
		
	}
}
