import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11137 Ingenuous Cubrency
Problem url: https://uva.onlinejudge.org/external/111/11137.pdf
Author: Andrey Yemelyanov
*/
public class _11137_IngenuousCubrency {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int[] coins = generateCubes(21);
    while(s.hasNext()){
      System.out.println(countWays(coins, s.nextInt()));
    }
  }

  static int[] generateCubes(int max){
    int[] cubes = new int[max];
    for(int i = 1; i <= max; i++){
      cubes[i - 1] = i * i * i;
    }
    return cubes;
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
