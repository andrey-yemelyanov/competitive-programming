import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 990 Diving for Gold
Problem url: https://uva.onlinejudge.org/external/9/990.pdf
Author: Andrey Yemelyanov
*/
public class _990_DivingForGold {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    boolean first = true;
    while(s.hasNext()){
      int airRemaining = s.nextInt();
      int W = s.nextInt();
      int n = s.nextInt();
      int[] depth = new int[n]; int[] value = new int[n];
      for(int i = 0; i < n; i++){
        depth[i] = s.nextInt();
        value[i] = s.nextInt();
      }
      if(s.hasNext()) s.nextLine();
      if(s.hasNext()) s.nextLine();
      List<Integer> treasures = collectTreasures(W, depth, value, airRemaining);
      if(first) first = false;
      else System.out.println();
      int totalValue = treasures.stream().map(i -> value[i]).mapToInt(i -> i).sum();
      System.out.println(totalValue);
      System.out.println(treasures.size());
      treasures.stream().forEach(i -> System.out.println(depth[i] + " " + value[i]));
    }
  }

  static List<Integer> collectTreasures(int W, int[] depth, int[] value, int airRemaining){
    int[][] memo = new int[depth.length][airRemaining + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], INF);
    int maxValue = maxValue(W, depth, value, 0, airRemaining, memo);
    //System.out.println(maxValue);
    return getTreasures(memo, value, maxValue);
  }

  static List<Integer> getTreasures(int[][] memo, int[] value, int maxValue){
    List<Integer> treasures = new ArrayList<>();
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        if(memo[i][j] == maxValue){
          if(i + 1 == memo.length || memo[i + 1][j] != maxValue){
            maxValue -= value[i];
            treasures.add(i);
            if(maxValue == 0) return treasures;
            continue;
          }
        }
      }
    }
    return treasures;
  }


  static final int INF = -1;
  static int maxValue(int W, int[] depth, int[] value, int i, int airRemaining, int[][] memo){
    if(i == depth.length || airRemaining == 0) return 0;
    if(memo[i][airRemaining] != INF) return memo[i][airRemaining];
    int descentTime = W * depth[i]; int ascentTime = 2 * W * depth[i];
    int maxValue = 0;
    if(descentTime + ascentTime > airRemaining){
      maxValue = maxValue(W, depth, value, i + 1, airRemaining, memo);
    }else{
      int valueIfExcludeTreasure = maxValue(W, depth, value, i + 1, airRemaining, memo);
      int valueIfIncludeTreasure = maxValue(W, depth, value, i + 1, airRemaining - descentTime - ascentTime, memo) + value[i];
      maxValue = max(valueIfIncludeTreasure, valueIfExcludeTreasure);
    }
    return memo[i][airRemaining] = maxValue;
  }
}
