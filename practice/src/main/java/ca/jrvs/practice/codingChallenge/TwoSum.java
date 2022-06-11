package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-ead853d95dbc4b889588005dab88b2d2
 */
public class TwoSum {


  /**
   * Big-O: O(n^2) Justification: two loops
   */
  public int[] twoSumsBruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

  /**
   * Big-O: O(n) Justification: use a HashMap to reduce the lookup time
   */
  public int[] twoSumsMap(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.get(complement) != null) {
        return new int[]{map.get(complement), i};
      } else {
        map.put(nums[i], i);
      }
    }
    return null;
  }
}
