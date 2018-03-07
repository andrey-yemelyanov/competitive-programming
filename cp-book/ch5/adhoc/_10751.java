import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10751 Chessboard
Problem url: https://uva.onlinejudge.org/external/107/10751.pdf
Author: Andrey Yemelyanov
*/
public class _10751 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      double maxLen = sqrt(2) * pow(n - 2, 2) + 4 * (n - 1);
      if(n == 1) maxLen = 0;
      System.out.printf("%.3f\n", maxLen);
      if(nTests > 0) System.out.println();
    }
  }

  static void bruteForce(){
    /*
      n   S   D
      2   4   0
      3   8   1
      4  12   4
      5  16   9
      6  20  16

      #S = 4 * (n - 1)
      #D = (n -2)^2
      Derived formula: sqrt(2)*#D + #S
    */
    printSolution(2);
    printSolution(3);
    printSolution(4);
    printSolution(5);
    printSolution(6);
  }

  static void printSolution(int n){
    double[] solution = solve(0, 0, new boolean[n][n], 0, 0, 0, "", new boolean[n][n][n][n]);
    System.out.printf("n=%d len=%f S=%d D=%d\n", n, solution[0], (int)solution[1], (int)solution[2]);
  }

  static double[] solve(int i, int j, boolean[][] grid, double pathLen, int nS, int nD, String indent, boolean[][][][] lines){
    if(i == 0 && j == 0 && grid[i][j]) {
      //System.out.println(indent + "Return=" + pathLen + " S=" + nS + " D=" + nD);
      return new double[]{pathLen, nS, nD};
    }
    int[] dr = new int[]{ 0, -1, -1, -1, 0, 1, 1,  1};
    int[] dc = new int[]{-1, -1,  0,  1, 1, 1, 0, -1};
    double[] bestResult = new double[]{0.0,0,0};
    for(int k = 0; k < 8; k++){
      int row = i + dr[k]; int col = j + dc[k];
      if(row < 0 || row >= grid.length || col < 0 || col >= grid.length || grid[row][col]) continue;
      if(lines[row][col][i][j]) continue;
      int newNS = nS; int newND = nD; double len = 0;
      if(dr[k] != 0 && dc[k] != 0){ // move diagonally
        // ensure we are not crossing a path
        if(row == i - 1 && col == j - 1 && lines[i][j - 1][i - 1][j]) continue;
        if(row == i - 1 && col == j + 1 && lines[i - 1][j][i][j + 1]) continue;
        if(row == i + 1 && col == j + 1 && lines[i][j + 1][i + 1][j]) continue;
        if(row == i + 1 && col == j - 1 && lines[i + 1][j][i][j - 1]) continue;
        len = sqrt(2.0);
        newND++;
      }else{ // move in straight line
        len = 1.0;
        newNS++;
      }
      grid[row][col] = true;
      lines[row][col][i][j] = true;
      lines[i][j][row][col] = true;
      //System.out.println(indent + "Row=" + row + " Col=" + col + " PathLen=" + (pathLen + len));
      double[] result = solve(row, col, grid, pathLen + len, newNS, newND, indent + "  ", lines);
      if(result[0] > bestResult[0]){
        bestResult = result;
      }
      grid[row][col] = false;
      lines[row][col][i][j] = false;
      lines[i][j][row][col] = false;
    }
    return bestResult;
  }
}
