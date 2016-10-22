import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

public class _452_ProjectScheduling {
  static class Edge{
    public int v;
    public int weight;
    public Edge(int v, int weight) { this.v = v; this.weight = weight; }
    @Override
    public String toString(){ return (char)(v + 'A') + "(" + weight + ")"; }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine(); s.nextLine();
    while(nTests-- > 0){
      Map<Integer, List<Edge>> dag = new HashMap<>();
      final int dummy = MAX_V + 1;
      dag.put(dummy, new ArrayList<>());
      while(s.hasNext()){
        String line = s.nextLine();
        if(line == null || line.isEmpty()) break;
        String[] tokens = line.split("\\s+");
        int v = tokens[0].charAt(0) - 'A';
        int weight = Integer.parseInt(tokens[1]);
        dag.putIfAbsent(v, new ArrayList<>());
        if(tokens.length < 3){
          dag.get(dummy).add(new Edge(v, weight));
        }else{
          for(char vertex : tokens[2].toCharArray()){
            dag.putIfAbsent(vertex - 'A', new ArrayList<>());
            dag.get(vertex - 'A').add(new Edge(v, weight));
          }
        }
      }
      System.out.println(criticalPathLength(dag));
      if(nTests > 0) System.out.println();
    }
  }

  static final int MAX_V = 26;
  static final int NEG_INF = Integer.MIN_VALUE;
  static int criticalPathLength(Map<Integer, List<Edge>> dag){
    int[] dist = new int[MAX_V + 2];
    for(int i = 0; i < dist.length; i++) dist[i] = NEG_INF;
    Stack<Integer> linearOrdering = toposort(dag);
    dist[linearOrdering.pop()] = 0;

    while(!linearOrdering.isEmpty()){
      int v = linearOrdering.pop();
      //System.out.println("Pop: " + (char)(v + 'A'));
      for(Edge edge : incomingEdges(v, dag)){
        dist[v] = max(dist[v], dist[edge.v] + edge.weight);
      }
    }

    //System.out.println(Arrays.toString(dist));

    return Arrays.stream(dist)
                 .max()
                 .getAsInt();
  }

  static List<Edge> incomingEdges(int v, Map<Integer, List<Edge>> dag){
    List<Edge> incoming = new ArrayList<>();
    for(int u : dag.keySet()){
      dag.get(u).stream()
                .filter(e -> e.v == v)
                .findFirst()
                .ifPresent(e -> incoming.add(new Edge(u, e.weight)));
    }
    //System.out.println("Incoming edges for " + (char)(v + 'A') + "=" + incoming);
    return incoming;
  }

  static Stack<Integer> toposort(Map<Integer, List<Edge>> dag){
    boolean[] visited = new boolean[MAX_V + 2];
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
