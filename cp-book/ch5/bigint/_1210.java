import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 1210 Sum of Consecutive Prime Numbers
Problem url: https://uva.onlinejudge.org/external/12/1210.pdf
Author: Andrey Yemelyanov
*/
public class _1210 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    List<Integer> primes = generatePrimes(10000);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.println(countRepresentations(n, primes));
    }
  }

  static int countRepresentations(int n, List<Integer> primes){
    int N = primes.size();
    int count = 0;
    for(int i = 0; i < N; i++){
      int sum = 0;
      for(int j = i; j < N; j++){
        sum += primes.get(j);
        if(sum > n) break;
        if(sum == n){
          count++;
          break;
        }
      }
    }
    return count;
  }

  static List<Integer> generatePrimes(int max){
    List<Integer> primes = new ArrayList<>();
    for(int i = 0; i <= max; i++) if(isPrime(i)) primes.add(i);
    return primes;
  }

  static boolean isPrime(int n){
    return BigInteger.valueOf(n).isProbablePrime(10);
  }
}
