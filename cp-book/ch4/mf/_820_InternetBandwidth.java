import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 820 Internet Bandwidth
Problem url: https://uva.onlinejudge.org/external/8/820.pdf
Author: Andrey Yemelyanov
*/
public class _820_InternetBandwidth {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int network = 1;
    while(s.hasNext()){
      int V = s.nextInt();
      if(V == 0) break;
      int[][] res = new int[V][V];
      int source = s.nextInt() - 1; int sink = s.nextInt() - 1;
      int E = s.nextInt();
      for(int i = 0; i < E; i++){
        int u = s.nextInt() - 1; int v = s.nextInt() - 1;
        int weight = s.nextInt();
        res[u][v] += weight;
        res[v][u] += weight;
      }
      int maxFlow = maxFlow(res, source, sink);
      System.out.printf("Network %d\nThe bandwidth is %d.\n\n", network++, maxFlow);
    }
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
