import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 541 Error Correction
Problem url: https://uva.onlinejudge.org/external/5/541.pdf
Author: Andrey Yemelyanov
*/
public class _541_ErrorCorrection {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      int[][] m = new int[N][N];
      for(int i = 0; i < m.length; i++){
        for(int j = 0; j < m[0].length; j++){
          m[i][j] = s.nextInt();
        }
      }
      /*for(int i = 0; i < m.length; i++){
        System.out.println(Arrays.toString(m[i]));
      }*/
      if(hasParityProperty(m)) System.out.println("OK");
      else{
        int[] fixBit = fixMatrix(m);
        if(fixBit == null) System.out.println("Corrupt");
        else System.out.printf("Change bit (%d,%d)\n", fixBit[0] + 1, fixBit[1] + 1);
      }
    }
  }

  static int[] fixMatrix(int[][] m){
    for(int i = 0; i < m.length; i++){
      for(int j = 0; j < m[0].length; j++){
        // invert bit in m[i][j]
        m[i][j] ^= 1;
        if(hasParityProperty(m)) return new int[]{i, j};
        // restore bit in m[i][j]
        m[i][j] ^= 1;
      }
    }
    return null;
  }

  static boolean hasParityProperty(int[][] m){
    for(int i = 0; i < m.length; i++){
      if(!isEven(sumRow(m, i))) return false;
    }
    for(int j = 0; j < m[0].length; j++){
      if(!isEven(sumCol(m, j))) return false;
    }
    return true;
  }

  static boolean isEven(int n){
    return n % 2 == 0;
  }

  static int sumRow(int[][] m, int row){
    int s = 0;
    for(int j = 0; j < m[0].length; j++){
      s += m[row][j];
    }
    return s;
  }

  static int sumCol(int[][] m, int col){
    int s = 0;
    for(int i = 0; i < m.length; i++){
      s += m[i][col];
    }
    return s;
  }
}
