import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 639 Don't Get Rooked
Problem url: https://uva.onlinejudge.org/external/6/639.pdf
Author: Andrey Yemelyanov
*/
public class _639_DontGetRooked {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[][] grid = new int[n][n];
      for(int i = 0; i < grid.length; i++){
        String row = s.next();
        for(int j = 0; j < grid.length; j++){
          if(row.charAt(j) == 'X') grid[i][j] = OBSTACLE;
        }
      }
      System.out.println(getMaxRooks(grid));
    }
  }

  static int countRooks(int[][] grid){
    int count = 0;
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        if(grid[i][j] == ROOK) count++;
      }
    }
    return count;
  }

  static int getMaxRooks(int[][] initialGrid){
    int maxRooks = 0;
    int n = initialGrid.length;
    for(int config = 0; config < (1 << (n * n)); config++){
      int[][] grid = buildGrid(config, initialGrid);
      if(gridValid(grid)){
        maxRooks = max(maxRooks, countRooks(grid));
      }
    }
    return maxRooks;
  }

  static int[][] buildGrid(int config, int[][] initialGrid){
    int[][] grid = new int[initialGrid.length][initialGrid.length];
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        if(initialGrid[i][j] == OBSTACLE) grid[i][j] = OBSTACLE;
        else if(bitSet(config, i * grid.length + j)) grid[i][j] = ROOK;
      }
    }
    return grid;
  }

  static boolean bitSet(int n, int i){
    return ((1 << i) & n) != 0;
  }

  static final int ROOK = 1;
  static final int OBSTACLE = 2;
  static boolean gridValid(int[][] grid){
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        if(grid[i][j] == ROOK && rookUnderAttack(grid, i, j)) return false;
      }
    }
    return true;
  }

  static boolean rookUnderAttack(int[][] grid, int i, int j){
    // check up
    for(int row = i - 1; row >= 0; row--){
      if(grid[row][j] == ROOK) return true;
      else if(grid[row][j] == OBSTACLE) break;
    }
    // check down
    for(int row = i + 1; row < grid.length; row++){
      if(grid[row][j] == ROOK) return true;
      else if(grid[row][j] == OBSTACLE) break;
    }
    // check left
    for(int col = j - 1; col >= 0; col--){
      if(grid[i][col] == ROOK) return true;
      else if(grid[i][col] == OBSTACLE) break;
    }
    // check right
    for(int col = j + 1; col < grid.length; col++){
      if(grid[i][col] == ROOK) return true;
      else if(grid[i][col] == OBSTACLE) break;
    }
    return false;
  }
}
