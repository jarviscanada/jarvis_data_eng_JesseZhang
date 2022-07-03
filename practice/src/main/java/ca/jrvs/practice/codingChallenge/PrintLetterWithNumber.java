package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket:
 * https://www.notion.so/jarvisdev/Print-letter-with-number-ad2694733c8046cea99966ae54d872ea
 */
public class PrintLetterWithNumber {

  /**
   * Big-O: O(n) Justification: go through the array
   */
  public String printLetterWithNumber(String s) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c >= 'a' && c <= 'z') {
        result.append(c).append(c - 96);
      }
      if (c >= 'A' && c <= 'Z') {
        result.append(c).append(c - 27);
      }
    }
    return result.toString();
  }

}
