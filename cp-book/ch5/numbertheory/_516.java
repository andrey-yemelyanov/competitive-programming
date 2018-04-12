import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 516 Prime Land
Problem url: https://uva.onlinejudge.org/external/5/516.pdf
Author: Andrey Yemelyanov
*/
public class _516 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> primes = sieve(1000000);
    while(s.hasNext()){
      String[] line = s.nextLine().split("\\s+");
      if(line[0].equals("0")) break;
      int n = 1;
      for(int i = 0; i < line.length - 1; i+=2){
        n *= pow(Integer.parseInt(line[i]), Integer.parseInt(line[i + 1]));
      }
      System.out.println(toString(primeFactors(n - 1, primes)));
    }
  }

  static String toString(List<Long> primeFactors){
    Collections.reverse(primeFactors);
    StringBuilder sb = new StringBuilder();
    int freq = 1;
    long prev = primeFactors.get(0);
    for(int i = 1; i < primeFactors.size(); i++){
      if(primeFactors.get(i) == prev) freq++;
      else{
        if(sb.length() != 0) sb.append(" ");
        sb.append(prev + " " + freq);
        freq = 1;
        prev = primeFactors.get(i);
      }
    }
    if(sb.length() != 0) sb.append(" ");
    sb.append(prev + " " + freq);
    return sb.toString();
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
