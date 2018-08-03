import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11151 Longest Palindrome
Problem url: https://uva.onlinejudge.org/external/111/11151.pdf
Author: Andrey Yemelyanov
*/
public class _11151 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0) System.out.println(getLongestPalindrome(s.nextLine()));
  }

  static int getLongestPalindrome(String s){
    int[][] dp = new int[s.length()][s.length()];
    if(s.length() == 0) return 0;
    return getLongestPalindrome(s, 0, s.length() - 1, dp);
  }

  static int getLongestPalindrome(String s, int left, int right, int[][] dp){
    if(left == right) return 1;
    if(left + 1 == right) return (s.charAt(left) == s.charAt(right) ? 2 : 1);
    if(dp[left][right] != 0) return dp[left][right];
    if(s.charAt(left) == s.charAt(right)) {
      return dp[left][right] = 2 + getLongestPalindrome(s, left + 1, right - 1, dp);
    }else{
      return dp[left][right] = max(
        getLongestPalindrome(s, left + 1, right, dp),
        getLongestPalindrome(s, left, right - 1, dp)
      );
    }
  }
}
