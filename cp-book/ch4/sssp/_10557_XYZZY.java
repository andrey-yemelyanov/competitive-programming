import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10557 XYZZY
Problem url: https://uva.onlinejudge.org/external/105/10557.pdf
Author: Andrey Yemelyanov
*/
public class _10557_XYZZY {
  static class Edge{
    public int to;
    public int cost;
    public Edge(int to, int cost){
      this.to = to; this.cost = cost;
    }
    public String toString(){
      return String.format("%d(cost=%d)", to, cost);
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int nRooms = s.nextInt(); if(nRooms == -1) break;
      Map<Integer, List<Edge>> graph = new HashMap<>();
      for(int i = 0; i <= nRooms; i++) graph.put(i, new ArrayList<>());
      final int STARTING_ENERGY = 100;
      graph.get(0).add(new Edge(1, STARTING_ENERGY));
      int[] energyLevelInRoom = new int[graph.size()];
      for(int i = 1; i <= nRooms; i++){
        energyLevelInRoom[i] = s.nextInt();
        int nOutEdges = s.nextInt();
        for(int j = 0; j < nOutEdges; j++){
          graph.get(i).add(new Edge(s.nextInt(), 0));
        }
      }

      for(int i = 1; i <= nRooms; i++){
        for(Edge outEdge : graph.get(i)){
          outEdge.cost = energyLevelInRoom[outEdge.to];
        }
      }

      //System.out.println(Arrays.toString(energyLevelInRoom));
      //System.out.println(graph);

      if(winnable(graph)) System.out.println("winnable");
      else System.out.println("hopeless");
    }
  }

  static boolean winnable(Map<Integer, List<Edge>> graph){
    int source = 0; int dest = graph.size() - 1;
    if(!pathExists(graph, source, dest)) return false;
    int[] dist = new int[graph.size()];
    for(int i = 0; i < dist.length; i++){
      dist[i] = INF;
    }
    dist[source] = 0;
    bellmanFord(graph, dist);
    //System.out.println(Arrays.toString(dist));
    return hasPositiveWeightCycle(graph, dist, dest) || dist[dest] > 0;
  }

  static boolean pathExists(Map<Integer, List<Edge>> graph, int from, int to){
    return pathExists(graph, from, to, new boolean[graph.size()]);
  }

  static boolean pathExists(Map<Integer, List<Edge>> graph, int from, int to, boolean[] visited){
    if(from == to) return true;
    visited[from] = true;
    for(Edge outEdge : graph.get(from)){
      if(!visited[outEdge.to] && pathExists(graph, outEdge.to, to, visited)) return true;
    }
    return false;
  }

  static void bellmanFord(Map<Integer, List<Edge>> graph, int[] dist){
    int V = graph.size();
    for(int i = 0; i < V - 1; i++){
      for(int u = 0; u < V; u++){
        for(Edge outEdge : graph.get(u)){
          int v = outEdge.to;
          int weight = outEdge.cost;
          if(u != 0 && dist[u] <= 0) continue;
          dist[v] = max(dist[v], dist[u] + weight);
        }
      }
    }
  }

  static final int INF = -1000000000;
  static boolean hasPositiveWeightCycle(Map<Integer, List<Edge>> graph, int[] dist, int dest){
    int V = graph.size();
    for(int u = 0; u < V; u++){
      for(Edge outEdge : graph.get(u)){
        int v = outEdge.to;
        int weight = outEdge.cost;
        if(dist[u] <= 0) continue;
        if(dist[v] < dist[u] + weight && pathExists(graph, u, dest)) return true;
      }
    }
    return false;
  }
}
