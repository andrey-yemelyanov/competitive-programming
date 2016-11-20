import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11506 Angry Programmer
Problem url: https://uva.onlinejudge.org/external/115/11506.pdf
Author: Andrey Yemelyanov
*/
public class _11506_AngryProgrammer {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int M = s.nextInt(); int W = s.nextInt();
      if(M == 0 && W == 0) break;
      int[] vertexCapacity = new int[M];
      for(int i = 1; i < M - 1; i++){
        vertexCapacity[s.nextInt() - 1] = s.nextInt();
      }
      vertexCapacity[0] = LARGE; vertexCapacity[M - 1] = LARGE;
      int[][] incidentMatrix = new int[M][M];
      for(int i = 0; i < W; i++){
        int v1 = s.nextInt() - 1; int v2 = s.nextInt() - 1; int cost = s.nextInt();
        incidentMatrix[v1][v2] = cost; incidentMatrix[v2][v1] = cost;
      }
      int[][] res = buildResidualMatrix(incidentMatrix, vertexCapacity);
      int source = 0; int sink = res.length - 1;
      System.out.println(maxFlow(res, source, sink));
    }
  }

  static int out(int v){ return v * 2 + 1; }
  static int in(int v){ return v * 2; }

  static final int LARGE = 1000000;
  static int[][] buildResidualMatrix(int[][] incidentMatrix, int[] vertexCapacity){
    int V = incidentMatrix.length;
    int[][] res = new int[V * 2][V * 2];
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        if(i == j && (i == 0 || i == V - 1)){
          res[out(i)][in(i)] = LARGE;
          res[in(i)][out(i)] = LARGE;
        }else if(incidentMatrix[i][j] != 0){ // edge exists
          res[out(i)][in(j)] = incidentMatrix[i][j]; // edge weight
          res[in(j)][out(j)] = vertexCapacity[j]; // vertex capacity
        }
      }
    }
    return res;
  }

  static final int UNDEF = -1;
  static final int INF = 100000000;
  static int f;
  static void augment(int v, int source, int minEdge, int[][] res, int[] p){
    if(v == source){
      f = minEdge;
      return;
    }else if(p[v] != UNDEF){
      augment(p[v], source, min(minEdge, res[p[v]][v]), res, p);
      res[p[v]][v] -= f;
      res[v][p[v]] += f;
    }
  }

  static int maxFlow(int[][] res, int source, int sink){
    int V = res.length;
    int maxFlow = 0;
    while(true){
      f = 0;
      boolean[] visited = new boolean[V];
      visited[source] = true;
      Queue<Integer> q = new LinkedList<>();
      q.add(source);
      int[] p = new int[V]; for(int i = 0; i < V; i++) p[i] = UNDEF;
      while(!q.isEmpty()){
        int u = q.remove();
        if(u == sink) break;
        for(int v = 0; v < V; v++){
          if(res[u][v] > 0 && !visited[v]){
            visited[v] = true;
            q.add(v);
            p[v] = u;
          }
        }
      }
      augment(sink, source, INF, res, p);
      if(f == 0) break;
      maxFlow += f;
    }
    return maxFlow;
  }
}
