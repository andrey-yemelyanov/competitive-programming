import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11658 Best Coalitions
Problem url: https://uva.onlinejudge.org/external/116/11658.pdf
Author: Andrey Yemelyanov
*/
public class _11658_BestCoalitions {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    while(s.hasNext()){
      int n = s.nextInt(); int shareholder = s.nextInt();
      if(n == 0 && shareholder == 0) break;
      int[] shares = new int[n];
      for(int i = 0; i < n; i++){
        shares[i] = (int)round((s.nextDouble() * 100));
      }
      //System.out.println(Arrays.toString(shares));
      System.out.printf(Locale.US, "%.2f\n", bestShare(shares, shareholder - 1) * 100);
      //System.out.println(bestShare(shares, shareholder - 1));
    }
  }

  static double bestShare(int[] shares, int shareholder){
    int[] sharesExcludingShareholder = new int[shares.length - 1];
    int i = 0;
    for(int j = 0; j < shares.length; j++){
      if(j != shareholder){
        sharesExcludingShareholder[i++] = shares[j];
      }
    }
    int bestCoalition = bestCoalition(sharesExcludingShareholder, 5001 - shares[shareholder]);
    return (double)shares[shareholder] / (shares[shareholder] + bestCoalition);
  }

  static int bestCoalition(int[] shares, int requiredShare){
    int[][] memo = new int[shares.length][10000];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return bestCoalition(shares, 0, requiredShare, memo);
  }

  static final int INF = 1000000;
  static int bestCoalition(int[] shares, int i, int requiredShare, int[][] memo){
    if(requiredShare <= 0) return 0;
    if(i == shares.length) return INF;
    if(memo[i][requiredShare] != -1) return memo[i][requiredShare];
    return memo[i][requiredShare] = min(bestCoalition(shares, i + 1, requiredShare, memo),
                                        bestCoalition(shares, i + 1, requiredShare - shares[i], memo)
                                        + shares[i]);
  }
}
