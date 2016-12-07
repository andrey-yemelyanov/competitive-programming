import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10596 Morning Walk
Problem url: https://uva.onlinejudge.org/external/105/10596.pdf
Author: Andrey Yemelyanov
*/
public class _10596_MorningWalk {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt(); int R = s.nextInt();
      Map<Integer, Integer> degree = new HashMap<>();
      Map<Integer, List<Integer>> graph = new HashMap<>();
      int source = -1;
      for(int i = 0; i < R; i++){
        int v1 = s.nextInt(); int v2 = s.nextInt(); if(source == -1) source = v1;
        degree.putIfAbsent(v1, 0);
        degree.putIfAbsent(v2, 0);
        graph.putIfAbsent(v1, new ArrayList<>());
        graph.putIfAbsent(v2, new ArrayList<>());
        degree.put(v1, degree.get(v1) + 1);
        degree.put(v2, degree.get(v2) + 1);
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
      }
      if(connected(graph, source) && hasEulerTour(degree)){
        System.out.println("Possible");
      }else{
        System.out.println("Not Possible");
      }
    }
  }

  static boolean connected(Map<Integer, List<Integer>> graph, int source){
    if(graph.size() == 0) return false;
    Set<Integer> visited = new HashSet<>();
    dfs(graph, visited, source);
    return visited.size() == graph.size();
  }

  static void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int source){
    visited.add(source);
    for(int v : graph.get(source)){
      if(!visited.contains(v)){
        visited.add(v);
        dfs(graph, visited, v);
      }
    }
  }

  static boolean hasEulerTour(Map<Integer, Integer> degree){
    for(int deg : degree.values()){
      if(deg % 2 != 0) return false;
    }
    return true;
  }
}
