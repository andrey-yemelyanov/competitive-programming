import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10179 Irreducable Basic Fractions
Problem url: https://uva.onlinejudge.org/external/101/10179.pdf
Author: Andrey Yemelyanov
*/
public class _10179 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      System.out.println(eulerPhi(N, primes));
    }
  }

  static long eulerPhi(int N, List<Integer> primes){
    int primeIndex = 0; long prime = primes.get(primeIndex); int ans = N;
    while(prime * prime <= N){
      if(N % prime == 0) ans -= ans / prime;
      while(N % prime == 0) N /= prime;
      prime = primes.get(++primeIndex);
    }
    if(N != 1) ans -= ans / N;
    return ans;
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
