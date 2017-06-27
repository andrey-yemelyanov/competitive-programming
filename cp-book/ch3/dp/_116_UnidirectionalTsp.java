import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 116 Unidirectional TSP
Problem url: https://uva.onlinejudge.org/external/1/116.pdf
Author: Andrey Yemelyanov
*/
public class _116_UnidirectionalTsp {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int m = s.nextInt(); int n = s.nextInt();
      int[][] weight = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          weight[i][j] = s.nextInt();
        }
      }

      int[][] memo = new int[m][n];
      for(int i = 0; i < m; i++) Arrays.fill(memo[i], INF);
      int minWeight = minWeight(weight, memo);
      System.out.println(
        getMinWeightPath(minWeight, memo, weight).stream()
                                                 .map(i -> Integer.toString(i + 1))
                                                 .collect(Collectors.joining(" ")));
      System.out.println(minWeight);
    }
  }

  static List<Integer> getMinWeightPath(int minWeight, int[][] memo, int[][] weight){
    List<Integer> path = new ArrayList<>();
    /*for(int row = 0; row < memo.length; row++){
      System.out.println(Arrays.toString(memo[row]));
    }*/
    for(int row = 0; row < memo.length; row++){
      if(memo[row][0] == minWeight){
        path.add(row);
        getMinWeightPath(minWeight, memo, weight, path, row, 0);
        break;
      }
    }
    return path;
  }

  static void getMinWeightPath(int minWeight, int[][] memo, int[][] weight, List<Integer> path, int row, int col){
    if(col == memo[0].length - 1) return;
    int prevRow = prevRow(row, memo.length); int nextRow = (row + 1) % memo.length;
    int[] rows = new int[]{prevRow, row, nextRow};
    Arrays.sort(rows);
    //System.out.println("Row = " + row + " Col = " + col + " " + Arrays.toString(rows));
    if(memo[rows[0]][col + 1] == minWeight - weight[row][col]){
      path.add(rows[0]);
      getMinWeightPath(minWeight - weight[row][col], memo, weight, path, rows[0], col + 1);
    }else if(memo[rows[1]][col + 1] == minWeight - weight[row][col]){
      path.add(rows[1]);
      getMinWeightPath(minWeight - weight[row][col], memo, weight, path, rows[1], col + 1);
    }else{
      path.add(rows[2]);
      getMinWeightPath(minWeight - weight[row][col], memo, weight, path, rows[2], col + 1);
    }
  }

  static int minWeight(int[][] weight, int[][] memo){
    int minWeight = INF;
    for(int row = 0; row < weight.length; row++){
      minWeight = min(minWeight, minWeight(weight, row, 0, memo));
    }
    return minWeight;
  }

  static final int INF = Integer.MAX_VALUE;
  static int minWeight(int[][] weight, int row, int col, int[][] memo){
    int m = weight.length; int n = weight[0].length;
    if(col == n) return 0;
    if(memo[row][col] != INF) return memo[row][col];
    return memo[row][col] = weight[row][col] +
      min(minWeight(weight, prevRow(row, m), col + 1, memo),
        min(minWeight(weight, row, col + 1, memo),
            minWeight(weight, (row + 1) % m, col + 1, memo)));
  }

  static int prevRow(int row, int m){
    int prevRow = (row - 1) % m;
    if(prevRow < 0) prevRow += m;
    return prevRow;
  }
}
