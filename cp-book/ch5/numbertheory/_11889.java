import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11889 Benefit
Problem url: https://uva.onlinejudge.org/external/118/11889.pdf
Author: Andrey Yemelyanov
*/
public class _11889 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    List<Integer> primes = sieve(1000000);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int B = getB(s.nextInt(), s.nextInt(), primes);
      if(B == NO_SOLUTION) System.out.println("NO SOLUTION");
      else System.out.println(B);
    }
  }

  static final int NO_SOLUTION = 0;
  static int getB(int A, int LCM, List<Integer> primes){
    if(LCM % A != 0) return NO_SOLUTION;
    Map<Long, Integer> factorsOfA = factorMap(primeFactors(A, primes));
    Map<Long, Integer> factorsOfLCM = factorMap(primeFactors(LCM, primes));
    int B = 1;
    for(long factor : factorsOfLCM.keySet()){
      if(!factorsOfA.containsKey(factor)){
        B *= (int)pow(factor, factorsOfLCM.get(factor));
      }else{
        int power = 0;
        int powerInA = factorsOfA.get(factor);
        int powerInLCM = factorsOfLCM.get(factor);
        if(powerInA != powerInLCM) power = max(powerInA, powerInLCM);
        B *= (int)pow(factor, power);
      }
    }
    return B;
  }

  static Map<Long, Integer> factorMap(List<Long> factors){
    Map<Long, Integer> factorMap = new HashMap<>();
    for(long factor : factors){
      factorMap.putIfAbsent(factor, 0);
      factorMap.put(factor, factorMap.get(factor) + 1);
    }
    return factorMap;
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
