import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10446 The Marriage Interview :-)
Problem url: https://uva.onlinejudge.org/external/104/10446.pdf
Author: Andrey Yemelyanov
*/
public class _10446_MarriageInterview {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int c = 1;
    while(s.hasNext()){
      int n = s.nextInt(); if(n > 60) break;
      int back = s.nextInt();
      System.out.printf("Case %d: %s\n", c++, trib(n, back).toString());
    }
  }

  static BigInteger trib(int n, int back){
    BigInteger[][] memo = new BigInteger[61][61];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], BigInteger.valueOf(-1));
    return trib(n, back, memo);
  }

  static BigInteger trib(int n, int back, BigInteger[][] memo)
  {
    BigInteger nCalls = BigInteger.ONE;
    if(n <= 0 || n == 1) return BigInteger.ONE;
    if(!memo[n][back].equals(BigInteger.valueOf(-1))) return memo[n][back];
    for(int i = 1; i <= back; i++){
      nCalls = nCalls.add(trib(n - i, back, memo));
    }
    return memo[n][back] = nCalls;
  }
}
