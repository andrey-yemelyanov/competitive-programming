import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12457 Tennis contest
Problem url: https://uva.onlinejudge.org/external/124/12457.pdf
Author: Andrey Yemelyanov
*/
public class _12457 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt(); double p = s.nextDouble();
      double probability = 0.0;
      int nGames = 2 * n - 1;
      System.out.printf("%.2f\n", P(n, nGames, p));
    }
  }

  static double P(int k, int m, double p){
    return probability(k, m, p, new double[k + 1][m + 1]);
  }

  // Let P(k,m) = P(A wins exactly k out of m matches)
  static double probability(int k, int m, double p, double[][] memo){
    if(k == 0) return 1.0;
    if(k > m) return 0.0;
    if(memo[k][m] != 0) return memo[k][m];
    return memo[k][m] = p * probability(k - 1, m - 1, p, memo) + (1 - p) * probability(k, m - 1, p, memo);
  }
}
