package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-e2955a13a9824ad79f83e8e6460edf3d
 */
public class FibonacciNumber {


  /**
   * Big-O: O(2^n) Justification: each step increases 2 times steps
   */
  public int fibRecursive(int n) {
    if (n == 0 || n == 1) {
      return n;
    } else if (n > 1) {
      return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
    return -1;
  }


  /**
   * Big-O: O(n) Justification: iteratively go through n elements
   */
  public int fibDynamic(int n) {
    int result = 0;
    if (n == 0 || n == 1) {
      return n;
    } else {
      int pre1 = 1, pre2 = 0;
      while (n > 1) {
        result = pre1 + pre2;
        pre2 = pre1;
        pre1 = result;
        n--;
      }
    }
    return result;
  }
}
