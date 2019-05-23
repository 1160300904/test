package debug;

/**
 * Given two ordered integer arrays nums1 and nums2, with size m and n Find out the median (double)
 * of the two arrays. You may suppose nums1 and nums2 cannot be null at the same time.
 * Example 1: nums1 = [1, 3] nums2 = [2] The output would be 2.0
 * Example 2: nums1 = [1, 2] nums2 = [3, 4] The output would be 2.5
 * Example 3: nums1 = [1, 1, 1] nums2 = [5, 6, 7] The output would be 3.0
 * Example 4: nums1 = [1, 1] nums2 = [1, 2, 3] The output would be 1.0
 */

public class FindMedianSortedArrays {

  /**
   * find median sorted arrays.
   * @param a   one array.
   * @param b   the other array.
   * @return    the median number.
   */
  public double findMedianSortedArrays(int[] a, int[] b) {
    int m = a.length;
    int n = b.length;
    if (m > n) {
      int[] temp = a;
      a = b;
      b = temp;
      int tmp = m;
      m = n;
      n = tmp;
    }
    int imin = 0;
    int imax = m;
    int halfLen = (m + n + 1) / 2;
    // halfLen = (m + n) / 2;----halfLen = (m +n+1) / 2;
    while (imin <= imax) {
      int i = (imin + imax) / 2;// int i = (iMin + iMax + 1) / 2----int i = (iMin + iMax ) / 2
      int j = halfLen - i;
      if (i < imax && b[j - 1] > a[i]) {
        imin = i + 1;
      } else if (i > imin && a[i - 1] > b[j]) {
        imax = i - 1;
      } else {
        int maxLeft = 0;
        if (i == 0) {
          maxLeft = b[j - 1];
        } else if (j == 0) {
          maxLeft = a[i - 1];
        } else {
          maxLeft = Math.max(a[i - 1], b[j - 1]);
        }
        if ((m + n) % 2 != 0) {
          // if ((m + n + 1) % 2 == 1)-----if ((m + n) % 2 == 1)
          return maxLeft;
        }
        int minRight = 0;
        if (i == m) {
          minRight = b[j];
        } else if (j == n) {
          minRight = a[i];
        } else {
          minRight = Math.min(b[j], a[i]);
        }
        return (maxLeft + minRight) / 2.0;
      }
    }
    return 0.0;
  }

}
