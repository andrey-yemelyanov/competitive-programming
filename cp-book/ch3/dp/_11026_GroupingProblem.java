import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11026 A Grouping Problem
Problem url: https://uva.onlinejudge.org/external/110/11026.pdf
Author: Andrey Yemelyanov
*/
public class _11026_GroupingProblem {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int N = s.nextInt(); int M = s.nextInt();
      if(N == 0 && M == 0) break;
      int[] seq = new int[N];
      for(int i = 0; i < N; i++) seq[i] = s.nextInt();
      System.out.println(maxFitness(seq, M));
    }
  }

  static long maxFitness(int[] seq, int M){
    long[][] memo = new long[seq.length + 1][seq.length + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    long maxFitness = 0;
    for(int k = 1; k <= seq.length; k++){
      maxFitness = max(maxFitness, fitness(seq.length, k, seq, M, memo));
    }
    return maxFitness;
  }

  static long fitness(int n, int k, int[] seq, int M, long[][] memo){
    if(k == 0) return 1;
    if(n == 0) return 0;
    if(memo[n][k] != -1) return memo[n][k];
    return memo[n][k] = ((fitness(n - 1, k - 1, seq, M, memo) * seq[n - 1]) % M +
                          fitness(n - 1, k, seq, M, memo) % M) % M;
  }
}
