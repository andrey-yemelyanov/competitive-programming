import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11466 Largest Prime Divisor
Problem url: https://uva.onlinejudge.org/external/114/11466.pdf
Author: Andrey Yemelyanov
*/
public class _11466 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(10000000);
    while(s.hasNext()){
      long n = s.nextLong();
      if(n == 0) break;
      System.out.println(largestPrimeFactor(n, primes));
    }
  }

  static long largestPrimeFactor(long n, List<Integer> primes){
    if(n < 0) n = -n;
    List<Long> primeFactors = primeFactors(n, primes);
    boolean allEqual = true;
    for(int i = 1; i < primeFactors.size(); i++){
      if(primeFactors.get(i) != primeFactors.get(i - 1)) allEqual = false;
    }
    if(allEqual) return -1;
    return primeFactors.get(primeFactors.size() - 1);
  }

  static List<Long> primeFactors(long n, List<Integer> primes){
    List<Long> factors = new ArrayList<>();
    int primeIndex = 0; int prime = primes.get(primeIndex);
    while((long)prime * prime <= n){
      while(n % prime == 0){
        n /= prime;
        factors.add((long)prime);
      }
      prime = primes.get(++primeIndex);
    }
    if(n != 1) factors.add(n);
    return factors;
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
