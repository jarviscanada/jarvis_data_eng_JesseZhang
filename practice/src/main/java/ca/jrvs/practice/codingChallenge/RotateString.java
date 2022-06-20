package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-e1cd90448b064106bc94bcb2a5018c4f
 */
public class RotateString {

  /**
   * Big-O: O(n) Justification: use String build-in methods
   */
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }

    int index_first_match = s.length() - 2;
    int length = 1;
    while (goal.contains(s.substring(index_first_match))) {
      index_first_match--;
      length++;
    }
    String match = s.substring(index_first_match + 1);

    if (s.substring(0, index_first_match + 1)
        .equals(goal.substring(goal.indexOf(match) + length))) {
      return true;
    } else {
      return false;
    }
  }
}
