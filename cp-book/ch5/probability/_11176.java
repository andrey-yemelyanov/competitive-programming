import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11176 Winning Streak
Problem url: https://uva.onlinejudge.org/external/111/11176.pdf
Author: Andrey Yemelyanov

It was a really hard problem for me to solve.
Big thanks to http://lbv-pc.blogspot.se/2012/06/winning-streak.html for a great explanation.
*/
public class _11176 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    while(s.hasNext()){
      int n = s.nextInt(); if(n == 0) break;
      double p = s.nextDouble();
      System.out.printf("%.6f\n", prob(n, p));
    }
  }

  static double prob(int nGames, double p){
    double[][] dp = new double[nGames + 1][nGames + 1];
    double[] pPower = new double[nGames + 1];
    pPower[0] = 1.0;
    for(int i = 1; i <= nGames; i++) pPower[i] = pPower[i - 1] * p;
    for(int j = 0; j <= nGames; j++) dp[0][j] = 1.0;
    for(int i = 1; i <= nGames; i++){
      for(int j = 0; j <= nGames; j++){
        dp[i][j] = dp[i - 1][j];
        if(j == i - 1) dp[i][j] -= pPower[i];
        else if(j < i - 1) dp[i][j] -= dp[i - j - 2][j] * (1 - p) * pPower[j + 1];
      }
    }
    double prob = 0.0;
    for(int j = 1; j <= nGames; j++) prob += j * (dp[nGames][j] - dp[nGames][j - 1]);
    return prob;
  }
}
