import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10699 Count the factors
Problem url: https://uva.onlinejudge.org/external/106/10699.pdf
Author: Andrey Yemelyanov
*/
public class _10699 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int[] diffPfCount = countDiffPrimeFactors(new HashSet<>(sieve(1000000)));
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("%d : %d\n", n, diffPfCount[n]);
    }
  }

  static int[] countDiffPrimeFactors(Set<Integer> primes){
    int[] diffPfCount = new int[1000000 + 1];
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
