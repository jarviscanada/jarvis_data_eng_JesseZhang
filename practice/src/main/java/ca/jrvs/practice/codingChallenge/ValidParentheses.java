package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-3a2733da3b5a42dc843da67d851a3651
 */
public class ValidParentheses {

  /**
   * Big-O: O(n) Justification: go through the string linearly
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('{', '}');
    map.put('[', ']');
    char first = s.charAt(0);
    if (first == ')' || first == '}' || first == ']') {
      return false;
    }
    stack.push(first);
    for (int i = 1; i < s.length(); i++) {
      char top;
      if (!stack.isEmpty()) {
        top = stack.peek();
        if (map.containsKey(top) && map.get(top) == s.charAt(i)) {
          stack.pop();
        } else {
          stack.push(s.charAt(i));
        }
      } else {
        stack.push(s.charAt(i));
      }
    }
    return stack.isEmpty();
  }

}
