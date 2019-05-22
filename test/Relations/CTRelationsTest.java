package Relations;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class CTRelationsTest {
  /*
   * Testing strategy: 1.two constructors: input :null, not null. 2.addRelation(): relation has
   * existed in current relations, or not. 3.deleRelation(): relation has existed in current
   * relations, or not. 4.relatedTo(): the relation between the inputs has existed in current
   * relations, or not.
   */

  @Test
  public void testCTRelations() {
    CTRelations<String, String> c1;
    try {
      c1 = new CTRelations<>(null);

    } catch (Exception ex) {
      assert ex instanceof IllegalArgumentException;
    }

    try {
      c1 = new CTRelations<>(null, null);

    } catch (Exception ex) {
      assert ex instanceof IllegalArgumentException;
    }
    Set<String> set = new HashSet<>();
    try {
      c1 = new CTRelations<>(set, null);

    } catch (Exception ex) {
      assert ex instanceof IllegalArgumentException;
    }
    try {
      c1 = new CTRelations<>(null, "A");

    } catch (Exception ex) {
      assert ex instanceof IllegalArgumentException;
    }
    c1 = new CTRelations<>("cen");
    try {
      c1.setCentralObject(null);

    } catch (Exception ex) {
      assert ex instanceof IllegalArgumentException;
    }
    c1.setCentralObject("cen");

    set.add("o1");
    set.add("o2");
    set.add("o3");
    CTRelations<String, String> c2 = new CTRelations<>(set, "cen");
    assertEquals("cen", c1.getCentralObject());
    Set<String> retset = c2.getPhysicalObject();
    assertTrue(retset.contains("o1"));
    assertTrue(retset.contains("o2"));
    assertTrue(retset.contains("o3"));
    assertFalse(retset.contains("o4"));

    assertTrue(c1.addRelation("o1"));
    assertFalse(c1.addRelation("o1"));
    assertTrue(c1.addRelation("o2"));
    assertTrue(c1.addRelation("o3"));
    assertTrue(c1.addRelation("o4"));
    assertTrue(c1.deleRelation("o4"));
    assertFalse(c1.deleRelation("o4"));
    retset = c2.getPhysicalObject();
    assertTrue(retset.contains("o1"));
    assertTrue(retset.contains("o2"));
    assertTrue(retset.contains("o3"));
    assertFalse(retset.contains("o4"));
    assertTrue(c1.relatedTo("o1"));
    assertFalse(c1.relatedTo("o5"));
  }
}
