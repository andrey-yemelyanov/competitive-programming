import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11283 Playing Boggle
Problem url: https://uva.onlinejudge.org/external/112/11283.pdf
Author: Andrey Yemelyanov
*/
public class _11283 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    for(int game = 1; game <= nTests; game++){
      s.nextLine();
      char[][] grid = new char[4][4];
      for(int i = 0; i < grid.length; i++){
        String line = s.next();
        for(int j = 0; j < grid.length; j++){
          grid[i][j] = line.charAt(j);
        }
      }
      int nWords = s.nextInt();
      Set<String> dict = new HashSet<>();
      while(nWords-- > 0) dict.add(s.next());
      System.out.printf("Score for Boggle game #%d: %d\n", game, totalPoints(grid, dict));
    }
  }

  static int totalPoints(char[][] grid, Set<String> dict){
    int totalPoints = 0;
    for(String word : dict){
      if(containsWord(grid, word)){
        totalPoints += points[word.length()];
      }
    }
    return totalPoints;
  }

  static final int[] points = new int[]{0,0,0,1,1,2,3,5,11,11,11,11,11,11,11,11,11};
  static final int[][] dirs = new int[][]{
    {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1,  1}, {0, 1}, {1, 1}, {-1, 0}
  };

  static boolean containsWord(char[][] grid, String word){
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid.length; j++){
        if(containsWord(grid, new boolean[4][4], i, j, word, "")) return true;
      }
    }
    return false;
  }

  static boolean containsWord(char[][] grid, boolean[][] used, int row, int col, String word, String current){
    if(current.equals(word)) return true;
    if(!word.startsWith(current)) return false;
    if(row < 0 || row == grid.length || col < 0 || col == grid.length) return false;
    if(used[row][col]) return false;
    used[row][col] = true;
    for(int[] dir : dirs){
      if(containsWord(grid, used, row + dir[0], col + dir[1], word, current + grid[row][col])) return true;
    }
    used[row][col] = false;
    return false;
  }
}
