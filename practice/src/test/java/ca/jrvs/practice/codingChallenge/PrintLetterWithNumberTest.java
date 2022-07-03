package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintLetterWithNumberTest {

  @Test
  public void printLetterWithNumber() {
    PrintLetterWithNumber print = new PrintLetterWithNumber();
    assertEquals("a1b2c3e5e5", print.printLetterWithNumber("abcee"));
  }
}