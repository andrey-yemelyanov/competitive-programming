import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 108 Maximum Sum
Problem url: https://uva.onlinejudge.org/external/1/108.pdf
Author: Andrey Yemelyanov
*/
public class _108_MaximumSum {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[][] matrix = new int[N][N];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        matrix[i][j] = s.nextInt();
      }
    }
    //for(int i = 0; i < matrix.length; i++) System.out.println(Arrays.toString(matrix[i]));
    System.out.println(largestSubRectSum(buildSums(matrix)));
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
    //for(int i = 0; i < n; i++) System.out.println(Arrays.toString(sums[i]));
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
