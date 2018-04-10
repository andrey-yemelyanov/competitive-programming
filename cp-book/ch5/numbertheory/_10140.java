import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10140 Prime Distance
Problem url: https://uva.onlinejudge.org/external/101/10140.pdf
Author: Andrey Yemelyanov
*/
public class _10140 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    final int BOUND = 10000000;
    List<Integer> primes = sieve(BOUND);
    while(s.hasNext()){
      long L = s.nextLong(); long U = s.nextLong();
      long[] result = findPairs(L, U, primes);
      if(result == null) System.out.println("There are no adjacent primes.");
      else System.out.printf("%d,%d are closest, %d,%d are most distant.\n",
        result[0], result[1],
        result[2], result[3]);
    }
  }

  static long[] findPairs(long L, long U, List<Integer> primes){
    long closestDist = Integer.MAX_VALUE;
    long farthestDist = Integer.MIN_VALUE;
    long prevPrime = -1;
    long[] closest = null; long[] farthest = null;
    for(long i = L; i <= U; i++){
      if(isPrime(i, primes)){
        if(prevPrime != -1 && i - prevPrime > farthestDist){
          farthestDist = i - prevPrime;
          farthest = new long[]{prevPrime, i};
        }
        if(prevPrime != -1 && i - prevPrime < closestDist){
          closestDist = i - prevPrime;
          closest = new long[]{prevPrime, i};
        }
        prevPrime = i;
      }
    }
    if(closest == null || farthest == null) return null;
    return new long[]{closest[0], closest[1], farthest[0], farthest[1]};
  }

  static long[] closestPair(long L, long U, List<Integer> primes){
    long dist = Integer.MAX_VALUE;
    long[] pair = null; long prevPrime = -1;
    for(long i = L; i <= U; i++){
      if(isPrime(i, primes)){
        if(prevPrime != -1 && i - prevPrime < dist){
          dist = i - prevPrime;
          pair = new long[]{prevPrime, i};
        }
        prevPrime = i;
      }
    }
    return pair;
  }

  static boolean isPrime(long n, List<Integer> primes){
    if(n < sieve.length) return sieve[(int)n];
    int bound = (int)ceil(sqrt(n));
    for(int i = 0; i < primes.size(); i++){
      if(primes.get(i) > bound) break;
      if(n % primes.get(i) == 0) return false;
    }
    return true;
  }

  static boolean[] sieve;
  static List<Integer> sieve(int upperBound){
    sieve = new boolean[upperBound + 1];
    List<Integer> primes = new ArrayList<>();
    for(int i = 2; i < sieve.length; i++) sieve[i] = true;
    for(int i = 2; i < sieve.length; i++){
      if(sieve[i]){
        primes.add(i);
        for(long j = (long)i * i; j < sieve.length; j += i) sieve[(int)j] = false;
      }
    }
    return primes;
  }
}
