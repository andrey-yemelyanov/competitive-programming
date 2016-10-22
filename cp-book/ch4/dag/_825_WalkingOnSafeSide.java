import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

public class _825_WalkingOnSafeSide {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int W = s.nextInt(); int N = s.nextInt(); s.nextLine();
      int[][] map = new int[W][N];
      for(int k = 0; k < W; k++){
        String[] tokens = s.nextLine().split("\\s+");
        int i = Integer.parseInt(tokens[0]) - 1;
        for(int t = 1; t < tokens.length; t++){
          int j = Integer.parseInt(tokens[t]) - 1;
          map[i][j] = BLOCKED;
        }
      }
      System.out.println(countPaths(map));
      if(nTests > 0) System.out.println();
    }
  }

  static int countPaths(int[][] map){
    memo = new int[map.length][map[0].length];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        memo[i][j] = INF;
      }
    }
    return countPaths(0, 0, map);
  }

  static final int BLOCKED = 1;
  static final int INF = Integer.MAX_VALUE;
  static int[][] memo;
  static int countPaths(int i, int j, int[][] map){
    if(i >= map.length || j >= map[0].length || map[i][j] == BLOCKED) return 0;
    if(i == map.length - 1 && j == map[0].length - 1) return 1;
    if(memo[i][j] != INF) return memo[i][j];
    int nPaths = countPaths(i, j + 1, map) + countPaths(i + 1, j, map);
    memo[i][j] = nPaths;
    return nPaths;
  }
}
