import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10635 Price and Princess
Problem url: https://uva.onlinejudge.org/external/106/10635.pdf
Author: Andrey Yemelyanov
*/
public class _10635 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    for(int i = 1; i <= nTests; i++){
      s.nextLine();
      String[] prince = s.nextLine().split("\\s+");
      String[] princess = s.nextLine().split("\\s+");
      System.out.printf("Case %d: %d\n", i, lcs(prince, princess));
    }
  }

  static int lcs(String[] s1, String[] s2){
    int[][] lcs = new int[s1.length + 1][s2.length + 1];
    for(int i = 0; i <= s1.length; i++){
      for(int j = 0; j <= s2.length; j++){
        if(i == 0 || j == 0) lcs[i][j] = 0;
        else if(s1[i - 1].equals(s2[j - 1])) lcs[i][j] = 1 + lcs[i - 1][j - 1];
        else lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]);
      }
    }
    return lcs[s1.length][s2.length];
  }
}
