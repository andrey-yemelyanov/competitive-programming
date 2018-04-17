import java.util.*;
import java.util.stream.*;

/*
Problem name: 10680 LCM
Problem url: https://uva.onlinejudge.org/external/106/10680.pdf
Author: Andrey Yemelyanov
*/
public class _10680 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      System.out.println(getLastLcmNonZeroDigit(N, primes));
    }
  }

  static int getLastLcmNonZeroDigit(int N, List<Integer> primes){
    long result = (pow(2, highestPower(2, N) - highestPower(5, N))) % 10;
    for(int prime : primes){
      if(prime > N) break;
      if(prime == 5 || prime == 2) continue;
      result = (result * pow(prime, highestPower(prime, N))) % 10;
    }
    return (int)result;
  }

  static int highestPower(int n, int N){
    int power = 1;
    while(pow(n, power) <= N) power++;
    return power - 1;
  }

  static long pow(long n, int p){
    if(p == 0) return 1;
    if(p % 2 == 0) return pow(n * n, p / 2);
    else return n * pow(n * n, p / 2);
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
