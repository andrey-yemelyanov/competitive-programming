import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 543 Goldbach's Conjecture
Problem url: https://uva.onlinejudge.org/external/5/543.pdf
Author: Andrey Yemelyanov
*/
public class _543 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000 - 1);
    Set<Integer> primesSet = new HashSet<>(primes);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[] result = testGoldbachsConjecture(n, primes, primesSet);
      if(result != null) System.out.printf("%d = %d + %d\n", n, result[0], result[1]);
      else System.out.println("Goldbach's conjecture is wrong.");
    }
  }

  static int[] testGoldbachsConjecture(int n, List<Integer> primes, Set<Integer> primesSet){
    for(int prime : primes) {
      if(primesSet.contains(n - prime)) return new int[]{prime, n - prime};
    }
    return null;
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
