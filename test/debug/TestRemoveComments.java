package debug;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TestRemoveComments {

  @Test
  public void testRemoveComments() {
    String[] source = {"/*Test program */", "int main()", "/*aaaa*/", "{ ",
        "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ",
        "   comment for ", "   testing */", "a = b + c;", "}"};
    RemoveComments rc = new RemoveComments();
    String[] res = {"int main()", "{ ", "  ", "int a, b, c;", "a = b + c;", "}"};

    List<String> a = rc.removeComments(source);
    assertEquals(res[0], a.get(0));
    assertEquals(res[1], a.get(1));
    assertEquals(res[2], a.get(2));
    assertEquals(res[3], a.get(3));
    assertEquals(res[4], a.get(4));
    assertEquals(res[5], a.get(5));
    // System.out.println(rc.removeComments(source));
    String[] source2 = {"a/*comment", "line", "more_comment*/b"};
    String[] ret2 = {"ab"};
    // System.out.println(rc.removeComments(source2));
  }
}
