import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10738 Riemann vs Mertens
Problem url: https://uva.onlinejudge.org/external/107/10738.pdf
Author: Andrey Yemelyanov
*/
public class _10738 {
  static final int BOUND = 1000000;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Set<Integer> primes = new HashSet<>(sieve(BOUND));
    int[] diffPfCount = countDiffPrimeFactors(primes);
    boolean[] squareFree = sieveSquareFree(primes);
    int[] mu = muFunc(diffPfCount, squareFree);
    int[] m = mertensFunc(mu);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("%8d%8d%8d\n", n, mu[n], m[n]);
    }
  }

  static int[] mertensFunc(int[] mu){
    int[] m = new int[BOUND + 1];
    m[1] = mu[1];
    for(int i = 2; i < m.length; i++){
      m[i] = m[i - 1] + mu[i];
    }
    return m;
  }

  static int[] muFunc(int[] diffPfCount, boolean[] squareFree){
    int[] mu = new int[BOUND + 1];
    for(int i = 1; i < mu.length; i++){
      if(i == 1) mu[i] = 1;
      if(squareFree[i]){
        if(diffPfCount[i] % 2 == 0) mu[i] = 1;
        else mu[i] = -1;
      }
    }
    return mu;
  }

  static boolean[] sieveSquareFree(Set<Integer> primes){
    boolean[] squareFree = new boolean[BOUND + 1];
    for(int i = 0; i < squareFree.length; i++) squareFree[i] = true;
    for(int i = 2; i < squareFree.length; i++){
      if(primes.contains(i)){ // i is a prime number
        for(int j = i; j < squareFree.length; j += i){
          if(j % (i * i) == 0) squareFree[j] = false;
        }
      }
    }
    return squareFree;
  }

  static int[] countDiffPrimeFactors(Set<Integer> primes){
    int[] diffPfCount = new int[BOUND + 1];
    for(int i = 2; i < diffPfCount.length; i++){
      if(primes.contains(i)){ // i is a prime number
        for(int j = i; j < diffPfCount.length; j += i){
          diffPfCount[j]++;
        }
      }
    }
    return diffPfCount;
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
