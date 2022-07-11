package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Count-Primes-a69bc3e1f52a431291a75f111d6ecbc2
 */
public class CountPrimes {

  /**
   * Big-O: O(nlog(log(n)))  Justification: Sieve of Eratosthenes
   */
  public int countPrimes(int n) {
    boolean[] notPrime = new boolean[n];
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (!notPrime[i]) {
        count++;
        for (int j = i; j < n; j += i) {
          notPrime[j] = true;
        }
      }
    }
    return count;
  }


}







