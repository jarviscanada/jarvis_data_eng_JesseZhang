package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-11930b6546ed4042a1af213ecf9b550c
 */
public class DuplicatesFromSortedArray {

  /**
   * Big-O: O(n) Justification: two pointers
   */
  public int removeDuplicates(int[] nums) {
    int i = 0;

    for (int j = 1; j < nums.length; j++) {
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }

}
