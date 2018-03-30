import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 12036 Stable Grid
Problem url: https://uva.onlinejudge.org/external/120/12036.pdf
Author: Andrey Yemelyanov
*/
public class _12036 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt();
      int[][] grid = new int[n][n];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          grid[i][j] = s.nextInt();
        }
      }
      System.out.printf("Case %d: %s\n", c, (isStableGrid(grid) ? "yes" : "no"));
    }
  }

  static boolean isStableGrid(int[][] grid){
    final int MAX_MAGNITUDE = 100;
    int[] count = new int[MAX_MAGNITUDE + 1];
    int n = grid.length;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        count[grid[i][j]]++;
      }
    }
    for(int c : count) if(c > n) return false;
    return true;
  }
}
