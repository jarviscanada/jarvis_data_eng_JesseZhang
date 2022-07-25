package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-7067bc1b6b88488f8682f462be2bd95e
 */
public class MergeSortedArray {


  /**
   * Big-O: O(n) Justification: go through the arrays
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int tail1 = m - 1, tail2 = n - 1, index = m + n - 1;

    while (tail2 >= 0 && tail1 >= 0) {
      if (nums1[tail1] < nums2[tail2]) {
        nums1[index--] = nums2[tail2--];
      } else {
        nums1[index--] = nums1[tail1--];
      }
    }

    while (tail2 >= 0) {
      nums1[index--] = nums2[tail2--];
    }

  }
}
