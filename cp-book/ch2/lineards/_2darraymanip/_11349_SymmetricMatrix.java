import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11349 Symmetric Matrix
Problem url: https://uva.onlinejudge.org/external/113/11349.pdf
Author: Andrey Yemelyanov
*/
public class _11349_SymmetricMatrix {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    for(int t = 1; t <= nTests; t++){
      int N = Integer.parseInt(s.nextLine().split("=")[1].trim());
      long[][] m = new long[N][N];
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
          m[i][j] = s.nextLong();
        }
      }
      System.out.printf("Test #%d: %s.\n", t, isSymmetric(m) ? "Symmetric" : "Non-symmetric");
      if(t < nTests) s.nextLine();
    }
  }

  static boolean isSymmetric(long[][] m){
    /*for(int i = 0; i < m.length; i++){
      System.out.println(Arrays.toString(m[i]));
    }*/
    return nonNegative(m) && isSymmetricToCenter(m);
  }

  static boolean isSymmetricToCenter(long[][] m){
    int dim = m.length;
    for(int row = 0; row <= (dim - 1) / 2; row++){
      int upperRow = row; int lowerRow = dim - 1 - upperRow;
      for(int col = 0; col < dim; col++){
        int leftCol = col; int rightCol = dim - 1 - leftCol;
        if(m[upperRow][leftCol] != m[lowerRow][rightCol]) return false;
      }
    }
    return true;
  }

  static boolean nonNegative(long[][] m){
    int dim = m.length;
    for(int i = 0; i < dim; i++){
      for(int j = 0; j < dim; j++){
        if(m[i][j] < 0) return false;
      }
    }
    return true;
  }
}
