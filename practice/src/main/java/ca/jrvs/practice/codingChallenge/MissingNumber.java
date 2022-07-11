package ca.jrvs.practice.codingChallenge;


import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Missing-Number-bd873cb29708401384d881ebf9631cc3
 */
public class MissingNumber {


  /**
   * Big-O: O(n) Justification: go through the array
   */
  public int missingNumber1(int[] nums) {
    int res = nums.length;
    for (int i = 0; i < nums.length; i++) {
      res += i;
      res -= nums[i];
    }
    return res;
  }


  /**
   * Big-O: O(n) Justification: set operation and go through the array
   */
  public int missingNumber2(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i <= nums.length; i++) {
      set.add(i);
    }
    for (int i : nums) {
      set.remove(i);
    }
    return (int) set.toArray()[0];
  }


  /**
   * Big-O: O(n) Justification: go through the array
   */
  public int missingNumber3(int[] nums) {
    int n = nums.length;
    int sum = (0 + n) * (n + 1) / 2;
    for (int i : nums) {
      sum -= i;
    }
    return sum;
  }
}

