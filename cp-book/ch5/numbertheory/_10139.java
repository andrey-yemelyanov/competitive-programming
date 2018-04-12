import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10139 Factovisors
Problem url: https://uva.onlinejudge.org/external/101/10139.pdf
Author: Andrey Yemelyanov
*/
public class _10139 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    while(s.hasNext()){
      int n = s.nextInt(); int m = s.nextInt();
      if(div(n, m, primes)){
        System.out.printf("%d divides %d!\n", m, n);
      }else{
        System.out.printf("%d does not divide %d!\n", m, n);
      }
    }
  }

  static boolean div(int n, int m, List<Integer> primes){
    Map<Long, Integer> map = new HashMap<>();
    for(long factor : primeFactors(m, primes)){
      map.putIfAbsent(factor, 0);
      map.put(factor, map.get(factor) + 1);
    }
    for(long factor : map.keySet()){
      if(map.get(factor) > getPowers(n, factor)) return false;
    }
    return true;
  }

  static long getPowers(int n, long p){
    long res = 0;
    for(long power = p; power <= n; power *= p) res += n / power;
    return res;
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
