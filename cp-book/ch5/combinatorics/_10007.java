import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10007 Count the Trees
Problem url: https://uva.onlinejudge.org/external/100/10007.pdf
Author: Andrey Yemelyanov
*/
public class _10007 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.println(catalanNumber(n).multiply(factorial(n)));
    }
  }

  static BigInteger factorial(int n){
    BigInteger f = BigInteger.ONE;
    for(int i = 1; i <= n; i++) f = f.multiply(BigInteger.valueOf(i));
    return f;
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
