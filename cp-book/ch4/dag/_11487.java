import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11487 Gathering Food
Problem url: https://uva.onlinejudge.org/external/114/11487.pdf
Author: Andrey Yemelyanov
*/
public class _11487 {
  static class Cell{
    public int row; public int col;
    public Cell(int row, int col){ this.row = row; this.col = col; }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    while(s.hasNext()){
      int N = s.nextInt(); if(N == 0) break;
      char[][] grid = new char[N][N];
      for(int i = 0; i < grid.length; i++){
        char[] row = s.next().toCharArray();
        for(int j = 0; j < row.length; j++){
          grid[i][j] = row[j];
        }
      }
      int[] path = gatherFood(grid);
      if(path[0] == INF) System.out.printf("Case %d: Impossible\n", caseNum++);
      else System.out.printf("Case %d: %d %d\n", caseNum++, path[0], path[1]);
    }
  }

  static int[] findLetter(char[][] grid, char letter){
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        if(grid[i][j] == letter){
          return new int[] {i, j};
        }
      }
    }
    return null;
  }

  static int[] gatherFood(char[][] grid){
    int count = 0;
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        if(Character.isLetter(grid[i][j])) count++;
      }
    }
    char first = 'A';
    char last = (char)(first + count - 1);
    int totalPaths = 1;
    int pathLength = 0;
    for(char c = first; c < last; c++){
      //System.out.println(c);
      int[] start = findLetter(grid, c); int[] dest = findLetter(grid, (char)(c + 1));
      int[] path = countShortestPaths(grid, start[0], start[1], dest[0], dest[1]);
      if(path[0] == INF) return new int[] {INF, INF};
      pathLength += path[0];
      totalPaths = ((totalPaths % MOD) * (path[1] % MOD)) % MOD;
    }
    return new int[] {pathLength, totalPaths};
  }

  static final int INF = Integer.MAX_VALUE;
  static final int[] dr = new int[] {1, 0, 0, -1};
  static final int[] dc = new int[] {0, -1, 1, 0};
  static final int MOD = 20437;
  static int[] countShortestPaths(char[][] grid, int startRow, int startCol, int destRow, int destCol){
    int[][] dist = new int[grid.length][grid[0].length];
    for(int i = 0; i < dist.length; i++){
      for(int j = 0; j < dist[0].length; j++){
        if(!(i == startRow && j == startCol)) dist[i][j] = INF;
      }
    }
    int[][] shortestPaths = new int[grid.length][grid[0].length];
    shortestPaths[startRow][startCol] = 1;
    Queue<Cell> q = new LinkedList<>();
    q.add(new Cell(startRow, startCol));
    while(!q.isEmpty()){
      Cell current = q.remove();
      for(int i = 0; i < 4; i++){
        Cell neighbor = new Cell(current.row + dr[i], current.col + dc[i]);
        if(insideGrid(neighbor, grid) &&
            (grid[neighbor.row][neighbor.col] == '.' || grid[neighbor.row][neighbor.col] == grid[destRow][destCol]
            || (Character.isLetter(grid[neighbor.row][neighbor.col]) && grid[neighbor.row][neighbor.col] < grid[startRow][startCol]))){
          if(dist[neighbor.row][neighbor.col] == INF){
            dist[neighbor.row][neighbor.col] = dist[current.row][current.col] + 1;
            shortestPaths[neighbor.row][neighbor.col] = shortestPaths[current.row][current.col];
            q.add(neighbor);
          }else{
            if(dist[neighbor.row][neighbor.col] == dist[current.row][current.col] + 1){
              shortestPaths[neighbor.row][neighbor.col] += shortestPaths[current.row][current.col];
            }
          }
        }
      }
    }
    /*System.out.printf("Source %c Dest %c\n", grid[startRow][startCol], grid[destRow][destCol]);
    for(int i = 0; i < dist.length; i++){
      System.out.println(Arrays.toString(dist[i]));
    }*/
    return new int[] {dist[destRow][destCol], shortestPaths[destRow][destCol] % MOD};
  }

  static boolean insideGrid(Cell cell, char[][] grid){
    return cell.row >= 0 && cell.row < grid.length && cell.col >= 0 && cell.col < grid[0].length;
  }
}
