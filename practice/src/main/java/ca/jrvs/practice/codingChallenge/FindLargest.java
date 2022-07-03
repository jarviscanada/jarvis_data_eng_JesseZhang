package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-0f20270cc8494ff2a4fd97c013fdf189
 */
public class FindLargest {

  /**
   * Big-O: O(n) Justification: go through the array
   */
  public int findLargest1(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (max < arr[i]) {
        max = arr[i];
      }
    }
    return max;
  }

  /**
   * Big-O: O(n) Justification: stream APIs
   */
  public int findLargest2(int[] arr) {
    return Arrays.stream(arr).max().getAsInt();
  }


  /**
   * Big-O: O(n) Justification: built-in API
   */
  public int findLargest3(int[] arr) {
    Integer[] array = Arrays.stream(arr).boxed().toArray(Integer[]::new);
    return Collections.max(Arrays.asList(array));
  }

}
