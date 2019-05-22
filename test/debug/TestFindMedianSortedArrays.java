package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFindMedianSortedArrays {

  @Test
  public void testFindMedianSortedArrays() {
    FindMedianSortedArrays f = new FindMedianSortedArrays();
    int[] a1 = {1, 3};
    int[] b1 = {2};
    // int[] a1= {2,3,4,5,8,10};int [] b1= {1,3,4,6,9};
    int[] a2 = {1, 2};
    int[] b2 = {3, 4};
    int[] a3 = {1, 1, 1};
    int[] b3 = {5, 6, 7};
    int[] a4 = {1, 1};
    int[] b4 = {1, 2, 3};

    assertEquals(2.0, f.findMedianSortedArrays(a1, b1), 0.01);
    assertEquals(2.5, f.findMedianSortedArrays(a2, b2), 0.01);
    assertEquals(3.0, f.findMedianSortedArrays(a3, b3), 0.01);
    assertEquals(1.0, f.findMedianSortedArrays(a4, b4), 0.01);

    /*
     * System.out.println(f.findMedianSortedArrays(a1, b1));
     * System.out.println(f.findMedianSortedArrays(a2, b2));
     * System.out.println(f.findMedianSortedArrays(a3, b3));
     * System.out.println(f.findMedianSortedArrays(a4, b4));
     */

  }
}
