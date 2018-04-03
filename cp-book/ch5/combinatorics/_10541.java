import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10541 Stripe
Problem url: https://uva.onlinejudge.org/external/105/10541.pdf
Author: Andrey Yemelyanov
*/
public class _10541 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt();
      int K = s.nextInt();
      int[] code = new int[K];
      for(int i = 0; i < K; i++) code[i] = s.nextInt();
      System.out.println(calcNumberOfRectangles(N, code));
    }
  }

  static BigInteger calcNumberOfRectangles(int N, int[] code){
    int nWhiteBlocks = N;
    for(int c : code) nWhiteBlocks -= c;
    if(nWhiteBlocks + 1 < code.length) return BigInteger.ZERO;
    return binomial(nWhiteBlocks + 1, code.length);
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
