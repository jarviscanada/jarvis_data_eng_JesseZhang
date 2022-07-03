package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckIfStringContainsOnlyDigitsTest {

  @Test
  public void ifOnlyDigits1() {
    CheckIfStringContainsOnlyDigits check = new CheckIfStringContainsOnlyDigits();
    assertTrue( check.ifOnlyDigits1("1234"));
    assertFalse( check.ifOnlyDigits1("123,000"));
  }


  @Test
  public void ifOnlyDigits2() {
    CheckIfStringContainsOnlyDigits check = new CheckIfStringContainsOnlyDigits();
    assertTrue( check.ifOnlyDigits2("1234"));
    assertFalse( check.ifOnlyDigits2("123,000"));
  }

  @Test
  public void ifOnlyDigits3() {
    CheckIfStringContainsOnlyDigits check = new CheckIfStringContainsOnlyDigits();
    assertTrue( check.ifOnlyDigits3("1234"));
    assertFalse( check.ifOnlyDigits3("123,000"));
  }

}