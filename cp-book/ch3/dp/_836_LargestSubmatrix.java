import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 836 Largest Submatrix
Problem url: https://uva.onlinejudge.org/external/8/836.pdf
Author: Andrey Yemelyanov
*/
public class _836_LargestSubmatrix {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine(); s.nextLine();
    while(nTests-- > 0){
      List<int[]> m = new ArrayList<>();
      while(true){
        String line = "";
        if(s.hasNext()){
          line = s.nextLine();
        }
        if(!line.isEmpty()){
          int[] row = new int[line.length()];
          for(int i = 0; i < row.length; i++){
            row[i] = Character.getNumericValue(line.charAt(i));
          }
          m.add(row);
        }else{
          int[][] matrix = new int[m.size()][m.get(0).length];
          for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
              matrix[i][j] = m.get(i)[j];
            }
          }
          int largest = largestSubmatrix(matrix);
          if(largest == NEG_INF) System.out.println(0);
          else System.out.println(largest);
          if(nTests > 0) System.out.println();
          break;
        }
      }
    }
  }

  static final int NEG_INF = -1000000;
  static int largestSubmatrix(int[][] matrix){
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[0].length; j++){
        if(matrix[i][j] == 0) matrix[i][j] = NEG_INF;
      }
    }
    return largestSubRectSum(buildSums(matrix));
  }

  static int largestSubRectSum(int[][] sums){
    int n = sums.length; int m = sums[0].length;
    int largestSubRect = Integer.MIN_VALUE;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        for(int k = i; k < n; k++){
          for(int l = j; l < m; l++){
            largestSubRect = max(largestSubRect, getSubRectSum(sums, i, j, k, l));
          }
        }
      }
    }
    return largestSubRect;
  }

  static int[][] buildSums(int[][] matrix){
    int[][] sums = new int[matrix.length][matrix[0].length];
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
        sums[i][j] = matrix[i][j];
        if(i > 0) sums[i][j] += sums[i - 1][j];
        if(j > 0) sums[i][j] += sums[i][j - 1];
        if(i > 0 && j > 0) sums[i][j] -= sums[i - 1][j - 1];
      }
    }
    return sums;
  }

  static int getSubRectSum(int[][] sums, int i, int j, int k, int l){
    int subRect = sums[k][l];
    if(i > 0) subRect -= sums[i - 1][l];
    if(j > 0) subRect -= sums[k][j - 1];
    if(i > 0 && j > 0) subRect += sums[i - 1][j - 1];
    return subRect;
  }
}
