package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OddEvenTest {

  OddEven oddEven = new OddEven();

  @Test
  public void oddEvenMod() {
    assertEquals("odd", oddEven.oddEvenMod(1));
    assertEquals("even", oddEven.oddEvenMod(2));
  }

  @Test
  public void oddEvenBit() {
    assertEquals("odd", oddEven.oddEvenBit(1));
    assertEquals("even", oddEven.oddEvenBit(2));
  }
}