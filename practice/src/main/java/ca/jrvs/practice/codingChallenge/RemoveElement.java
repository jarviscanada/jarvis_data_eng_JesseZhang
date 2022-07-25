package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Remove-Element-76c87652a9cf4574be20091730f75da6
 */
public class RemoveElement {

  /**
   * Big-O: O(n) Justification: two pointers
   */
  public int removeElement(int[] nums, int val) {
    int head = 0, tail = nums.length - 1;
    while (head <= tail) {
      if (nums[tail] == val) {
        tail--;
        continue;
      }
      if (nums[head] == val) {
        nums[head++] = nums[tail--];
      } else {
        head++;
      }
    }
    return head;
  }

}
