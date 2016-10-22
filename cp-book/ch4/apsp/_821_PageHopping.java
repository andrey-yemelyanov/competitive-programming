import java.util.*;
import static java.lang.Math.*;

public class _821_PageHopping {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    while(s.hasNext()){
      Map<Integer, List<Integer>> graph = new HashMap<>();
      while(true){
        int v1 = s.nextInt() - 1; int v2 = s.nextInt() - 1;
        if(v1 == -1 && v2 == -1) break;
        if(!graph.containsKey(v1)) graph.put(v1, new ArrayList<Integer>());
        graph.get(v1).add(v2);
      }
      if(!graph.isEmpty()){
        int[][] dist = new int[graph.size()][graph.size()];
        int[] map = new int[graph.size()];
        int n = 0;
        for(int vertex : graph.keySet()){
          map[n++] = vertex;
        }
        for(int i = 0; i < dist.length; i++){
          for(int j = 0; j < dist.length; j++){
            if(i == j) dist[i][j] = 0;
            else if(graph.containsKey(map[i]) && graph.get(map[i]).contains(map[j])) dist[i][j] = 1;
            else dist[i][j] = INF;
          }
        }

        floydWarshall(dist);
        int V = graph.size();
        int shortestPathSum = 0;
        for(int i = 0; i < V; i++){
          for(int j = 0; j < V; j++){
            shortestPathSum += dist[i][j];
          }
        }
        double averageShortestPath = (double) shortestPathSum / (V * (V - 1));
        System.out.printf("Case %d: average length between pages = %.3f clicks\n", caseNum++, averageShortestPath);
      }
    }
  }

  static final int INF = 1000000;
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
}
