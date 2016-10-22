import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

public class _10285_LongestRun {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      String slope = s.next();
      int R = s.nextInt(); int C = s.nextInt();
      int[][] matrix = new int[R][C];
      for(int i = 0; i < matrix.length; i++){
        for(int j = 0; j < matrix[i].length; j++){
          matrix[i][j] = s.nextInt();
        }
      }
      System.out.printf("%s: %d\n", slope, longestPath(buildDag(matrix)));
    }
  }

  static Map<Integer, List<Integer>> incoming;
  static Map<Integer, List<Integer>> buildDag(int[][] matrix){
    final int dummyVertex = matrix.length * matrix[0].length;
    Map<Integer, List<Integer>> dag = new HashMap<>();
    incoming = new HashMap<>();
    dag.put(dummyVertex, new ArrayList<>());
    incoming.put(dummyVertex, new ArrayList<>());
    int[][] index = new int[matrix.length][matrix[0].length];
    for(int i = 0; i < index.length; i++){
      for(int j = 0; j < index[i].length; j++){
        index[i][j] = i * index[i].length + j;
      }
    }

    final int[] dr = new int[] {-1, 0, 1, 0};
    final int[] dc = new int[] {0, 1, 0, -1};

    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
        dag.putIfAbsent(index[i][j], new ArrayList<>());
        incoming.putIfAbsent(index[i][j], new ArrayList<>());

        for(int dir = 0; dir < 4; dir++){
          int newRow = i + dr[dir]; int newCol = j + dc[dir];
          if(valid(matrix, newRow, newCol) && matrix[newRow][newCol] < matrix[i][j]){
            dag.get(index[i][j]).add(index[newRow][newCol]);
            incoming.putIfAbsent(index[newRow][newCol], new ArrayList<>());
            incoming.get(index[newRow][newCol]).add(index[i][j]);
          }
        }
      }
    }

    for(int v : dag.keySet()){
      if(v != dummyVertex && incoming.get(v).isEmpty()){
        dag.get(dummyVertex).add(v);
        incoming.get(v).add(dummyVertex);
      }
    }

    return dag;
  }

  static boolean valid(int[][] matrix, int row, int col){
    return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
  }

  static final int NEG_INF = Integer.MIN_VALUE;
  static int longestPath(Map<Integer, List<Integer>> dag){
    int[] dist = new int[dag.size()];
    for(int i = 0; i < dist.length; i++) dist[i] = NEG_INF;
    Stack<Integer> linearOrdering = toposort(dag);
    //System.out.println(linearOrdering);
    dist[linearOrdering.pop()] = 0;

    while(!linearOrdering.isEmpty()){
      int v = linearOrdering.pop();
      for(int u : incoming.get(v)){
        dist[v] = max(dist[v], dist[u] + 1);
      }
    }
    //System.out.println(Arrays.toString(dist));
    return Arrays.stream(dist)
                 .max()
                 .getAsInt();
  }

  static Stack<Integer> toposort(Map<Integer, List<Integer>> dag){
    boolean[] visited = new boolean[dag.size()];
    Stack<Integer> toposort = new Stack<>();
    for(int v : dag.keySet()){
      if(!visited[v]){
        dfs(v, dag, visited, toposort);
      }
    }
    return toposort;
  }

  static void dfs(int source, Map<Integer, List<Integer>> dag, boolean[] visited, Stack<Integer> s){
    visited[source] = true;
    for(int neighbor : dag.get(source)){
      if(!visited[neighbor]){
        dfs(neighbor, dag, visited, s);
      }
    }
    s.add(source);
  }
}
