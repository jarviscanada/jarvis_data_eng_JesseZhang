package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareMapsTest {


  CompareMaps compareMaps = new CompareMaps();
  Map<String, String> asiaCapital1 = new HashMap<String, String>();
  Map<String, String> asiaCapital2 = new HashMap<String, String>();
  Map<String, String> asiaCapital3 = new HashMap<String, String>();


  @Test
  public void compareMaps1() {
    asiaCapital1.put("Japan","Tokyo");
    asiaCapital1.put("South Korea","Seoul");
    asiaCapital2.put("South Korea","Seoul");
    asiaCapital2.put("Japan","Tokyo");
    asiaCapital3.put("Japan","Tokyo");
    asiaCapital3.put("China","Beijing");
    assertTrue(compareMaps.compareMaps1(asiaCapital1,asiaCapital2));
    assertFalse(compareMaps.compareMaps1(asiaCapital1,asiaCapital3));
  }

  @Test
  public void compareMaps2() {
    asiaCapital1.put("Japan","Tokyo");
    asiaCapital1.put("South Korea","Seoul");
    asiaCapital2.put("South Korea","Seoul");
    asiaCapital2.put("Japan","Tokyo");
    asiaCapital3.put("Japan","Tokyo");
    asiaCapital3.put("China","Beijing");
    assertTrue(compareMaps.compareMaps2(asiaCapital1,asiaCapital2));
    assertFalse(compareMaps.compareMaps2(asiaCapital1,asiaCapital3));
  }
}