import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10805 Cockroach Escape Networks
Problem url: https://uva.onlinejudge.org/external/108/10805.pdf
Author: Andrey Yemelyanov
*/
public class _10805_CockroachEscapeNetworks {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt(); int m = s.nextInt();
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for(int i = 0; i < m; i++){
        int u = s.nextInt(); int v = s.nextInt();
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v); graph.get(v).add(u);
      }
      int mstDiameter = mstDiameter(graph);
      System.out.printf("Case #%d:\n%d\n\n", c, mstDiameter);
    }
  }

  static int mstDiameter(Map<Integer, List<Integer>> graph){
    int mstDiameter = INF;
    for(int v : graph.keySet()){
      int[] dist = bfs(graph, Arrays.asList(v));
      int maxDist = Arrays.stream(dist)
                          .max()
                          .getAsInt();
      int diameter = 2 * maxDist;
      mstDiameter = min(mstDiameter, diameter);
    }

    for(int u : graph.keySet()){
      for(int v : graph.get(u)){
        int[] dist = bfs(graph, Arrays.asList(u, v));
        int maxDist = Arrays.stream(dist)
                            .max()
                            .getAsInt();
        int diameter = 2 * maxDist + 1;
        mstDiameter = min(mstDiameter, diameter);
      }
    }
    return mstDiameter;
  }

  static final int INF = 1000000;
  static int[] bfs(Map<Integer, List<Integer>> graph, List<Integer> sources){
    int[] dist = new int[graph.size()];
    for(int i = 0; i < dist.length; i++){
      dist[i] = INF;
    }
    Queue<Integer> q = new LinkedList<>();
    for(int s : sources){
      dist[s] = 0;
      q.add(s);
    }
    while(!q.isEmpty()){
      int u = q.remove();
      for(int v : graph.get(u)){
        if(dist[v] == INF){
          q.add(v);
          dist[v] = dist[u] + 1;
        }
      }
    }
    return dist;
  }
}
