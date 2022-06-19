package ca.jrvs.practice.codingChallenge;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-b15560e2aa5f45e99d15489f6f9412e4
 */
public class ValidAnagram {

  /**
   * Big-O: O(nlogn) Justification: use sort
   */
  public boolean isAnagram1(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] s_array = s.toCharArray();
    char[] t_array = t.toCharArray();

    Arrays.sort(s_array);
    Arrays.sort(t_array);

    for (int i = 0; i < s_array.length; i++) {
      if (s_array[i] != t_array[i]) {
        return false;
      }
    }

    return true;
  }


  /**
   * Big-O: O(n) Justification: use hashmap
   */
  public boolean isAnagram2(String s, String t) {
    int n = s.length();
    int m = t.length();
    if (n != m) {
      return false;
    }

    Map<Character, Integer> count = new HashMap<>();
    for (int i = 0; i < n; i++) {
      count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
    }

    for (int i = 0; i < m; i++) {
      Character c = t.charAt(i);
      if (count.containsKey(c)) {
        if (count.get(c) == 1) {
          count.remove(c);
        } else {
          count.replace(c, count.get(c), count.get(c) - 1);
        }
      }
    }

    if (count.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }


}
