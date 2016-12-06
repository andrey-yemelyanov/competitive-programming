import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10054 The Necklace
Problem url: https://uva.onlinejudge.org/external/100/10054.pdf
Author: Andrey Yemelyanov
*/
public class _10054_Necklace {
  static class Edge{
    public int v;
    public boolean edgeCanBeUsed;
    public Edge(int v){
      this.v = v;
      edgeCanBeUsed = true;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int N = s.nextInt();
      Map<Integer, List<Edge>> graph = new HashMap<>();
      Map<Integer, Integer> degree = new HashMap<>();
      int source = 0;
      for(int i = 0; i < N; i++){
        int v1 = s.nextInt(); int v2 = s.nextInt();
        if(source == 0) source = v1;
        graph.putIfAbsent(v1, new ArrayList<>());
        graph.putIfAbsent(v2, new ArrayList<>());
        graph.get(v1).add(new Edge(v2));
        graph.get(v2).add(new Edge(v1));
        degree.putIfAbsent(v1, 0);
        degree.putIfAbsent(v2, 0);
        degree.put(v1, degree.get(v1) + 1);
        degree.put(v2, degree.get(v2) + 1);
      }
      List<Integer> eulerTour = new ArrayList<>();
      System.out.printf("Case #%d\n", c);
      if(!connected(graph, source) || !hasEulerianTour(degree)){
        System.out.println("some beads may be lost");
      }else{
        eulerTour(graph, source, eulerTour);
        //System.out.println(eulerTour);
        System.out.println(printTour(eulerTour));
      }
      if(c < nTests) System.out.println();
    }
  }

  static boolean hasEulerianTour(Map<Integer, Integer> degree){
    for(int deg : degree.values()){
      if(deg % 2 != 0) return false;
    }
    return true;
  }

  static void dfs(Map<Integer, List<Edge>> graph, int source, Set<Integer> visited){
	  visited.add(source);
  	for(Edge e : graph.get(source)){
  		if(!visited.contains(e.v)){
  			dfs(graph, e.v, visited);
  		}
  	}
  }

  static boolean connected(Map<Integer, List<Edge>> graph, int source){
  	Set<Integer> visited = new HashSet<>();
  	dfs(graph, source, visited);
  	return visited.size() == graph.size();
  }

  static String printTour(List<Integer> tour){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < tour.size() - 1; i++){
      sb.append(tour.get(i));
      sb.append(" ");
      sb.append(tour.get(i + 1));
      sb.append("\n");
    }
    sb.append(tour.get(tour.size() - 1));
    sb.append(" ");
    sb.append(tour.get(0));
    return sb.toString();
  }

  static void eulerTour(Map<Integer, List<Edge>> graph, int u, List<Integer> tour){
    for(Edge e : graph.get(u)){
      if(e.edgeCanBeUsed){
        e.edgeCanBeUsed = false;
        for(Edge e2 : graph.get(e.v)){
          if(e2.v == u && e2.edgeCanBeUsed){
            e2.edgeCanBeUsed = false;
            break;
          }
        }
        eulerTour(graph, e.v, tour);
        tour.add(u);
      }
    }
  }
}
