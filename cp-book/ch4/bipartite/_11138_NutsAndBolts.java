import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11138 Nuts and Bolts
Problem url: https://uva.onlinejudge.org/external/111/11138.pdf
Author: Andrey Yemelyanov
*/
public class _11138_NutsAndBolts {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int nBolts = s.nextInt(); int nNuts = s.nextInt();
      int[][] adjMatrix = new int[nBolts][nNuts];
      for(int i = 0; i < nBolts; i++){
        for(int j = 0; j < nNuts; j++){
          adjMatrix[i][j] = s.nextInt();
        }
      }
      int source = 0; int sink = nBolts + nNuts + 1;
      int mcbm = maxFlow(buildResidualGraph(adjMatrix, nNuts, nBolts), source, sink);
      System.out.printf("Case %d: a maximum of %d nuts and bolts can be fitted together\n", c, mcbm);
    }
  }

  static int[][] buildResidualGraph(int[][] adjMatrix, int nNuts, int nBolts){
    int V = nNuts + nBolts + 2;
    int source = 0; int sink = V - 1;
    int[][] res = new int[V][V];
    // connect source to all bolts
    for(int bolt = 1; bolt <= nBolts; bolt++){
      res[source][bolt] = 1;
    }
    // connect all nuts to sink
    for(int nut = nBolts + 1; nut <= V - 2; nut++){
      res[nut][sink] = 1;
    }
    // connect bolts and nuts
    for(int bolt = 1; bolt <= nBolts; bolt++){
      for(int nut = nBolts + 1; nut <= V - 2; nut++){
        if(adjMatrix[bolt - 1][nut - nBolts - 1] == 1) res[bolt][nut] = 1;
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
