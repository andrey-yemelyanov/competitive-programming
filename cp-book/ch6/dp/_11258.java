import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11258 String Partition
Problem url: https://uva.onlinejudge.org/external/112/11258.pdf
Author: Andrey Yemelyanov
*/
public class _11258 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0) System.out.println(maxSum(s.next()));
  }

  static long maxSum(String s){
    long[] dp = new long[s.length() + 1];
    for(int i = 0; i < dp.length; i++) dp[i] = -1;
    return findMaxSum(s, 0, dp);
  }

  static long findMaxSum(String s, int i, long[] dp){
    if(dp[i] != -1) return dp[i];
    long maxSum = 0;
    for(int k = i; k < s.length(); k++){
      if(k > i && s.charAt(i) == '0') break;
      String sub = s.substring(i, k + 1);
      if(!fitsInto32BitInt(sub)) break;
      maxSum = max(maxSum, Integer.parseInt(sub) + findMaxSum(s, k + 1, dp));
    }
    return dp[i] = maxSum;
  }

  static boolean fitsInto32BitInt(String s){
    try{
      Integer.parseInt(s);
      return true;
    }catch(Exception ex){
      return false;
    }
  }
}
