package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-f60ba0dac86e456592cd69249c8f2593
 */
public class ValidPalindrome {


  /**
   * Big-O: O(n) Justification: go through the string using two pointers
   */
  public boolean isPalindrome1(String s) {

    char[] input = new char[s.length()];
    int count = 0;
    s = s.toLowerCase();
    for (char c : s.toCharArray()) {
      if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
        input[count++] = c;
      }
    }

    char[] newInput = Arrays.copyOfRange(input, 0, count);

    int j = 0;
    count--;
    while (j < count) {
      if (newInput[j++] != newInput[count--]) {
        return false;
      }
    }
    return true;
  }


  /**
   * Big-O: O(n) Justification: go through the string from two ends
   */
  public boolean isPalindrome2(String s) {
    s = s.toLowerCase();
    return isPalindromeHelper(s);

  }

  private boolean isPalindromeHelper(String s) {
    if (s.length() == 1 || s.length() == 0) {
      return true;
    }
    int first = 0, last = s.length() - 1;

    while (!(Character.isLetterOrDigit(s.charAt(first)))
        && first < last) {
      first++;
    }
    while (!(Character.isLetterOrDigit(s.charAt(last)))
        && first < last) {
      last--;
    }

    if (s.charAt(first) == s.charAt(last)) {
      if (first < last) {
        return isPalindromeHelper(s.substring(first + 1, last));
      } else {
        if (first == last) {
          return true;
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
  }

}
