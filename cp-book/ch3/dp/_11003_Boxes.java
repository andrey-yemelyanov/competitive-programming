import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11003 Boxes
Problem url: https://uva.onlinejudge.org/external/110/11003.pdf
Author: Andrey Yemelyanov
*/
public class _11003_Boxes {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      int[] weight = new int[N];
      int[] maxLoad = new int[N];
      for(int i = 0; i < N; i++){
        weight[i] = s.nextInt();
        maxLoad[i] = s.nextInt();
      }
      System.out.println(stackBoxes(weight, maxLoad));
    }
  }

  static int stackBoxes(int[] weight, int[] maxLoad){
    int maxWeight = getMaxWeight(weight, maxLoad);
    int[][] memo = new int[weight.length][maxWeight + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return stackBoxes(weight, maxLoad, maxWeight, 0, memo);
  }

  static int getMaxWeight(int[] weight, int[] maxLoad){
    int maxWeight = 0;
    for(int i = 0; i < weight.length; i++){
      maxWeight = max(maxWeight, weight[i] + maxLoad[i]);
    }
    return maxWeight;
  }

  static int stackBoxes(int[] weight, int[] maxLoad, int maxWeight, int i, int[][] memo){
    if(i == weight.length) return 0;
    if(maxWeight == 0) return 0;
    if(memo[i][maxWeight] != -1) return memo[i][maxWeight];
    int maxBoxes = 0;
    if(weight[i] > maxWeight){
      maxBoxes = stackBoxes(weight, maxLoad, maxWeight, i + 1, memo);
    }else{
      maxBoxes = max(stackBoxes(weight, maxLoad, maxWeight, i + 1, memo),
                     stackBoxes(weight, maxLoad, min(maxLoad[i], maxWeight - weight[i]), i + 1, memo)
                     + 1);
    }
    return memo[i][maxWeight] = maxBoxes;
  }
}
