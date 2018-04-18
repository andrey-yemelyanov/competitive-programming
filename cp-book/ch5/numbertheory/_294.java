import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 294 Divisors
Problem url: https://uva.onlinejudge.org/external/2/294.pdf
Author: Andrey Yemelyanov
*/
public class _294 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int L = s.nextInt(); int U = s.nextInt();
      long maxDivisors = 0;
      int numberWithMaxDivisors = 0;
      for(int n = L; n <= U; n++){
        long nDivisors = nDivisors(n, primes);
        if(nDivisors > maxDivisors){
          maxDivisors = nDivisors;
          numberWithMaxDivisors = n;
        }
      }
      System.out.printf("Between %d and %d, %d has a maximum of %d divisors.\n", L, U, numberWithMaxDivisors, maxDivisors);
    }
  }

  static long nDivisors(long N, List<Integer> primes){
    int primeIndex = 0; long prime = primes.get(primeIndex); int ans = 1;
    while(prime * prime <= N){
      int power = 0;
      while(N % prime == 0){ N /= prime;  power++;}
      ans *= (power + 1);
      prime = primes.get(++primeIndex);
    }
    if(N != 1) ans *= 2;
    return ans;
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
