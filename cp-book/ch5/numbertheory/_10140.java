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
    List<Integer> primes = sieve(BOUND);
    while(s.hasNext()){
      int L = s.nextInt(); int U = s.nextInt();
      int[] mostDistant = mostDistantPair(L, U, primes);
      int[] closest = closestPair(L, U, primes);
      if(closest == null || mostDistant == null) System.out.println("There are no adjacent primes.");
      else System.out.printf("%d,%d are closest, %d,%d are most distant.\n",
        closest[0], closest[1],
        mostDistant[0], mostDistant[1]);
    }
  }

  static int[] mostDistantPair(int L, int U, List<Integer> primes){
    long dist = Integer.MIN_VALUE;
    int[] pair = null; long prevPrime = -1;
    for(long i = L; i <= U; i++){
      if(isPrime(i, primes)){
        if(prevPrime != -1 && i - prevPrime > dist){
          dist = i - prevPrime;
          pair = new int[]{(int)prevPrime, (int)i};
        }
        prevPrime = i;
      }
    }
    return pair;
  }

  static int[] closestPair(int L, int U, List<Integer> primes){
    long dist = Integer.MAX_VALUE;
    int[] pair = null; long prevPrime = -1;
    for(long i = L; i <= U; i++){
      if(isPrime(i, primes)){
        if(prevPrime != -1 && i - prevPrime < dist){
          dist = i - prevPrime;
          pair = new int[]{(int)prevPrime, (int)i};
        }
        prevPrime = i;
      }
    }
    return pair;
  }

  static final int BOUND = 1000000;

  static boolean isPrime(long n, List<Integer> primes){
    int bound = (int)ceil(sqrt(n));
    for(int i = 0; i < primes.size() && bound >= primes.get(i); i++){
      if(n % primes.get(i) == 0) return false;
    }
    return true;
  }

  static List<Integer> sieve(int upperBound){
    boolean[] sieve = new boolean[upperBound + 1];
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
