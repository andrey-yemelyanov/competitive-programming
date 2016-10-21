import java.util.*;
import static java.lang.Math.*;

public class _11463_Commandos {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 1; t <= nTests; t++){
      int N = s.nextInt(); int R = s.nextInt();
      int[][] dist = new int[N][N];
      for(int i = 0; i < dist.length; i++){
        for(int j = 0; j < dist.length; j++){
          if(i != j) dist[i][j] = INF;
        }
      }
      for(int i = 0; i < R; i++){
        int v1 = s.nextInt(); int v2 = s.nextInt();
        dist[v1][v2] = 1; dist[v2][v1] = 1;
      }
      int source = s.nextInt(); int dest = s.nextInt();
      floydWarshall(dist);
      int minTime = 0;
      for(int i = 0; i < N; i++){
        minTime = max(minTime, dist[source][i] + dist[i][dest]);
      }
      System.out.printf("Case %d: %d\n", t, minTime);
    }
  }

  static final int INF = 1000000;
  static void floydWarshall(int[][] dist){
    int V = dist.length;
    for(int k = 0; k < V; k++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
  }
}
