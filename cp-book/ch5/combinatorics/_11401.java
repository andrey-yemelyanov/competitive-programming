import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11401 Triangle Counting
Problem url: https://uva.onlinejudge.org/external/114/11401.pdf
Author: Andrey Yemelyanov
*/
public class _11401 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    pregenerate();
    while(s.hasNext()){
      int n = s.nextInt();
      if(n < 3) break;
      System.out.println(dp[n]);
    }
  }

  static final int MAX = 1000000;
  static final long[] dp = new long[MAX + 1];
  static void pregenerate(){
    dp[3] = 0;
    long toAdd = 0; int incr = 0;
    for(int i = 4; i <= MAX; i++){
      if(i % 2 == 0) incr++;
      toAdd += incr;
      dp[i] = dp[i - 1] + toAdd;
    }
  }
}
