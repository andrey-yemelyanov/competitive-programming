import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.util.function.*;

/*
Problem name: 10349 Antenna Placement
Problem url: https://uva.onlinejudge.org/external/103/10349.pdf
Author: Andrey Yemelyanov

Followed this great hint from: https://uva.onlinejudge.org/board/viewtopic.php?f=20&t=1272&hilit=10349
This is a bipartite matching problem (although it has other solutions as well, one of them is dynamic programming).
If you think of the whole region as a big chess board, you'll notice that each antenna placement will cover one black square
and one white square. Thus you can create a bipartite graph with all squares to be covered on white squares in one partition and
all squares to be covered on black squares in another partition. Connect two squares if they're adjacent to one another.

The maximum matching in this graph corresponds to which placements you should put to cover exactly two squares.
If there are 10 squares in partition A and 7 square in partition B, and the maximum matching is, say, 5,
this means that you can cover 10 of the 17 with 5 placements and the remaining 7 have to be covered one by by one.
Thus the answer is 12 which happens to be 17-maxmatching.

*/
public class _10349_AntennaPlacement {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int nRows = s.nextInt(); int nCols = s.nextInt();
      char[][] grid = new char[nRows][nCols];
      for(int i = 0; i < nRows; i++){
        String row = s.next();
        for(int j = 0; j < nCols; j++){
          grid[i][j] = row.charAt(j);
        }
      }
      List<List<Integer>> pointsOfInterest = getPointsOfInterest(grid);
      List<Integer> whites = pointsOfInterest.get(0);
      List<Integer> blacks = pointsOfInterest.get(1);
      if(whites.size() == 0){
        System.out.println(blacks.size());
      }else if(blacks.size() == 0){
        System.out.println(whites.size());
      }else{
        int V = whites.size() + blacks.size();
        int[][] res = buildResidualGraph(whites, blacks, grid);
        int source = 0; int sink = res.length - 1;
        int mcbm = maxFlow(res, source, sink);
        System.out.println(V - mcbm);
      }
    }
  }

  static boolean adjacent(int white, int black, char[][] grid){
    int whiteRow = white / grid[0].length; int whiteCol = white % grid[0].length;
    int blackRow = black / grid[0].length; int blackCol = black % grid[0].length;
    return (whiteRow == blackRow && abs(whiteCol - blackCol) == 1) || (whiteCol == blackCol && abs(whiteRow - blackRow) == 1);
  }

  static List<List<Integer>> getPointsOfInterest(char[][] grid){
    List<List<Integer>> list = new ArrayList<>();
    list.add(new ArrayList<>()); // white partition
    list.add(new ArrayList<>()); // black partition
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        if(grid[i][j] == '*'){
          int id = grid[0].length * i + j;
          if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) list.get(0).add(id);
          if((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) list.get(1).add(id);
        }
      }
    }
    return list;
  }

  static int[][] buildResidualGraph(List<Integer> whites, List<Integer> blacks, char[][] grid){
    int nWhite = whites.size(); int nBlack = blacks.size();
    int V = nWhite + nBlack + 2;
    int source = 0; int sink = V - 1;
    int[][] res = new int[V][V];
    // connect source to all whites
    for(int white = 1; white <= nWhite; white++){
      res[source][white] = 1;
    }
    // connect all blacks to sink
    for(int black = nWhite + 1; black <= V - 2; black++){
      res[black][sink] = 1;
    }
    // connect whites and blacks
    for(int white = 1; white <= nWhite; white++){
      for(int black = nWhite + 1; black <= V - 2; black++){
        if(adjacent(whites.get(white - 1), blacks.get(black - nWhite - 1), grid)) res[white][black] = 1;
      }
    }
    /*for(int i = 0; i < res.length; i++){
      System.out.println(Arrays.toString(res[i]));
    }*/
    return res;
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
}
