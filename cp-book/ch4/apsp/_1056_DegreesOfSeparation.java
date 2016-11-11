import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 1056 Degrees of Separation
Problem url: https://uva.onlinejudge.org/external/10/1056.pdf
Author: Andrey Yemelyanov
*/
public class _1056_DegreesOfSeparation {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int n = 1;
    while(s.hasNext()){
      int P = s.nextInt(); int R = s.nextInt();
      if(P == 0 && R == 0) break;
      Map<String, List<String>> graph = new HashMap<>();
      for(int i = 0; i < R; i++){
        String person1 = s.next(); String person2 = s.next();
        graph.putIfAbsent(person1, new ArrayList<>());
        graph.putIfAbsent(person2, new ArrayList<>());
        graph.get(person1).add(person2);
        graph.get(person2).add(person1);
      }
      if(P > graph.size()) System.out.printf("Network %d: DISCONNECTED\n\n", n++);
      else{
        int[][] adjMatrix = toAdjMatrix(graph);
        floydWarshall(adjMatrix);
        int maxDegree = graphDiameter(adjMatrix);
        if(maxDegree == INF) System.out.printf("Network %d: DISCONNECTED\n\n", n++);
        else System.out.printf("Network %d: %d\n\n", n++, maxDegree);
      }
    }
  }

  static int graphDiameter(int[][] adjMatrix){
    int longestShortestPath = 0;
    int V = adjMatrix.length;
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        longestShortestPath = max(longestShortestPath, adjMatrix[i][j]);
      }
    }
    return longestShortestPath;
  }

  static void print(int[][] adjMatrix){
    for(int i = 0; i < adjMatrix.length; i++){
      System.out.println(Arrays.toString(adjMatrix[i]));
    }
    System.out.println();
  }

  static void floydWarshall(int[][] dist){
    int V = dist.length;
    for(int k = 0; k < V; k++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
  }

  static final int INF = 10000000;
  static int[][] toAdjMatrix(Map<String, List<String>> graph){
    List<String> personToIdMap = new ArrayList<>();
    for(String person : graph.keySet()){
      personToIdMap.add(person);
    }
    int V = personToIdMap.size();
    int[][] adjMatrix = new int[V][V];
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        String person1 = personToIdMap.get(i);
        String person2 = personToIdMap.get(j);
        if(i == j) adjMatrix[i][j] = 0;
        else if(graph.get(person1).contains(person2)) adjMatrix[i][j] = 1;
        else adjMatrix[i][j] = INF;
      }
    }
    return adjMatrix;
  }
}
