package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket:
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-6521b724adc44c60830d19f132581b13
 */
public class FindDuplicateNumber {

  /**
   * Big-O: O(nlog(n)) Justification: sort algorithm
   */
  public int findDuplicate1(int[] nums) {
    Arrays.sort(nums);
    int cur = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == cur) {
        return cur;
      }
      cur = nums[i];
    }
    return cur;
  }


  /**
   * Time Big-O: O(n) Justification: go through the array Space Big-O: O(n)
   */
  public int findDuplicate2(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i : nums) {
      if (set.contains(i)) {
        return i;
      } else {
        set.add(i);
      }
    }
    return -1;
  }


}
