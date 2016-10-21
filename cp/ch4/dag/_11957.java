import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11957 Checkers
Problem url: https://uva.onlinejudge.org/external/119/11957.pdf
Author: yemelyanov
*/
public class _11957 {
  static final int WHITE = 2;
  static final int BLACK = 1;
  static final int EMPTY = 0;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int test = 1; test <= nTests; test++){
      int N = s.nextInt(); s.nextLine();
      int whiteRow = 0, whiteCol = 0;
      int[][] board = new int[N][N];
      for(int i = 0; i < N; i++){
        char[] line = s.nextLine().toCharArray();
        for(int j = 0; j < N; j++){
          if(line[j] == 'B') board[i][j] = BLACK;
          else if(line[j] == 'W') {
            board[i][j] = WHITE;
            whiteRow = i; whiteCol = j;
          }
        }
      }
      memo = new long[N][N];
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
          memo[i][j] = UNDEF;
        }
      }
      System.out.printf("Case %d: %d\n", test, countPaths(board, whiteRow, whiteCol));
    }
  }

  static final int MOD = 1000007;
  static long[][] memo;
  static final int UNDEF = -1;
  static long countPaths(int[][] board, int row, int col){
    if(row == 0) return 1;
    if(memo[row][col] != UNDEF) return memo[row][col];
    long nPaths = 0;
    if(valid(board, row - 1, col - 1)){
      if(board[row - 1][col - 1] == EMPTY) nPaths += countPaths(board, row - 1, col - 1) % MOD;
      else if(valid(board, row - 2, col - 2) && board[row - 2][col - 2] == EMPTY) nPaths += countPaths(board, row - 2, col - 2) % MOD;
    }
    if(valid(board, row - 1, col + 1)){
      if(board[row - 1][col + 1] == EMPTY) nPaths += countPaths(board, row - 1, col + 1) % MOD;
      else if(valid(board, row - 2, col + 2) && board[row - 2][col + 2] == EMPTY) nPaths += countPaths(board, row - 2, col + 2) % MOD;
    }
    nPaths %= MOD;
    memo[row][col] = nPaths;
    return nPaths;
  }

  static boolean valid(int[][] board, int row, int col){
    return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
  }
}
