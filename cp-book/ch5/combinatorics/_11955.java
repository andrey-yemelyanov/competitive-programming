import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 11955 Binomial Theorem
Problem url: https://uva.onlinejudge.org/external/119/11955.pdf
Author: Andrey Yemelyanov
*/
public class _11955 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    for(int c = 1; c <= nTests; c++){
      String[] expr = s.nextLine().split("\\^");
      int k = Integer.parseInt(expr[1]);
      String[] vars = expr[0].replace("(","").replace(")","").split("\\+");
      BigInteger[][] memo = new BigInteger[k + 1][k + 1];
      System.out.printf("Case %d: %s\n", c, expand(k, vars[0], vars[1], memo));
    }
  }

  static String expand(int k, String var1, String var2, BigInteger[][] memo){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i <= k; i++){
      appendTerm(k, i, var1, var2, sb, memo);
      if(i < k) sb.append("+");
    }
    return sb.toString();
  }

  static void appendTerm(int k, int i, String var1, String var2, StringBuilder sb, BigInteger[][] memo){
    BigInteger binomial = binomial(k, i, memo);
    if(!binomial.equals(BigInteger.ONE)){
      sb.append(binomial);
      sb.append("*");
    }
    if(k - i > 0){
      sb.append(var1);
      if(k - i > 1){
        sb.append("^");
        sb.append(k - i);
      }
    }
    if(i > 0){
      if(k - i > 0) sb.append("*");
      sb.append(var2);
      if(i > 1){
        sb.append("^");
        sb.append(i);
      }
    }
  }

  static BigInteger binomial(int n, int k, BigInteger[][] memo){
    if(k == 0 || k == n) return BigInteger.ONE;
    if(memo[n][k] != null) return memo[n][k];
    return memo[n][k] = binomial(n - 1, k - 1, memo).add(binomial(n - 1, k, memo));
  }
}
