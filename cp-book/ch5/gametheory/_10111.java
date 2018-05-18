import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10111 Find the Winning Move
Problem url: https://uva.onlinejudge.org/external/101/10111.pdf
Author: Andrey Yemelyanov
*/
public class _10111 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String sep = s.next();
      if(sep.equals("$")) break;
      s.nextLine();
      int[][] board = new int[4][4];
      for(int i = 0; i < board.length; i++){
        String line = s.nextLine();
        for(int j = 0; j < board.length; j++){
          if(line.charAt(j) == 'x') board[i][j] = X;
          else if(line.charAt(j) == 'o') board[i][j] = O;
        }
      }
      int[] winningMove = getWinningMove(board);
      if(winningMove == null) System.out.println("#####");
      else System.out.printf("(%d,%d)\n", winningMove[0], winningMove[1]);
    }
  }

  static int[] getWinningMove(int[][] board){
    int nEmptyCells = 16;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] != EMPTY) nEmptyCells--;
      }
    }
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] == EMPTY){
          board[i][j] = X;
          if(play(board, O, nEmptyCells - 1) == 10) return new int[] {i, j};
          board[i][j] = EMPTY;
        }
      }
    }
    return null;
  }

  static void printBoard(int[][] board){
    for(int i = 0; i < board.length; i++){
      System.out.println(Arrays.toString(board[i]));
    }
  }

  static int play(int[][] board, int turn, int nEmptyCells){
    if(playerWins(board, X)) return 10;
    if(playerWins(board, O)) return -10;
    if(nEmptyCells == 0) return 0;

    int bestScore = turn == X ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] == EMPTY){
          if(turn == X){
            board[i][j] = X;
            int score = play(board, O, nEmptyCells - 1);
            board[i][j] = EMPTY;
            if(score == 10) return score; // prune as soon as we find a guaranteed win
            bestScore = max(bestScore, score);
          }else if(turn == O){
            board[i][j] = O;
            int score = play(board, X, nEmptyCells - 1);
            board[i][j] = EMPTY;
            if(score == -10) return score; // prune as soon as we find a guaranteed loss
            bestScore = min(bestScore, score);
          }
        }
      }
    }
    return bestScore;
  }

  static boolean playerWins(int[][] board, int player){
    // check all rows
    for(int i = 0; i < board.length; i++){
      boolean win = true;
      for(int j = 0; j < board.length; j++){
        if(board[i][j] != player){
          win = false;
          break;
        }
      }
      if(win) return true;
    }
    // check all columns
    for(int j = 0; j < board.length; j++){
      boolean win = true;
      for(int i = 0; i < board.length; i++){
        if(board[i][j] != player){
          win = false;
          break;
        }
      }
      if(win) return true;
    }
    // check diagonals
    int i = 0; int j = 0; boolean win = true;
    while(i < board.length && j < board.length){
      if(board[i++][j++] != player){
        win = false;
        break;
      }
    }
    if(win) return true;
    i = board.length - 1; j = 0; win = true;
    while(i >= 0 && j < board.length){
      if(board[i--][j++] != player){
        win = false;
        break;
      }
    }
    return win;
  }

  static final int X = 1;
  static final int O = 2;
  static final int EMPTY = 0;
}
