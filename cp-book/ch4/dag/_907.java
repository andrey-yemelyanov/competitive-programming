import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 907 Winterim Backpacking Trip
Problem url: https://uva.onlinejudge.org/external/9/907.pdf
Author: Andrey Yemelyanov
*/
public class _907 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt(); int K = s.nextInt();
      int[] camps = new int[N + 1];
      for(int i = 0; i < camps.length; i++){
        camps[i] = s.nextInt();
      }
      System.out.println(hike(camps, K, N));
    }
  }

  static int hike(int[] camps, int k, int n){
    nCamps = n + 2;
    memo = new int[nCamps + 1][k + 2];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        memo[i][j] = UNDEF;
      }
    }

    buildDistMatrix(camps);
    return hike(0, nCamps - 1, k + 1);
  }

  static void buildDistMatrix(int[] camps){
    dist = new int[camps.length + 1][camps.length + 1];
    for(int i = 0; i < dist.length; i++){
      for(int j = 0; j < dist[i].length; j++){
        if(j > i) dist[i][j] = sum(camps, i, j - 1);
      }
    }
  }

  static int sum(int[] camps, int from, int to){
    int sum = 0;
    for(int i = from; i <= to; i++){
      sum += camps[i];
    }
    return sum;
  }

  static final int INF = Integer.MAX_VALUE;
  static final int UNDEF = -1;
  static int[][] memo;
  static int[][] dist;
  static int nCamps;
  static int hike(int current, int dest, int nightsLeft){
    if(nightsLeft < 0) return INF;
    if(current == dest && nightsLeft == 0) return 0;
    if(memo[current][nightsLeft] != UNDEF) return memo[current][nightsLeft];
    int longestDailyHike = hike(current, dest, nightsLeft - 1);
    for(int camp = current + 1; camp < nCamps; camp++){
      int longestDailyHikeViaCamp = max(dist[current][camp], hike(camp, dest, nightsLeft - 1));
      longestDailyHike = min(longestDailyHike, longestDailyHikeViaCamp);
    }
    return memo[current][nightsLeft] = longestDailyHike;
  }
}
