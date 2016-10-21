import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

public class _10350_LiftlessEme {
  static class Edge{
    public int v;
    public int weight;
    public Edge(int v, int weight) { this.v = v; this.weight = weight; }
    @Override
    public String toString(){ return v + "(" + weight + ")"; }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String caseName = s.next();
      int n = s.nextInt(); int m = s.nextInt();
      int V = n * m + 2; // 2 extra nodes for source and sink
      final int source = V - 2; final int sink = V - 1;
      Map<Integer, List<Edge>> dag = new HashMap<>();
      for(int i = 0; i < V; i++){
        dag.put(i, new ArrayList<>());
      }

      // build main graph (connect holes on floor k to holes on floor k + 1)
      for(int i = 0; i < m * (n - 1); i++){
        int from = (i / m + 1) * m;
        for(int j = from; j < from + m; j++){
          dag.get(i).add(new Edge(j, s.nextInt() + LADDER_CLIMB_TIME));
        }
      }

      // connect source to all vertices with in-degree of 0 (edge weight = 0)
      for(int i = 0; i < m; i++){
        dag.get(source).add(new Edge(i, 0));
      }

      // connect all vertices with out-degree of 0 to sink (edge weight = 0)
      for(int i : dag.keySet()){
        if(i != sink && dag.get(i).isEmpty()){
          dag.get(i).add(new Edge(sink, 0));
        }
      }

      System.out.println(caseName);
      System.out.println(shortestPath(dag, buildIncoming(dag), sink));
    }
  }

  static Map<Integer, List<Edge>> buildIncoming(Map<Integer, List<Edge>> dag){
    Map<Integer, List<Edge>> incoming = new HashMap<>();
    for(int i = 0; i < dag.size(); i++){
      incoming.putIfAbsent(i, new ArrayList<>());
      for(Edge e : dag.get(i)){
        incoming.putIfAbsent(e.v, new ArrayList<>());
        incoming.get(e.v).add(new Edge(i, e.weight));
      }
    }
    return incoming;
  }

  static final int LADDER_CLIMB_TIME = 2;
  static final int NEG_INF = Integer.MAX_VALUE;

  static int shortestPath(Map<Integer, List<Edge>> dag, Map<Integer, List<Edge>> incoming, int sink){
    int[] dist = new int[dag.size()];
    for(int i = 0; i < dist.length; i++) dist[i] = NEG_INF;
    Stack<Integer> linearOrdering = toposort(dag);
    //System.out.println(linearOrdering);
    dist[linearOrdering.pop()] = 0;

    while(!linearOrdering.isEmpty()){
      int v = linearOrdering.pop();
      for(Edge edge : incoming.get(v)){
        int u = edge.v; int uvWeight = edge.weight;
        dist[v] = min(dist[v], dist[u] + uvWeight);
      }
    }

    return dist[sink];
  }

  static Stack<Integer> toposort(Map<Integer, List<Edge>> dag){
    boolean[] visited = new boolean[dag.size()];
    Stack<Integer> toposort = new Stack<>();
    for(int v : dag.keySet()){
      if(!visited[v]){
        dfs(v, dag, visited, toposort);
      }
    }
    return toposort;
  }

  static void dfs(int source, Map<Integer, List<Edge>> dag, boolean[] visited, Stack<Integer> s){
    visited[source] = true;
    for(Edge e : dag.get(source)){
      if(!visited[e.v]){
        dfs(e.v, dag, visited, s);
      }
    }
    s.add(source);
  }
}
