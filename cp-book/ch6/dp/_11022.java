import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11022 - String Factoring
Problem url: https://uva.onlinejudge.org/external/110/11022.pdf
Author: Andrey Yemelyanov
*/
public class _11022 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String str = s.next();
      if(str.equals("*")) break;
      System.out.printf("%d\n", minWeight(str));
    }
  }

  static int minWeight(String s){
    int[][] dp = new int[s.length()][s.length()];
    return minWeight(s, 0, s.length() - 1, dp);
  }

  static int minWeight(String s, int i, int j, int[][] dp){
    if(i == j) return 1;
    if(dp[i][j] != 0) return dp[i][j];
    int minWeight = Integer.MAX_VALUE;
    for(int k = i; k < j; k++){
      int leftWeight = minWeight(s, i, k, dp);
      int rightWeight = minWeight(s, k + 1, j, dp);
      String sub1 = s.substring(i, k + 1);
      String sub2 = s.substring(k + 1, j + 1);
      if(leftWeight == rightWeight && (isPower(sub2, sub1) || isPower(sub1, sub2))){
        minWeight = min(minWeight, leftWeight);
      }else{
        minWeight = min(minWeight, leftWeight + rightWeight);
      }
    }
    return dp[i][j] = minWeight;
  }

  static boolean isPower(String pattern, String s){
    if(s.length() % pattern.length() != 0) return false;
    for(int i = 0; i <= s.length() - pattern.length(); i += pattern.length()){
      if(!s.substring(i, i + pattern.length()).equals(pattern)) return false;
    }
    return true;
  }
}
