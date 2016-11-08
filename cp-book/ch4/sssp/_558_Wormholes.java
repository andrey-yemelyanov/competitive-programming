import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 558 Wormholes
Problem url: https://uva.onlinejudge.org/external/5/558.pdf
Author: Andrey Yemelyanov
*/
public class _558_Wormholes {
  static class Edge{
    public int to;
    public int cost;
    public Edge(int to, int cost){
      this.to = to; this.cost = cost;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt(); int m = s.nextInt();
      Map<Integer, List<Edge>> graph = new HashMap<>();
      for(int i = 0; i < n; i++){
        graph.put(i, new ArrayList<>());
      }
      for(int i = 0; i < m; i++){
        int x = s.nextInt(); int y = s.nextInt(); int t = s.nextInt();
        graph.get(x).add(new Edge(y, t));
      }
      if(hasNegativeWeightCycle(graph)) System.out.println("possible");
      else System.out.println("not possible");
    }
  }

  static final int INF = 1000000000;
  static boolean hasNegativeWeightCycle(Map<Integer, List<Edge>> graph){
    int V = graph.size();
    int[] dist = new int[V];
    for(int i = 0; i < dist.length; i++){
      dist[i] = INF;
    }
    dist[0] = 0;
    for(int i = 0; i < V - 1; i++){
      for(int u = 0; u < V; u++){
        for(Edge outEdge : graph.get(u)){
          int v = outEdge.to;
          int weight = outEdge.cost;
          dist[v] = min(dist[v], dist[u] + weight);
        }
      }
    }

    for(int u = 0; u < V; u++){
      for(Edge outEdge : graph.get(u)){
        int v = outEdge.to;
        int weight = outEdge.cost;
        if(dist[v] > dist[u] + weight) return true;
      }
    }

    return false;
  }
}
