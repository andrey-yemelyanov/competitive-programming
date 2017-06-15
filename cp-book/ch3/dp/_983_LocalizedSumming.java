import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 983 Localized Summing for Blurring
Problem url: https://uva.onlinejudge.org/external/9/983.pdf
Author: Andrey Yemelyanov
*/
public class _983_LocalizedSumming {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    boolean firstCase = true;
    StringBuilder sb = new StringBuilder();
    while(s.hasNext()){
      if(firstCase) firstCase = false;
      else sb.append("\n");
      int N = s.nextInt(); int M = s.nextInt();
      int[][] matrix = new int[N][N];
      for(int i = N - 1; i >= 0; i--){
        for(int j = 0; j < N; j++){
          matrix[i][j] = s.nextInt();
        }
      }
      if(s.hasNext()) s.nextLine();
      if(s.hasNext()) s.nextLine();
      int[][] blurredMatrix = new int[N - M + 1][N - M + 1];
      long totalSum = buildBlurredMatrix(buildSums(matrix), blurredMatrix);
      for(int i = blurredMatrix.length - 1; i >= 0; i--){
        for(int j = 0; j < blurredMatrix[i].length; j++){
          sb.append(blurredMatrix[i][j] + "\n");
        }
      }
      sb.append(totalSum + "\n");
    }
    System.out.print(sb.toString());
  }

  static long buildBlurredMatrix(int[][] sums, int[][] blurredMatrix){
    int N = sums.length;
    int M = N + 1 - blurredMatrix.length;
    long totalSum = 0;
    for(int i = 0; i <= N - M; i++){
      for(int j = 0; j <= N - M; j++){
        blurredMatrix[i][j] = getSubRectSum(sums, i, j, i + M - 1, j + M - 1);
        totalSum += blurredMatrix[i][j];
      }
    }
    return totalSum;
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
