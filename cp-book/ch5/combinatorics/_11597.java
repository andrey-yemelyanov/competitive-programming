import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11597 Spanning Subtree
Problem url: https://uva.onlinejudge.org/external/115/11597.pdf
Author: Andrey Yemelyanov
*/
public class _11597 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("Case %d: %d\n", c++, countDistinctSpanningTrees(n));
    }
  }

  static int countDistinctSpanningTrees(int n){
    int nEdgesPerSpanningTree = n - 1;
    int nEdgesInGraph = (n * (n - 1)) / 2;
    return nEdgesInGraph / nEdgesPerSpanningTree;
  }
}
