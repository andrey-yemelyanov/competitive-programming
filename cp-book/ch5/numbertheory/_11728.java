import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11728 Alternate Task
Problem url: https://uva.onlinejudge.org/external/117/11728.pdf
Author: Andrey Yemelyanov
*/
public class _11728 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    Map<Long, Integer> map = new HashMap<>();
    for(int n = 1; n <= 1000; n++) map.put(sumDiv(n, primes), n);
    int caseNum = 1;
    while(s.hasNext()){
      long N = s.nextLong();
      if(N == 0) break;
      int ans = -1;
      if(map.containsKey(N)) ans = map.get(N);
      System.out.printf("Case %d: %d\n", caseNum++, ans);
    }
  }

  static long sumDiv(long N, List<Integer> primes){
    int primeIndex = 0; long prime = primes.get(primeIndex); long ans = 1;
    while(prime * prime <= N){
      int power = 0;
      while(N % prime == 0){ N /= prime; power++; }
      ans *= ((long)pow(prime, power + 1) - 1) / (prime - 1);
      prime = primes.get(++primeIndex);
    }
    if(N != 1) ans *= ((long)pow(N, 2) - 1) / (N - 1);
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
