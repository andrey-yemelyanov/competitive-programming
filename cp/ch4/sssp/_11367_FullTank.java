package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class _11367_FullTank{
  public static void main(String[] args) throws Exception{
    String data = "5 5\r\n10 10 20 12 13\r\n0 1 9\r\n0 2 8\r\n1 2 1\r\n1 3 11\r\n2 3 7\r\n2\r\n10 0 3\r\n20 1 4\r\n";
    Scanner s = new Scanner(new File("FullTankTest.txt"));
    while(s.hasNext()){
      int n = s.nextInt(); int m = s.nextInt();
      int[] fuelPrice = new int[n];
      for(int i = 0; i < fuelPrice.length; i++){
        fuelPrice[i] = s.nextInt();
      }
      Map<Integer, List<Edge>> graph = new HashMap<>();
      for(int i = 0; i < n; i++){
        graph.put(i, new ArrayList<Edge>());
      }
      for(int i = 0; i < m; i++){
        int u = s.nextInt(); int v = s.nextInt(); int d = s.nextInt();
        graph.get(u).add(new Edge(v, d));
        graph.get(v).add(new Edge(u, d));
      }
      int q = s.nextInt();
      while(q-- > 0){
        int tankCapacity = s.nextInt(); int source = s.nextInt(); int target = s.nextInt();
        int distance = dijkstra(source, target, graph, tankCapacity, fuelPrice);
        if(distance == INF) System.out.println("impossible");
        else System.out.println(distance);
      }
    }
  }

  static class Edge{
    public int vertex;
    public int cost;
    public Edge(int vertex, int cost){
      this.vertex = vertex; this.cost = cost;
    }
  }

  static int toInt(int vertex, int fuel){
    return (fuel << 10) + vertex;
  }

  static int vertex(int state){
    return state & ((1 << 10) - 1);
  }

  static int fuel(int state){
    return state >> 10;
  }

  static final int INF = Integer.MAX_VALUE;
  static int[] dist = new int[(int)pow(2, 17)]; // 17 bits are used for storing each state
  static int dijkstra(int source, int target, Map<Integer, List<Edge>> graph,
    int tankCapacity, int[] fuelPrice){
    int initialState = toInt(source, 0);
    for(int i = 0; i < dist.length; i++){
      dist[i] = INF;
    }
    dist[initialState] = 0;
    PriorityQueue<Edge> pq = new PriorityQueue<>(10, new Comparator<Edge>(){
      @Override
      public int compare(Edge e1, Edge e2){
        return Integer.compare(e1.cost, e2.cost);
      }
    });
    pq.add(new Edge(initialState, 0));
    while(!pq.isEmpty()){
      Edge e = pq.remove();
      if(vertex(e.vertex) == target) return dist[e.vertex];
      if(e.cost > dist[e.vertex]) continue;
      int fuelAvailable = fuel(e.vertex);
      int vertex = vertex(e.vertex);
      // add states by fueling at the current vertex
      if(fuelAvailable < tankCapacity){
        int nextState = toInt(vertex, fuelAvailable + 1);
        int weight = fuelPrice[vertex];
        if(dist[e.vertex] + fuelPrice[vertex] < dist[nextState]){
          dist[nextState] = dist[e.vertex] + fuelPrice[vertex];
          pq.add(new Edge(nextState, dist[nextState]));
        }
      }
      // travel to all neighbors if enough fuel
      for(Edge next : graph.get(vertex)){
        int distance = next.cost;
        if(fuelAvailable >= distance){
          int nextState = toInt(next.vertex, fuelAvailable - distance);
          int weight = 0;
          if(dist[e.vertex] + weight < dist[nextState]){
            dist[nextState] = dist[e.vertex] + weight;
            pq.add(new Edge(nextState, dist[nextState]));
          }
        }
      }
    }
    return INF;
  }
}
