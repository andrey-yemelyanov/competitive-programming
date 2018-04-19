import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10990 Another New Function
Problem url: https://uva.onlinejudge.org/external/109/10990.pdf
Author: Andrey Yemelyanov
*/
public class _10990 {
  static final int BOUND = 2000000;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    sieve(BOUND);
    int[] sodf = SODF(sieveEulerPhi());
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int m = s.nextInt(); int n = s.nextInt();
      System.out.println(sodf[n] - sodf[m - 1]);
    }
  }

  static int[] SODF(int[] eulerPhi){
    int[] sum = new int[BOUND + 1];
    for(int i = 1; i < sum.length; i++){
      sum[i] = sum[i - 1] + depthPhi(i, eulerPhi);
    }
    return sum;
  }

  static int[] memo = new int[BOUND + 1];
  static int depthPhi(int n, int[] eulerPhi){
    if(n == 1) return 0;
    if(memo[n] != 0) return memo[n];
    return memo[n] = depthPhi(eulerPhi[n], eulerPhi) + 1;
  }

  static int[] sieveEulerPhi(){
    int[] eulerPhi = new int[BOUND + 1];
    for(int i = 0; i < eulerPhi.length; i++) eulerPhi[i] = i;
    for(int i = 2; i < eulerPhi.length; i++){
      if(sieve[i]){ // i is a prime number
        for(int j = i; j < eulerPhi.length; j += i){
          eulerPhi[j] -= eulerPhi[j] / i;
        }
      }
    }
    return eulerPhi;
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
