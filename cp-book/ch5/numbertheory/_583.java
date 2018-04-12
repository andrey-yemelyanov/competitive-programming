import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 583 Prime Factors
Problem url: https://uva.onlinejudge.org/external/5/583.pdf
Author: Andrey Yemelyanov
*/
public class _583 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.println(toString(primeFactors(n < 0 ? -n : n, primes), n));
    }
  }

  static String toString(List<Long> primeFactors, int n){
    StringBuilder sb = new StringBuilder();
    if(n < 0) sb.append("-1");
    for(int i = 0; i < primeFactors.size(); i++){
      if(sb.length() != 0) sb.append(" x ");
      sb.append(primeFactors.get(i));
    }
    sb.insert(0, n + " = ");
    return sb.toString();
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
