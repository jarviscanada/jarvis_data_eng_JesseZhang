package ca.jrvs.practice.codingChallenge;


import java.util.HashMap;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-1e17a5a405f748b59dd3997c1c872858
 */
public class OddEven {

  /**
   * Big-O: O(1)
   * Justification: an arithmetic operation
   */
  public String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: O(1)
   * Justification: a bitwise operation
   */
  public String oddEvenBit(int i) {
    return (i & 1) == 0 ? "even" : "odd";
  }
}