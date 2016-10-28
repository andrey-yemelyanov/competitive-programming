import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10913 Walking on a Grid
Problem url: https://uva.onlinejudge.org/external/109/10913.pdf
Author: Andrey Yemelyanov
*/
public class _10913 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    while(s.hasNext()){
      int N = s.nextInt(); int k = s.nextInt();
      if(N == 0 && k == 0) break;
      int[][] grid = new int[N][N];
      for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[i].length; j++){
          grid[i][j] = s.nextInt();
        }
      }
      int maxSum = walk(grid, k);
      if(maxSum == NEG_INF) System.out.printf("Case %d: impossible\n", caseNum++);
      else System.out.printf("Case %d: %d\n", caseNum++, maxSum);
    }
  }

  static int walk(int[][] grid, int K){
    memo = new int[grid.length][grid[0].length][K + 1][grid.length * grid[0].length + 1];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        for(int k = 0; k < memo[i][j].length; k++){
          for(int l = 0; l < memo[i][j][k].length; l++){
              memo[i][j][k][l] = UNDEF;
          }
        }
      }
    }
    int maxSum = walk(grid, 0, 0, grid.length - 1, grid[0].length - 1, grid[0][0] < 0 ? K - 1 : K, -1, -1);
    if(maxSum != NEG_INF) return maxSum + grid[0][0];
    return NEG_INF;
  }

  static final int NEG_INF = -1000000;
  static final int UNDEF = 1000000;
  static int[][][][] memo;
  static final int[] dr = new int[] {1, 0, 0}; // down, left, right
  static final int[] dc = new int[] {0, -1, 1}; // down, left, right
  static int walk(int[][] grid, int row, int col, int destRow, int destCol, int negLeft, int prevRow, int prevCol){
    if(negLeft < 0) return NEG_INF;
    if(row == destRow && col == destCol) return 0;
    int prevCell = (prevRow == -1 && prevCol == -1) ? 0 : (prevRow * grid[0].length + prevCol + 1);
    if(memo[row][col][negLeft][prevCell] != UNDEF) return memo[row][col][negLeft][prevCell];
    int maxSum = NEG_INF;
    for(int i = 0; i < 3; i++){
      int nextRow = row + dr[i]; int nextCol = col + dc[i];
      if(valid(grid, nextRow, nextCol) && !(prevRow == nextRow && prevCol == nextCol)){
        int walk = walk(grid, nextRow, nextCol, destRow, destCol,
          grid[nextRow][nextCol] < 0 ? negLeft - 1 : negLeft, row, col);
        if(walk != NEG_INF){
          maxSum = max(maxSum, walk + grid[nextRow][nextCol]);
        }
      }
    }
    return memo[row][col][negLeft][prevCell] = maxSum;
  }

  static boolean valid(int[][] grid, int row, int col){
    return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
  }
}
