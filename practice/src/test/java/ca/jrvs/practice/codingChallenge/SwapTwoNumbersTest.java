package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {

  @Test
  public void swap1() {
    SwapTwoNumbers swap = new SwapTwoNumbers();
    assertArrayEquals(new int[]{3, 2}, swap.swap1(new int[]{2, 3}));
  }

  @Test
  public void swap2() {
    SwapTwoNumbers swap = new SwapTwoNumbers();
    assertArrayEquals(new int[]{3, 2}, swap.swap2(new int[]{2, 3}));
  }
}