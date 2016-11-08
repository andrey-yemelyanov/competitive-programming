import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.io.*;

/*
Problem name: 12047 Highest Paid Toll
Problem url: https://uva.onlinejudge.org/external/120/12047.pdf
Author: Andrey Yemelyanov
*/
public class _12047_HighestPaidToll {
  static class Edge{
    public int to;
    public int cost;
    public Edge(int to, int cost){
      this.to = to; this.cost = cost;
    }
  }
  static class Vertex{
    public int id;
    public int dist;
    public Vertex(int id, int dist){this.id = id; this.dist = dist;}
  }
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int nTests = Integer.parseInt(reader.readLine());
    while(nTests-- > 0){
      String[] line = reader.readLine().split("\\s+");
      int N = Integer.parseInt(line[0]); int M = Integer.parseInt(line[1]); int source = Integer.parseInt(line[2]); int dest = Integer.parseInt(line[3]); int p = Integer.parseInt(line[4]);
      Map<Integer, List<Edge>> graph = new HashMap<>();
      for(int v = 1; v <= N; v++) graph.put(v, new ArrayList<>());
      for(int i = 0; i < M; i++){
        line = reader.readLine().split("\\s+");
        int u = Integer.parseInt(line[0]); int v = Integer.parseInt(line[1]); int c = Integer.parseInt(line[2]);
        graph.get(u).add(new Edge(v, c));
      }
      int heaviestEdge = heaviestEdge(graph, source, dest, p);
      System.out.println(heaviestEdge);
    }
  }

  static int heaviestEdge(Map<Integer, List<Edge>> graph, int source, int dest, int maxPathWeight){
    int[] distFromSourceTo = new int[graph.size() + 1];
    int[] distToDestFrom = new int[graph.size() + 1];
    dijkstra(source, graph, distFromSourceTo);
    dijkstra(dest, invertGraph(graph), distToDestFrom);
    int heaviestEdge = -1;
    for(int u : graph.keySet()){
      for(Edge outEdge : graph.get(u)){
        int v = outEdge.to;
        int weight = outEdge.cost;
        if(distFromSourceTo[u] + weight + distToDestFrom[v] <= maxPathWeight){
          heaviestEdge = max(heaviestEdge, weight);
        }
      }
    }
    return heaviestEdge;
  }

  static Map<Integer, List<Edge>> invertGraph(Map<Integer, List<Edge>> graph){
    Map<Integer, List<Edge>> invertedGraph = new HashMap<>();
    for(int v : graph.keySet()) invertedGraph.put(v, new ArrayList<>());
    for(int u : graph.keySet()){
      for(Edge v : graph.get(u)){
        invertedGraph.get(v.to).add(new Edge(u, v.cost));
      }
    }
    return invertedGraph;
  }

  static final int INF = 100000000;
  static final int MAX_VERTICES = 10000;
  static void dijkstra(int source, Map<Integer, List<Edge>> graph, int[] dist){
    for(int i = 0; i < dist.length; i++){
      dist[i] = INF;
    }
    dist[source] = 0;
    PriorityQueue<Vertex> pq = new PriorityQueue<>(10, new Comparator<Vertex>(){
      @Override
      public int compare(Vertex v1, Vertex v2){
        return Integer.compare(v1.dist, v2.dist);
      }
    });
    pq.add(new Vertex(source, 0));
    while(!pq.isEmpty()){
      Vertex vertex = pq.remove();
      int u = vertex.id;
      if(vertex.dist > dist[u]) continue;
      for(Edge outEdge : graph.get(u)){
        int altDist = dist[u] + outEdge.cost;
        if(altDist < dist[outEdge.to]){
          dist[outEdge.to] = altDist;
          pq.add(new Vertex(outEdge.to, altDist));
        }
      }
    }
  }
}
