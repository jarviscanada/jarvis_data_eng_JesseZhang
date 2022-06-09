package ca.jrvs.practice.codingChallenge;

import java.util.Map;
import java.util.Map.Entry;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-73eaf2b930ab427bb0149d529892ccba
 */
public class CompareMaps {

  /**
   * Big-O: O(n) Justification: go through one of the maps at least once
   */
  public <K, V> boolean compareMaps1(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }

  /**
   * Big-O: O(n) Justification: go through one of the maps at least once
   */
  public <K, V> boolean compareMaps2(Map<K, V> m1, Map<K, V> m2) {
    if (m1 == m2) {
      return true;
    }
    if (m1.size() != m2.size()) {
      return false;
    }
    for (Entry<K, V> e : m1.entrySet()) {
      K key = e.getKey();
      V value = e.getValue();
      if (value == null) {
        if (!(m2.get(key) == null && m2.containsKey(key))) {
          return false;
        }
      } else {
        if (!value.equals(m2.get(key))) {
          return false;
        }
      }
    }
    return true;
  }
}
