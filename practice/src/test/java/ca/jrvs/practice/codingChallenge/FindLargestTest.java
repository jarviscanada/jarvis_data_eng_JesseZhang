package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindLargestTest {

  int arr[] = {10, 324, 45, 90, 9808};

  @Test
  public void findLargest1() {
    FindLargest find = new FindLargest();
    assertEquals(9808, find.findLargest1(arr));
  }

  @Test
  public void findLargest2() {
    FindLargest find = new FindLargest();
    assertEquals(9808, find.findLargest2(arr));
  }

  @Test
  public void findLargest3() {
    FindLargest find = new FindLargest();
    assertEquals(9808, find.findLargest3(arr));
  }

}
