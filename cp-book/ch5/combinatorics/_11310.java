import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 11310 Delivery Debacle
Problem url: https://uva.onlinejudge.org/external/113/11310.pdf
Author: Andrey Yemelyanov
*/
public class _11310 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTest = s.nextInt();
    while(nTest-- > 0){
      System.out.println(count(s.nextInt()));
    }
  }

  static BigInteger count(int i){
    BigInteger[] memo = new BigInteger[i + 1];
    return count(i, memo);
  }

  static BigInteger count(int i, BigInteger[] memo){
    if(i == 0 || i == 1) return BigInteger.valueOf(1);
    if(i == 2) return BigInteger.valueOf(5);
    if(memo[i] != null) return memo[i];
    return memo[i] = count(i - 1, memo)
      .add(BigInteger.valueOf(4).multiply(count(i - 2, memo)))
      .add(BigInteger.valueOf(2).multiply(count(i - 3, memo)));
  }
}
