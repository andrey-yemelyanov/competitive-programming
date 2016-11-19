import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11380 Down Went The Titanic
Problem url: https://uva.onlinejudge.org/external/113/11380.pdf
Author: Andrey Yemelyanov
*/
public class _11380_DownWentTheTitanic {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int X = s.nextInt(); int Y = s.nextInt(); int P = s.nextInt();
      char[][] grid = new char[X][Y];
      for(int i = 0; i < grid.length; i++){
        String line = s.next();
        for(int j = 0; j < grid[i].length; j++){
          grid[i][j] = line.charAt(j);
        }
      }
      int[][] res = buildResidualMatrix(buildIncidentMatrix(grid), grid, P);
      int source = res.length - 2;
      int sink = res.length - 1;
      System.out.println(maxFlow(res, source, sink));
    }
  }

  static final int UNDEF = -1;
  static final int INF = 100000000;
  static int f;
  static void augment(int v, int source, int minEdge, int[][] res, int[] p){
    if(v == source){
      f = minEdge;
      return;
    }else if(p[v] != UNDEF){
      augment(p[v], source, min(minEdge, res[p[v]][v]), res, p);
      res[p[v]][v] -= f;
      res[v][p[v]] += f;
    }
  }

  static int maxFlow(int[][] res, int source, int sink){
    int V = res.length;
    int maxFlow = 0;
    while(true){
      f = 0;
      boolean[] visited = new boolean[V];
      visited[source] = true;
      Queue<Integer> q = new LinkedList<>();
      q.add(source);
      int[] p = new int[V]; for(int i = 0; i < V; i++) p[i] = UNDEF;
      while(!q.isEmpty()){
        int u = q.remove();
        if(u == sink) break;
        for(int v = 0; v < V; v++){
          if(res[u][v] > 0 && !visited[v]){
            visited[v] = true;
            q.add(v);
            p[v] = u;
          }
        }
      }
      augment(sink, source, INF, res, p);
      if(f == 0) break;
      maxFlow += f;
    }
    return maxFlow;
  }

  static final char PERSON = '*';
  static final char WATER = '~';
  static final char ICE = '.';
  static final char ICEBERG = '@';
  static final char WOOD = '#';
  static final int LARGE = 1000;
  static int[][] buildIncidentMatrix(char[][] grid){
    int V = grid.length * grid[0].length;
    int[][] matrix = new int[V][V];
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        int row1 = i / grid[0].length; int row2 = j / grid[0].length;
        if((abs(i - j) == 1 && row1 == row2) || abs(i - j) == grid[0].length){ // i and j are immediate neighbors along NWES directions
          int col1 = i % grid[0].length; int col2 = j % grid[0].length;
          if(grid[row1][col1] != WATER && grid[row2][col2] != WATER){
            matrix[i][j] = LARGE;
          }
        }
      }
    }
    return matrix;
  }

  static int out(int v){ return v * 2 + 1; }
  static int in(int v){ return v * 2; }

  static int vertexCapacity(int v, char[][] grid){
    int row = v / grid[0].length; int col = v % grid[0].length;
    char c = grid[row][col];
    if(c == PERSON || c == ICE) return 1;
    if(c == ICEBERG || c == WOOD) return LARGE;
    return 0;
  }

  static int[][] buildResidualMatrix(int[][] incidentMatrix, char[][] grid, int P){
    int V = incidentMatrix.length * 2 + 2;
    int[][] res = new int[V][V];
    for(int i = 0; i < incidentMatrix.length; i++){
      for(int j = 0; j < incidentMatrix.length; j++){
        if(incidentMatrix[i][j] == LARGE){ // edge exists
          res[out(i)][in(j)] = LARGE; // edge weight
          res[in(j)][out(j)] = vertexCapacity(j, grid); // vertex capacity
        }
      }
    }
    int source = V - 2;
    int sink = V - 1;
    for(int i = 0; i < V - 2; i += 2){
      int row = (i / 2) / grid[0].length; int col = (i / 2) % grid[0].length;
      if(grid[row][col] == PERSON) res[source][i] = 1;
      if(grid[row][col] == WOOD) res[i + 1][sink] = P;
    }
    return res;
  }
}
