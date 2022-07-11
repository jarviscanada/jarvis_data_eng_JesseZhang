package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-Characters-25547480af294e3ead561e6288935bd1
 */
public class DuplicateCharacters {

  /**
   * Big-O: O(n)  Justification: hashmap operation
   */
  public String[] duplicateCharacters(String s) {
    char[] input = s.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    ArrayList<String> output = new ArrayList<>();
    for (char c : input) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      char c = entry.getKey();
      int num = entry.getValue();
      if (num > 1 && !Character.isWhitespace(c)) {
        output.add(String.valueOf(c));
      }
    }
    return output.toArray(new String[0]);
  }
}
