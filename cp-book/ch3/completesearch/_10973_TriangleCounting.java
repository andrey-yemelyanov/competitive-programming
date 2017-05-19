import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10973 Triangle Counting
Problem url: https://uva.onlinejudge.org/external/109/10973.pdf
Author: Andrey Yemelyanov
*/
public class _10973_TriangleCounting {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int V = s.nextInt();
      int E = s.nextInt();
      int[][] m = new int[V][V];
      for(int i = 0; i < E; i++){
        int v1 = s.nextInt() - 1;
        int v2 = s.nextInt() - 1;
        m[v1][v2] = 1;
        m[v2][v1] = 1;
      }
      System.out.println(countTriangles(m));
    }
  }

  static int countTriangles(int[][] m){
    int V = m.length;
    int nTriangles = 0;
    for(int v1 = 0; v1 < V; v1++){
      for(int v2 = v1 + 1; v2 < V; v2++){
        if(m[v1][v2] == 1){
          int n = 0;
          for(int v3 = v2 + 1; v3 < V; v3++){
            if(n == 2) break;
            if(m[v2][v3] == 1 && m[v1][v3] == 1) {
              n++;
            }
          }
          nTriangles += n;
        }
      }
    }
    return nTriangles;
  }
}
