import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10401 Injured Queen Problem
Problem url: https://uva.onlinejudge.org/external/104/10401.pdf
Author: yemelyanov
*/
public class _10401 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String line = s.next();
      int dim = line.length();
      int[][] board = new int[dim][dim];
      int[] constraints = new int[dim];
      for(int i = 0; i < dim; i++){
        constraints[i] = UNDEF;
        char c = line.charAt(i);
        if(c != '?'){
          constraints[i] = toRow(dim, c);
        }
      }
      System.out.println(countArrangements(board, constraints));
    }
  }

  static int toRow(int dim, char c){
    return dim - Character.digit(c, 16);
  }

  static long countArrangements(int[][] board, int[] constraints){
    memo = new long[board.length][board[0].length];
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        memo[i][j] = UNDEF;
      }
    }
    return count(0, board, constraints);
  }

  static long[][] memo;
  static long count(int col, int[][] board, int[] constraints){
    if(col == board[0].length) return 1;
    long nPaths = 0;
    for(int row : validRows(col, board, constraints)){
      if(memo[row][col] != UNDEF) {
        nPaths += memo[row][col];
        continue;
      }
      board[row][col] = QUEEN;
      long n = count(col + 1, board, constraints);
      nPaths += n;
      board[row][col] = EMPTY;
      memo[row][col] = n;
    }
    return nPaths;
  }

  static List<Integer> validRows(int col, int[][] board, int[] constraints){
    List<Integer> rows = new ArrayList<>();
    if(constraints[col] != UNDEF){
      if(valid(constraints[col], col, board)) {
        rows.add(constraints[col]);
        return rows;
      }
    }
    else{
      for(int row = 0; row < board.length; row++){
          if(valid(row, col, board)){
            rows.add(row);
          }
      }
    }
    return rows;
  }

  static final int EMPTY = 0;
  static final int QUEEN = 1;
  static final int UNDEF = -1;
  static boolean valid(int row, int col, int[][] board){
    if(col == 0) return true;
    if(row - 1 >= 0 && board[row - 1][col - 1] == QUEEN) return false;
    if(board[row][col - 1] == QUEEN) return false;
    if(row + 1 < board.length && board[row + 1][col - 1] == QUEEN) return false;
    return true;
  }
}
