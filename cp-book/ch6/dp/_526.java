import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 526 String Distance and Transform Process
Problem url: https://uva.onlinejudge.org/external/5/526.pdf
Author: Andrey Yemelyanov
*/
public class _526 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    boolean first = true;
    while(s.hasNext()){
      if(first) first = false;
      else System.out.println();
      List<String> commands = getChanges(s.nextLine(), s.nextLine());
      System.out.println(commands.size());
      int i = 1;
      for(String command : commands){
        System.out.println(i++ + " " + command);
      }
    }
  }

  static List<String> getChanges(String a, String b){
    int[][] dp = computeEditDistance(a, b);
    List<String> changes = new ArrayList<>();
    int i = a.length(); int j = b.length();
    while(i != 0 && j != 0){
      // chars are the same
      if(a.charAt(i - 1) == b.charAt(j - 1)){
        i--; j--;
      }
      // replace
      else if(dp[i][j] == dp[i - 1][j - 1] + 1){
        changes.add(String.format("Replace %d,%c", j, b.charAt(j - 1)));
        i--; j--;
      }
      // delete char
      else if(dp[i][j] == dp[i - 1][j] + 1){
        changes.add(String.format("Delete %d", j + 1));
        i--;
      }
      // add char
      else if(dp[i][j] == dp[i][j - 1] + 1){
        changes.add(String.format("Insert %d,%c", j, b.charAt(j - 1)));
        j--;
      }
    }
    while(i > 0){
      changes.add(String.format("Delete %d", 1));
      i--;
    }
    while(j > 0){
      changes.add(String.format("Insert %d,%c", j, b.charAt(j - 1)));
      j--;
    }
    Collections.reverse(changes);
    return changes;
  }

  static int[][] computeEditDistance(String a, String b){
    int m = a.length(); int n = b.length();
    int[][] dp = new int[m + 1][n + 1];
    for(int i = 0; i <= m; i++) dp[i][0] = i;
    for(int j = 1; j <= n; j++) dp[0][j] = j;
    for(int i = 1; i <= m; i++){
      for(int j = 1; j <= n; j++){
        dp[i][j] = min(
          min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
          dp[i - 1][j - 1] + diff(a.charAt(i - 1), b.charAt(j - 1)));
      }
    }
    return dp;
  }

  static int diff(char c1, char c2){
    if(c1 == c2) return 0;
    return 1;
  }
}
