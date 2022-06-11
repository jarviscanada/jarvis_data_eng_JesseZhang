package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToIntegerTest {

  StringToInteger stringToInteger = new StringToInteger();

  @Test
  public void atoiAPI() {
    assertEquals(42 ,stringToInteger.atoiAPI("42"));
    assertEquals(-42 ,stringToInteger.atoiAPI("   -42"));
    assertEquals(4193 ,stringToInteger.atoiAPI("4193 with words"));
  }
}