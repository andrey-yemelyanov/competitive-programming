import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12498 Ant's Shopping Mall
Problem url: https://uva.onlinejudge.org/external/124/12498.pdf
Author: Andrey Yemelyanov
*/
public class _12498_AntsShoppingMall {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 1; t <= nTests; t++){
      int R = s.nextInt(); int C = s.nextInt();
      int[][] mall = new int[R][C];
      for(int row = 0; row < R; row++){
        char[] r = s.next().toCharArray();
        for(int col = 0; col < C; col++){
          mall[row][col] = Character.getNumericValue(r[col]);
        }
      }
      System.out.printf("Case %d: %d\n", t, bestPathCost(mall));
    }
  }

  static int bestPathCost(int[][] mall){
    int minCost = Integer.MAX_VALUE;
    for(int col = 0; col < mall[0].length; col++){
      int totalCost = vacateCellCost(0, col, mall);
      if(totalCost == INF) return INF;
      for(int row = 1; row < mall.length; row++){
        int cost = vacateCellCost(row, col, mall);
        if(cost == INF) return INF;
        totalCost += cost;
      }
      minCost = min(minCost, totalCost);
    }
    return minCost;
  }

  static final int INF = -1; static final int VACANT = 0;
  static int vacateCellCost(int row, int col, int[][] mall){
    if(mall[row][col] == VACANT) return 0;
    int left = col - 1; int right = col + 1;
    int closestVacantCell = INF;
    while(true){
      if(left < 0 && right >= mall[row].length) break;
      if(left >= 0 && mall[row][left] == VACANT){
        closestVacantCell = left;
        break;
      }
      if(right < mall[row].length && mall[row][right] == VACANT){
        closestVacantCell = right;
        break;
      }
      left--; right++;
    }
    if(closestVacantCell == INF) return INF;
    return abs(col - closestVacantCell);
  }
}
