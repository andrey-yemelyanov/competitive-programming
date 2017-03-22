import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12187 Brothers
Problem url: https://uva.onlinejudge.org/external/121/12187.pdf
Author: Andrey Yemelyanov
*/
public class _12187_Brothers {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(true){
      int N = s.nextInt(); int R = s.nextInt(); int C = s.nextInt(); int K = s.nextInt();
      if(N == 0 && R == 0 && C == 0 && K == 0) break;
      int[][] kingdom = new int[R][C];
      for(int i = 0; i < R; i++){
        for(int j = 0; j < C; j++){
          kingdom[i][j] = s.nextInt();
        }
      }
      System.out.println(toString(doWar(kingdom, N, K)));
    }
  }

  static String toString(int[][] kingdom){
    int R = kingdom.length;
    int C = kingdom[0].length;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < R; i++){
      for(int j = 0; j < C; j++){
        sb.append(kingdom[i][j]);
        if(j < C - 1) sb.append(" ");
      }
      if(i < R - 1) sb.append("\n");
    }
    return sb.toString();
  }

  static int[][] doWar(int[][] kingdom, int nHeirs, int nBattles){
    while(nBattles-- > 0){
      kingdom = doBattle(kingdom, nHeirs);
    }
    return kingdom;
  }

  static int[][] doBattle(int[][] kingdom, int nHeirs){
    int R = kingdom.length;
    int C = kingdom[0].length;
    int[][] newKingdom = new int[R][];
    for(int i = 0; i < R; i++){
      newKingdom[i] = Arrays.copyOf(kingdom[i], kingdom[i].length);
    }

    int[] dr = new int[] {-1, 0, 1 ,0};
    int[] dc = new int[] {0, 1, 0, -1};
    for(int i = 0; i < R; i++){
      for(int j = 0; j < C; j++){
        int nextHeir = (kingdom[i][j] + 1) % nHeirs;
        for(int k = 0; k < 4; k++){
          int neighborRow = i + dr[k];
          int neighborCol = j + dc[k];
          if(neighborRow >= 0 && neighborRow < R && neighborCol >= 0 && neighborCol < C && kingdom[neighborRow][neighborCol] == nextHeir){
            newKingdom[neighborRow][neighborCol] = kingdom[i][j];
          }
        }
      }
    }
    return newKingdom;
  }
}
