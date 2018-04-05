import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11069 A Graph Problem
Problem url: https://uva.onlinejudge.org/external/110/11069.pdf
Author: Andrey Yemelyanov
*/
public class _11069 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.println(solve(s.nextInt()));
    }
  }

  static int solve(int n){
    int[] dp = new int[max(n + 1, 3)];
    dp[0] = 1; dp[1] = 1; dp[2] = 2;
    for(int i = 3; i <= n; i++){
      dp[i] = dp[i - 2] + dp[i - 3];
    }
    return dp[n];
  }
}
