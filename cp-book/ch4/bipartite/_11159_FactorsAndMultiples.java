import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11159 Factors and Multiples
Problem url: https://uva.onlinejudge.org/external/111/11159.pdf
Author: Andrey Yemelyanov
*/
public class _11159_FactorsAndMultiples {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt();
      int[] A = new int[n];
      for(int i = 0; i < n; i++){
        A[i] = s.nextInt();
      }
      int m = s.nextInt();
      int[] B = new int[m];
      for(int i = 0; i < m; i++){
        B[i] = s.nextInt();
      }
      int[][] res = buildResidualGraph(A, B);
      int source = 0; int sink = res.length - 1;
      System.out.printf("Case %d: %d\n", c, maxFlow(res, source, sink));
    }
  }

  static int[][] buildResidualGraph(int[] A, int[] B){
    int V = A.length + B.length + 2;
    int source = 0; int sink = V - 1;
    int[][] res = new int[V][V];
    // connect source to all elements in A
    for(int a = 1; a <= A.length; a++){
      res[source][a] = 1;
    }
    // connect all elements in B to sink
    for(int b = A.length + 1; b <= V - 2; b++){
      res[b][sink] = 1;
    }
    // connect elements in A to elements in B
    for(int a = 1; a <= A.length; a++){
      for(int b = A.length + 1; b <= V - 2; b++){
        if(isMultipleOf(B[b - A.length - 1], A[a - 1])) res[a][b] = 1;
      }
    }
    return res;
  }

  static boolean isMultipleOf(int b, int a){
    if(a == 0 && b == 0) return true;
    if(a == 0) return false;
    return b % a == 0;
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
