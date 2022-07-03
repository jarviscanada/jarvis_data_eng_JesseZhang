package ca.jrvs.practice.codingChallenge;

/**
 * ticket:
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-86b776b056ba41ec9db1fe4dac288cb4
 */
public class CheckIfStringContainsOnlyDigits {


  /**
   * Big-O: O(n) Justification: go through the array
   */
  public boolean ifOnlyDigits1(String s) {
    char[] char_array = s.toCharArray();
    for (char c : char_array) {
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n) Justification: built-in regex method
   */
  public boolean ifOnlyDigits2(String s) {
    try {
      Integer.valueOf(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Big-O: O(n) Justification: built-in regex method
   */
  public boolean ifOnlyDigits3(String s) {
    return s.matches("\\d*");
  }
}
