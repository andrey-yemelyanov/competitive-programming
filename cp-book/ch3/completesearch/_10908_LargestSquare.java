import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10908 Largest Square
Problem url: https://uva.onlinejudge.org/external/109/10908.pdf
Author: Andrey Yemelyanov
*/
public class _10908_LargestSquare {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int M = s.nextInt(); int N = s.nextInt(); int Q = s.nextInt();
      char[][] board = new char[M][N];
      for(int i = 0; i < M; i++){
        board[i] = s.next().toCharArray();
      }
      System.out.printf("%d %d %d\n", M, N, Q);
      for(int i = 0; i < Q; i++){
        System.out.println(largestSquareSize(board, s.nextInt(), s.nextInt()));
      }
    }
  }

  static int largestSquareSize(char[][] board, int r, int c){
    int largestSquareSize = 1;
    int startRow = r;
    int startCol = c;
    int endRow = r;
    int endCol = c;
    while(true){
      if(startRow < 0 || startCol < 0 || endRow >= board.length || endCol >= board[0].length) break;
      if(allCharsEqual(board, startRow, startCol, endRow, endCol)){
        largestSquareSize = endRow - startRow + 1;
      }else break;
      startRow--;
      startCol--;
      endRow++;
      endCol++;
    }
    return largestSquareSize;
  }

  static boolean allCharsEqual(char[][] board, int startRow, int startCol, int endRow, int endCol){
    Set<Character> s = new HashSet<>();
    for(int i = startRow; i <= endRow; i++){
      for(int j = startCol; j <= endCol; j++){
        s.add(board[i][j]);
      }
    }
    return s.size() == 1;
  }
}
