import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10394 Twin Primes
Problem url: https://uva.onlinejudge.org/external/103/10394.pdf
Author: Andrey Yemelyanov
*/
public class _10394 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    final int BOUND = 20000001;
    List<int[]> pairs = generateTwinPrimePairs(sieve(BOUND));
    while(s.hasNext()){
      int S = s.nextInt();
      int[] pair = pairs.get(S - 1);
      System.out.printf("(%d, %d)\n", pair[0], pair[1]);
    }
  }

  static List<int[]> generateTwinPrimePairs(List<Integer> primes){
    List<int[]> pairs = new ArrayList<>();
    for(int i = 1; i < primes.size(); i++){
      if(primes.get(i) - primes.get(i - 1) == 2) {
        pairs.add(new int[]{primes.get(i - 1), primes.get(i)});
      }
    }
    return pairs;
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
