import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10502 Counting Rectangles
Problem url: https://uva.onlinejudge.org/external/105/10502.pdf
Author: Andrey Yemelyanov
*/
public class _10502_CountingRectangles {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break; 
      int m = s.nextInt();
      int[][] board = new int[n][m];
      for(int i = 0; i < n; i++){
        char[] row = s.next().toCharArray();
        for(int j = 0; j < m; j++){
          board[i][j] = Character.getNumericValue(row[j]);
        }
      }
      System.out.println(countRectangles(board));
    }
  }

  static boolean isRectangle(int[][] board, int startRow, int startCol, int endRow, int endCol){
    for(int i = startRow; i <= endRow; i++){
      for(int j = startCol; j <= endCol; j++){
        if(board[i][j] != 1) return false;
      }
    }
    return true;
  }

  static int countRectangles(int[][] board){
    int nRects = 0;
    int nRows = board.length; int nCols = board[0].length;
    for(int startRow = 0; startRow < nRows; startRow++){
      for(int startCol = 0; startCol < nCols; startCol++){
        for(int endRow = startRow; endRow < nRows; endRow++){
          for(int endCol = startCol; endCol < nCols; endCol++){
            if(isRectangle(board, startRow, startCol, endRow, endCol)){
              nRects++;
            }
          }
        }
      }
    }
    return nRects;
  }
}
