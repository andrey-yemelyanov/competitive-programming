import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10312 Expression Bracketing
Problem url: https://uva.onlinejudge.org/external/103/10312.pdf
Author: Andrey Yemelyanov
*/
public class _10312 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      System.out.println(superCatalanNumber(n).subtract(catalanNumber(n - 1)));
    }
  }

  static BigInteger superCatalanNumber(int n){
    BigInteger[] memo = new BigInteger[n + 1];
    return superCatalanNumber(n, memo);
  }

  static BigInteger superCatalanNumber(int n, BigInteger[] memo){
    if(n == 1 || n == 2) return BigInteger.ONE;
    if(memo[n] != null) return memo[n];
    return memo[n] =
      BigInteger.valueOf(6 * n - 9)
        .multiply(superCatalanNumber(n - 1, memo))
        .subtract(BigInteger.valueOf(n - 3).multiply(superCatalanNumber(n - 2, memo)))
        .divide(BigInteger.valueOf(n));
  }

  static BigInteger catalanNumber(int n){
    return binomial(2 * n, n).divide(BigInteger.valueOf(n + 1));
  }

  static BigInteger binomial(int n, int k){
    // C(n,k) = n!/(n-k)!k!
    // C(n,k) = n(n-1)(n-2)...(n-k+1)/k(k-1)(k-2)...1
    BigInteger binomial = BigInteger.ONE;
    for(int i = n; i >= n - k + 1; i--){
      binomial = binomial.multiply(BigInteger.valueOf(i));
    }
    for(int i = 1; i <= k; i++){
      binomial = binomial.divide(BigInteger.valueOf(i));
    }
    return binomial;
  }
}
