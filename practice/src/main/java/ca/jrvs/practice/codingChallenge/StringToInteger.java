package ca.jrvs.practice.codingChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-54334616e62b430da75c61f8560bcdcd
 */
public class StringToInteger {

  /**
   * Big-O: O(n) Justification: use Java Regex API and build-in parse method
   */
  public int atoiAPI(String s) {
    String string = s.trim();
    int result = 0;

    Pattern pattern = Pattern.compile("^[-+]?\\d*");
    Matcher matcher = pattern.matcher(string);
    if (!matcher.find()) {
      return 0;
    } else {
      string = string.substring(matcher.start(), matcher.end());
    }
    try {
      result = Integer.parseInt(string);
    }catch (NumberFormatException e) {
      e.printStackTrace();
    }

    return result;
  }


  /**
   * Big-O: O(n) Justification: scan the string linearly
   */
  public int atoiASCII(String s) {
    if (s.isEmpty() || (s.length() == 1 && !Character.isDigit(s.charAt(0)))) {
      return 0;
    }

    int i = 0;
    int sign = 1;
    long result = 0;
    while (i < s.length() && s.charAt(i) == ' ') {
      i++;
    }

    if (s.charAt(i) == '+') {
      i++;
    } else {
      if (s.charAt(i) == '-') {
        sign = -1;
        i++;
      }
    }

    if (!Character.isDigit(s.charAt(i))) {
      return 0;
    }

    while (s.charAt(i) == '0') {
      i++;
      if (i >= s.length()) {
        return 0;
      }
    }
    ;
    if (!Character.isDigit(s.charAt(i))) {
      return 0;
    }

    while (s.charAt(i) == ' ') {
      i++;
      if (i >= s.length()) {
        break;
      }
    }

    if (i < s.length()) {
      while (Character.isDigit(s.charAt(i))) {
        ;
        result = result * 10 + s.charAt(i) - '0';
        if ((-1) * result < Integer.MIN_VALUE && sign == -1) {
          return Integer.MIN_VALUE;
        }
        if (result > Integer.MAX_VALUE && sign == 1) {
          return Integer.MAX_VALUE;
        }
        i++;
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
          break;
        }
      }
    }
    return (int) result * sign;
  }
}
