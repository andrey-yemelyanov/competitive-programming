import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 562 Dividing coins
Problem url: https://uva.onlinejudge.org/external/5/562.pdf
Author: Andrey Yemelyanov
*/
public class _562_DividingCoins {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int[] coins = new int[s.nextInt()];
      for(int i = 0; i < coins.length; i++){
        coins[i] = s.nextInt();
      }
      System.out.println(bestDistribution(coins));
    }
  }

  static int bestDistribution(int[] coins){
    int totalValue = Arrays.stream(coins).sum();
    int maxValue = knapsack(coins, totalValue / 2);
    return abs(totalValue - 2 * maxValue);
  }

  static int knapsack(int[] items, int capacity){
    int[][] memo = new int[items.length][capacity + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], INF);
    return knapsack(items, 0, capacity, memo);
  }

  static final int INF = -1;
  static int knapsack(int[] items, int i, int capacity, int[][] memo){
    if(capacity == 0 || i == items.length) return 0;
    if(memo[i][capacity] != INF) return memo[i][capacity];
    int maxValue = 0;
    if(items[i] > capacity) maxValue = knapsack(items, i + 1, capacity, memo);
    else maxValue = max(
      knapsack(items, i + 1, capacity, memo),
      knapsack(items, i + 1, capacity - items[i], memo) + items[i]);
    return memo[i][capacity] = maxValue;
  }
}
