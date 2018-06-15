import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 422 Word-Search Wonder
Problem url: https://uva.onlinejudge.org/external/4/422.pdf
Author: Andrey Yemelyanov
*/
public class _422 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int L = s.nextInt();
    char[][] grid = new char[L][L];
    for(int i = 0; i < grid.length; i++){
      String row = s.next();
      for(int j = 0; j < grid.length; j++){
        grid[i][j] = row.charAt(j);
      }
    }
    while(s.hasNext()){
      String word = s.next();
      if(word.equals("0")) break;
      int[] result = search(word, grid);
      if(result == null) System.out.println("Not found");
      else System.out.printf("%d,%d %d,%d\n", result[0] + 1, result[1] + 1, result[2] + 1, result[3] + 1);
    }
  }

  static int[] search(String word, char[][] grid){
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        for(int[] dir : dirs){
          if(search(word, grid, i, j, dir)){
            int k = i + dir[0] * (word.length() - 1);
            int l = j + dir[1] * (word.length() - 1);
            return new int[]{i, j, k, l};
          }
        }
      }
    }
    return null;
  }

  static final int[][] dirs = new int[][]{
    {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1,  1}, {0, 1}, {1, 1}
  };
  static boolean search(String word, char[][] grid, int row, int col, int[] dir){
    for(int i = 0; i < word.length(); i++){
      if(row < 0 || row == grid.length || col < 0 || col == grid.length) return false;
      if(grid[row][col] != word.charAt(i)) return false;
      row += dir[0];
      col += dir[1];
    }
    return true;
  }
}
