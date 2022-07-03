package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-c1030d482848483dbd66fe5852175748
 */
public class SwapTwoNumbers {

  public int[] swap1(int[] arr) {
    arr[0] = arr[0] ^ arr[1];
    arr[1] = arr[0] ^ arr[1];
    arr[0] = arr[0] ^ arr[1];
    return arr;
  }

  public int[] swap2(int[] arr) {
    arr[0] = arr[0] * arr[1];
    arr[1] = arr[0] / arr[1];
    arr[0] = arr[0] / arr[1];
    return arr;
  }

}
