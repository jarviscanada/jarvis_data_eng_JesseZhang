package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {

  Integer[] sortedArray = new Integer[]{21, 41, 31, 12, 323, 543, 731, 1898};
  BinarySearch binarySearch = new BinarySearch();

  @Test
  public void binarySearchRecursion() {
    Optional<Integer> result = binarySearch.binarySearchRecursion(sortedArray, 323);
    assertEquals(Optional.of(4), result);
    Optional<Integer> result2 = binarySearch.binarySearchIteration(sortedArray, 123);
    assertEquals(Optional.empty(), result2);
  }

  @Test
  public void binarySearchIteration() {
    Optional<Integer> result = binarySearch.binarySearchIteration(sortedArray, 323);
    assertEquals(Optional.of(4), result);
    Optional<Integer> result2 = binarySearch.binarySearchIteration(sortedArray, 123);
    assertEquals(Optional.empty(), result2);
  }
}