package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

  /**
   * find the target index in a sorted array
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {

    return binarySearchRecursionHelper(arr, target, 0, arr.length - 1);
  }

  private <E> Optional<Integer> binarySearchRecursionHelper(E[] arr, E target, int lo, int hi) {
    if (lo > hi) {
      return Optional.empty();
    }

    int mid_index = (lo + hi) >>> 1;
    Comparable<E> mid = (Comparable<E>) arr[mid_index];
    int cmp = mid.compareTo(target);
    if (cmp < 0) {
      return binarySearchRecursionHelper(arr, target, mid_index+1, hi);
    } else {
      if (cmp > 0) {
        return binarySearchRecursionHelper(arr, target, lo, mid_index-1);
      } else {
        return Optional.of(mid_index);
      }
    }


  }

  /**
   * find the target index in a sorted array
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int lo_index = 0, hi_index = arr.length - 1;
    int mid_index;
    while (lo_index <= hi_index) {
      mid_index = (lo_index + hi_index) >>> 1;
      if (arr[mid_index].equals(target)) {
        return Optional.of(mid_index);
      } else {
        Comparable<E> mid = (Comparable<E>) arr[mid_index];
        if (mid.compareTo(target) < 0) {
          lo_index = mid_index+1;
        } else {
          hi_index = mid_index-1;
        }
      }
    }
    return Optional.empty();
  }

}
