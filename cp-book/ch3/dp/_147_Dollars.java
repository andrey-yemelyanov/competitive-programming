import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 147 Dollars
Problem url: https://uva.onlinejudge.org/external/1/147.pdf
Author: Andrey Yemelyanov
*/
public class _147_Dollars {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    int[] coins = new int[] {10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
    while(s.hasNext()){
      int V = (int)round(s.nextDouble() * 100);
      if(V == 0) break;
      System.out.printf("%6.2f%17d\n", (double)V / 100, countWays(coins, V));
    }
  }

  static long countWays(int[] coins, int V){
    long[][] memo = new long[coins.length][V + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return countWays(coins, V, 0, memo);
  }

  static long countWays(int[] coins, int V, int i, long[][] memo){
    if(V == 0) return 1;
    if(V < 0 || i == coins.length) return 0;
    if(memo[i][V] != -1) return memo[i][V];
    return memo[i][V] = countWays(coins, V, i + 1, memo) + countWays(coins, V - coins[i], i, memo);
  }
}
