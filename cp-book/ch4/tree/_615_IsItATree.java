import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 615 Is It A Tree?
Problem url: https://uva.onlinejudge.org/external/6/615.pdf
Author: Andrey Yemelyanov
*/
public class _615_IsItATree {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int caseNum = 1;
    while(s.hasNext()){
      int u = s.nextInt(); int v = s.nextInt();
      if(u < 0 && v < 0) break;
      if(u == 0 && v == 0){
        boolean isTree = isTree(graph);
        graph = new HashMap<>();
        if(isTree) System.out.printf("Case %d is a tree.\n", caseNum++);
        else System.out.printf("Case %d is not a tree.\n", caseNum++);
      }else{
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v);
      }
    }
  }

  static boolean isTree(Map<Integer, List<Integer>> graph){
    if(graph.size() == 0) return true;
    List<Integer> rootNodes = nodesWithZeroInDegree(graph);
    if(rootNodes.size() != 1) return false;
    return bfs(graph, rootNodes.get(0));
  }

  static Map<Integer, List<Integer>> inverse(Map<Integer, List<Integer>> graph){
    Map<Integer, List<Integer>> inverse = new HashMap<>();
    for(int u : graph.keySet()){
      for(int v : graph.get(u)){
        inverse.putIfAbsent(u, new ArrayList<>());
        inverse.putIfAbsent(v, new ArrayList<>());
        inverse.get(v).add(u);
      }
    }
    return inverse;
  }

  static List<Integer> nodesWithZeroInDegree(Map<Integer, List<Integer>> graph){
    List<Integer> nodes = new ArrayList<>();
    Map<Integer, List<Integer>> inverse = inverse(graph);
    for(int u : inverse.keySet()){
      if(inverse.get(u).isEmpty()) nodes.add(u);
    }
    return nodes;
  }

  static boolean bfs(Map<Integer, List<Integer>> graph, int source){
    Queue<Integer> q = new LinkedList<>();
    q.add(source);
    Set<Integer> visited = new HashSet<>();
    visited.add(source);
    while(!q.isEmpty()){
      int u = q.remove();
      for(int v : graph.get(u)){
        if(visited.contains(v)) return false;
        visited.add(v);
        q.add(v);
      }
    }
    return visited.size() == graph.size();
  }
}
