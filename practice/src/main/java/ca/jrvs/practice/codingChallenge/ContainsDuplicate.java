package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Contains-Duplicate-3fe76d4e853d49ec8ba83b32264efc35
 */
public class ContainsDuplicate {

  /**
   * Big-O: O(n^2) Justification: two loops
   */
  public boolean containsDuplicate1(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == nums[j]) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Big-O: O(n) Justification: use set operation
   */
  public boolean containsDuplicate2(int[] nums) {
    Set<Integer> nums_set = new HashSet<>();
    for (int x : nums) {
      if (nums_set.contains(x)) {
        return true;
      }
      nums_set.add(x);
    }
    return false;
  }
}
