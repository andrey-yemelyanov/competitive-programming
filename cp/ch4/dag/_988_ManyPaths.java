import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

public class _988_ManyPaths {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    boolean firstSet = true;
    while(s.hasNext()){
      int nEvents = s.nextInt();
      Map<Integer, List<Integer>> dag = new HashMap<>();
      for(int i = 0; i < nEvents; i++){
        dag.put(i, new ArrayList<>());
      }
      for(int i = 0; i < nEvents; i++){
        int n = s.nextInt();
        for(int j = 0; j < n; j++){
          dag.get(i).add(s.nextInt());
        }
      }
      if(!firstSet) System.out.println();
      else firstSet = false;
      int nPaths = 0;
      for(int v : dag.keySet()){
        if(dag.get(v).isEmpty()){ // death vertex
          nPaths += countPaths(v, dag);
        }
      }
      System.out.println(nPaths);
    }
  }

  static int countPaths(int dest, Map<Integer, List<Integer>> dag){
    memo = new int[dag.size()][dag.size()];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        memo[i][j] = INF;
      }
    }
    return countPaths(0, dest, dag);
  }

  static final int INF = Integer.MAX_VALUE;
  static int[][] memo;
  static int countPaths(int source, int dest, Map<Integer, List<Integer>> dag){
    if(source == dest) return 1;
    if(memo[source][dest] != INF) return memo[source][dest];
    int nPaths = 0;
    for(int neighbor : dag.get(source)){
      nPaths += countPaths(neighbor, dest, dag);
    }
    memo[source][dest] = nPaths;
    return nPaths;
  }
}
