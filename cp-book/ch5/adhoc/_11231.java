import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11231 Black and white painting
Problem url: https://uva.onlinejudge.org/external/112/11231.pdf
Author: Andrey Yemelyanov
*/
public class _11231 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      int m = s.nextInt();
      int c = s.nextInt();
      if(n == 0 && m == 0 && c == 0) break;
      System.out.println(countBoards(n, m, c == 1));
    }
  }

  static long countBoards(int n, int m, boolean bottomRightCornerIsWhite){
    final int boardSize = 8;
    int nRows = n - boardSize + 1;
    int nWhiteRows = bottomRightCornerIsWhite ? (int)ceil(nRows / 2.0) : nRows / 2;
    int nBlackRows = nRows - nWhiteRows;
    int nBoardsPerWhiteRow = (int)ceil((m - boardSize + 1) / 2.0);
    int nBoardsPerBlackRow = (m - boardSize + 1) / 2;
    return nBoardsPerWhiteRow * nWhiteRows + nBoardsPerBlackRow * nBlackRows;
  }
}
