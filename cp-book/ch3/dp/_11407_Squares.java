import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11407 Squares
Problem url: https://uva.onlinejudge.org/external/114/11407.pdf
Author: Andrey Yemelyanov
*/
public class _11407_Squares {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    int[] memo = new int[10001];
    Arrays.fill(memo, -1);
    while(nTests-- > 0){
      System.out.println(squares(s.nextInt(), memo));
    }
  }

  static final int INF = 1000000;
  static int squares(int n, int[] memo){
    if(n < 0) return INF;
    if(n == 0) return 0;
    if(memo[n] != -1) return memo[n];
    int minCount = INF;
    for(int i = 1; i <= (int)sqrt(n); i++){
      minCount = min(minCount, 1 + squares(n - i * i, memo));
    }
    return memo[n] = minCount;
  }
}
