package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _10801_LiftHopping{
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
  public static void main(String[] args){
    String data = "2 30\r\n10 5\r\n0 1 3 5 7 9 11 13 15 20 99\r\n4 13 15 19 20 25 30\r\n2 30\r\n10 1\r\n0 5 10 12 14 20 25 30\r\n2 4 6 8 10 12 14 22 25 28 29\r\n3 50\r\n10 50 100\r\n0 10 30 40\r\n0 20 30\r\n0 20 50\r\n1 1\r\n2\r\n0 2 4 6 8 10";
    String data2 = "3 14\r\n12 34 35\r\n0 12 23\r\n8 14\r\n9 12\r\n3 14\r\n3 2 1\r\n0 14\r\n0 14\r\n0 14\r\n3 14\r\n3 2 1\r\n0 1 2 3 4 5 6 7 8 9 14\r\n12 13 14\r\n9 14 \r\n2 0\r\n1 3\r\n0 12\r\n0 13";
    String data3 = "3 1\r\n100 10 100\r\n0 1\r\n0 1\r\n0 1";
    Scanner s = new Scanner(data);
    while(s.hasNext()){
      int n = s.nextInt(); int k = s.nextInt();
      int[] elevTimes = new int[n];
      for(int i = 0; i < elevTimes.length; i++){
        elevTimes[i] = s.nextInt();
      }
      Map<Integer, List<Edge>> graph = new HashMap<>();
      s.nextLine();
      for(int i = 0; i < n; i++){
        String[] floors = s.nextLine().split("\\s+");
        for(int floor = 0; floor < floors.length; floor++){
          if(floor < floors.length - 1){
              int f1 = Integer.parseInt(floors[floor]);
              int f2 = Integer.parseInt(floors[floor + 1]);
              int cost = (f2 - f1) * elevTimes[i];
              if(!graph.containsKey(code(i, f1))) graph.put(code(i, f1), new ArrayList<Edge>());
              graph.get(code(i, f1)).add(new Edge(code(i, f2), cost));
              if(!graph.containsKey(code(i, f2))) graph.put(code(i, f2), new ArrayList<Edge>());
              graph.get(code(i, f2)).add(new Edge(code(i, f1), cost));
          }
        }
      }
      for(int v1 : graph.keySet()){
        for(int v2 : graph.keySet()){
          if(v1 != v2 && floor(v1) == floor(v2)){
            graph.get(v1).add(new Edge(v2, WAIT_TIME));
            graph.get(v2).add(new Edge(v1, WAIT_TIME));
          }
        }
      }
      int minDist = INF;
      for(int v : graph.keySet()){
        if(floor(v) == 0){
            minDist = min(minDist, dijkstra(v, k, graph));
        }
      }
      if(minDist == INF) System.out.println("IMPOSSIBLE");
      else System.out.println(minDist);
    }
  }

  static int floor(int code){
    return code >> 3;
  }

  static int elevator(int code){
    return code & 7;
  }

  static int code(int elevator, int floor){
    return (floor << 3) + elevator;
  }

  static final int WAIT_TIME = 60;
  static final int INF = Integer.MAX_VALUE;
  static final int MAX_N_FLOORS = 100;
  static final int MAX_N_ELEVATORS = 5;
  static int[] dist = new int[MAX_N_FLOORS * MAX_N_ELEVATORS * 2];
  static int dijkstra(int source, int targetFloor, Map<Integer, List<Edge>> graph){
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
      Vertex v = pq.remove();
      int u = v.id;
      if(floor(u) == targetFloor) return dist[u];
      if(v.dist > dist[u]) continue;
      for(Edge neighbor : graph.get(u)){
        int altDist = dist[u] + neighbor.cost;
        if(altDist < dist[neighbor.to]){
          dist[neighbor.to] = altDist;
          pq.add(new Vertex(neighbor.to, altDist));
        }
      }
    }
    return INF;
  }
}
